package bg.softuni.homefurniture.model.dto.view;

import bg.softuni.homefurniture.model.entity.Order;
import bg.softuni.homefurniture.model.entity.Role;
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
public class UserViewModel {
    private Long id;

    private String username;

    private String email;

    private String password;

    private LocalDateTime createdOn;

    private Set<Order> orders;

    private Set<Role> roles;
}
