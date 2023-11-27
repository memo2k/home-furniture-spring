package bg.softuni.homefurniture.model.entity;

import bg.softuni.homefurniture.model.enums.CategoryName;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
