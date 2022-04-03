package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")  // columnDefinition="VARCHAR(40)"
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(unique = true, nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;

    @Column(unique = true, nullable = false)
    public String email;


    @OneToMany(mappedBy = "uploader", targetEntity = Tube.class)
    public Set<Tube> tubes;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Tube> getTubes() {
        return tubes;
    }

    public void setTubes(Set<Tube> tubes) {
        this.tubes = tubes;
    }

//    public String getUserRemoveFriendView() {
//        StringBuilder result = new StringBuilder();
//
//        result.append("<div class=\"col-md-2 d-flex flex-column text-center\">")
//                .append("<h5 class=\"text-center\"><a href=\"/profile/" + this.getId() + "\" class=\"text-muted\">" + this.getUsername() + "</a></h5>")
//                .append("</div><div class=\"col-md-2 d-flex flex-column text-center\">")
//                .append("<a href=\"/friends/unfriend/" + this.getId() + "\" class=\"btn btn-danger add-friend-btn\">Unfriend</a>")
//                .append("</div>");
//
//        return result.toString();
//    }
//
//    public String getProfileView() {
//        StringBuilder result = new StringBuilder();
//
//        result.append("<div class=\"col-md-6 d-flex flex-column \">")
//                .append("<img src=\""
//                        + Gender.getSimpleValue(this.getGender()) + ".png\" class=\"mx-auto img-thumbnail\" width=\"200\" height=\"200\">")
//                .append("<h4 class=\"mx-auto mt-3\">" + this.getUsername() + "</h4>")
//                .append("<h5 class=\"mx-auto mt-3\">" + Gender.getComplexValue(this.getGender()) + "</h5>")
//                .append("</div>");
//
//        return result.toString();
//    }
}
