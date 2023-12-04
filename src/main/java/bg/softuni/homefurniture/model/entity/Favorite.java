package bg.softuni.homefurniture.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "favorites")
public class Favorite extends BaseEntity {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "favorites_products",
            joinColumns = @JoinColumn(name = "favorite_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    @ManyToOne
    private User user;

    public Favorite() {
        this.products = new HashSet<>();
    }
}
