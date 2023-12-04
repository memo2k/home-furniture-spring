package bg.softuni.homefurniture.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @ManyToOne
    private User author;

    @ManyToOne
    private Product product;

    public Comment() {

    }
}
