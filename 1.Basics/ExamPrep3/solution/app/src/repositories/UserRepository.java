package repositories;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository extends BaseRepository {
    public void createUser(User user) {
        this.execute(actionResult -> {
            this.em.persist(user);
        });
    }

    public List<User> findStrangers(String userId) {
        List<User> result = new ArrayList<User>();

        this.execute(actionResult -> {
            result.addAll(this.em.createNativeQuery(
                    "SELECT * FROM users AS u " +
                            "WHERE u.id != \'" + userId + "\' " +
                            "AND u.id NOT IN ( " +
                            "SELECT uf.friend_id FROM users_friends AS uf " +
                            "WHERE uf.user_id = \'" + userId + "\' )", User.class
            ).getResultList());
        });

        return result;
    }

    public List<User> findFriends(String userId) {
        List<User> result = new ArrayList<User>();

        this.execute(actionResult -> {
            result.addAll(this.em.createNativeQuery(
                    "SELECT * FROM users AS u " +
                            "WHERE u.id != \'" + userId + "\' " +
                            "AND u.id IN ( " +
                            "SELECT uf.friend_id FROM users_friends AS uf " +
                            "WHERE uf.user_id = \'" + userId + "\' )", User.class
            ).getResultList());
        });

        return result;
    }

    public User findById(String userId) {
        User user = (User) this.execute(actionResult -> {
            Object queryResult =
                    this.em.createNativeQuery("SELECT * FROM users AS u WHERE u.id = \'" + userId + "\'", User.class)
                            .getResultList()
                            .stream()
                            .findFirst()
                            .orElse(null);

            actionResult.setResult(queryResult);
        }).getResult();

        return user;
    }

    public User findByUsername(String username) {
        User user = (User) this.execute(actionResult -> {
            Object queryResult =
                    this.em.createNativeQuery("SELECT * FROM users AS u WHERE u.username = \'" + username + "\'", User.class)
                            .getResultList()
                            .stream()
                            .findFirst()
                            .orElse(null);

            actionResult.setResult(queryResult);
        }).getResult();

        return user;
    }

    public void addFriend(String userId, String friendId) {
        this.execute(actionResult -> {
                    this.em.createNativeQuery("INSERT INTO users_friends (user_id, friend_id) " +
                            "VALUES(\'" + userId + "\', \'" + friendId + "\'), " +
                            "(\'" + friendId + "\', \'" + userId + "\')")
                            .executeUpdate();
        });
    }

    public void removeFriend(String userId, String friendId) {
        this.execute(actionResult -> {
            this.em.createNativeQuery("DELETE FROM users_friends " +
                    "WHERE user_id= \'" + userId + "\' AND friend_id = \'" + friendId +"\'")
                    .executeUpdate();
            this.em.createNativeQuery("DELETE FROM users_friends " +
                    "WHERE user_id= \'" + friendId + "\' AND friend_id = \'" + userId + "\'")
                    .executeUpdate();
        });
    }
}
