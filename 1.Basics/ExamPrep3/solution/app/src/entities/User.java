package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition="VARCHAR(40)")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "UUID")  // - not used because of a java.sql.SQLSyntaxErrorException: Specified key was too long; max key length is 1000 bytes
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(unique = true, nullable = false, columnDefinition="VARCHAR(40)")
    public String username;

    @Column(nullable = false, columnDefinition="VARCHAR(40)")
    public String password;

    @Column(nullable = false, columnDefinition="VARCHAR(40)")
    @Enumerated(EnumType.STRING)
    public Gender gender;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    public Set<User> friends;

    public User() {
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public String getUserAddFriendView() {
        StringBuilder result = new StringBuilder();

        result.append("<div class=\"col-md-2 d-flex flex-column text-center\">")
                .append("<img src=\""
                        + Gender.getSimpleValue(this.getGender()) + ".png\" class=\"img-thumbnail\" width=\"200\" height=\"200\">")
                .append("<h5 class=\"text-center\">" + this.getUsername() + "</h5>")
                .append("<a href=\"/friends/add/" + this.getId() + "\" class=\"btn btn-info add-friend-btn\">Add Friend</a>")
                .append("</div>");

        return result.toString();
    }


    public String getUserRemoveFriendView() {
        StringBuilder result = new StringBuilder();

        result.append("<div class=\"col-md-2 d-flex flex-column text-center\">")
                .append("<h5 class=\"text-center\"><a href=\"/profile/" + this.getId() + "\" class=\"text-muted\">" + this.getUsername() + "</a></h5>")
                .append("</div><div class=\"col-md-2 d-flex flex-column text-center\">")
                .append("<a href=\"/friends/unfriend/" + this.getId() + "\" class=\"btn btn-danger add-friend-btn\">Unfriend</a>")
                .append("</div>");

        return result.toString();
    }

    public String getProfileView() {
        StringBuilder result = new StringBuilder();

        result.append("<div class=\"col-md-6 d-flex flex-column \">")
                .append("<img src=\""
                        + Gender.getSimpleValue(this.getGender()) + ".png\" class=\"mx-auto img-thumbnail\" width=\"200\" height=\"200\">")
                .append("<h4 class=\"mx-auto mt-3\">" + this.getUsername() + "</h4>")
                .append("<h5 class=\"mx-auto mt-3\">" + Gender.getComplexValue(this.getGender()) + "</h5>")
                .append("</div>");

        return result.toString();
    }
}
