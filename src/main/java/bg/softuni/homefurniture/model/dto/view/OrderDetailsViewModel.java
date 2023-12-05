package bg.softuni.homefurniture.model.dto.view;

import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.model.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OrderDetailsViewModel {
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private String country;

    private String city;

    private String postalCode;

    private String paymentType;

    private LocalDateTime createdOn;

    private Double totalPrice;

    private OrderStatus orderStatus;

    private Set<Product> products;

    private User user;
}
