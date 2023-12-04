package bg.softuni.homefurniture.model.entity;

import bg.softuni.homefurniture.model.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UserRoles name;

    public Role() {

    }
}
