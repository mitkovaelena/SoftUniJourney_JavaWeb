package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "kittens")
public class Kitten {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    public String id;

    @Column(unique = true, nullable = false)
    public String name;

    @Column(nullable = false)
    public Integer age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public KittenBreed breed;

    public Kitten() { }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public KittenBreed getBreed() {
        return breed;
    }

    public void setBreed(KittenBreed breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("<div class=\"kitten col-md-4 justify-content-center\">")
                .append("<img src=\"" +  KittenBreed.getSimpleValue(this.breed) + ".jpg\" class=\"img-thumbnail\">")
                .append("<h6 class=\"text-center\">Name: " + this.getName() + "</h6>")
                .append("<h6 class=\"text-center\">Age: " + this.getAge() + "</h6>")
                .append("<h6 class=\"text-center\">Breed: " + KittenBreed.getComplexValue(this.breed) + "</h6>")
                .append("</div>");

        return result.toString();
    }
}
