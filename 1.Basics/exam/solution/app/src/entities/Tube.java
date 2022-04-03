package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tubes")
public class Tube {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public String author;

    @Column(nullable = false, columnDefinition = "text")
    public String description;

    @Column(nullable = false, name = "youtube_id")
    public String youtubeId;

    @Column(nullable = false)
    public long views;

    @ManyToOne(optional = false)
    public User uploader;

    public Tube() {
    }

    private String getVideoThumbnailUrl() {
        return "https://img.youtube.com/vi/" + this.getYoutubeId() + "/hqdefault.jpg";
    }


    public String getIFrameUrl() {
        return "http://www.youtube.com/embed/" + this.getYoutubeId() + "?autoplay=0&amp;showinfo=0&amp;controls=0";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public String getTubeThumbnailView() {
        StringBuilder result = new StringBuilder();

        result.append("<div class=\"col-md-3 d-flex flex-column text-center\">")
                .append("<td><a href=\"/tube/details/" + this.getId() + "\">")
                .append("<img src=\""
                        + this.getVideoThumbnailUrl() + "\" class=\"img-thumbnail\"></a>")
                .append("<h4 class=\"text-center\">" + this.getTitle() + "</h4>")
                .append("<h5 class=\"text-center font-italic\">" + this.getAuthor() + "</h5>")
                .append("</div>");

        return result.toString();
    }

    public String getTubeTableView() {
        StringBuilder result = new StringBuilder();

        result.append("<tr>")
                .append("<th scope=\"row\">%d</th>")
                .append("<td>" + this.getTitle() + "</td>")
                .append("<td>" + this.getAuthor() + "</td>")
                .append("<td><a href=\"/tube/details/" + this.getId() + "\">Details</a></td>")
                .append("</div>");

        return result.toString();
    }
}
