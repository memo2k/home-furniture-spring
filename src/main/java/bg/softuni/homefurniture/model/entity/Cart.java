package bg.softuni.homefurniture.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {
    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "carts_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    @OneToOne
    private User user;

    public Cart() {
        this.products = new HashSet<>();
        this.totalPrice = 0.00;
    }
}
