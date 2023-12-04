package bg.softuni.homefurniture.model.entity;

import bg.softuni.homefurniture.model.enums.CategoryName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private CategoryName name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category() {
        this.products = new HashSet<>();
    }
}
