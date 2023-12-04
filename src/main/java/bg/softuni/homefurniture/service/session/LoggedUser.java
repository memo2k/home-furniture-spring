package bg.softuni.homefurniture.service.session;

import bg.softuni.homefurniture.model.entity.Role;
import bg.softuni.homefurniture.model.enums.UserRoles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Component
@SessionScope
public class LoggedUser {
    private Long id;

    private String username;

    private String email;

    private Set<Role> roles;

    private boolean isLogged;

    public LoggedUser() {
        this.roles = new HashSet<>();
    }

    public void reset() {
        this.setId(null);
        this.setUsername(null);
        this.setEmail(null);
        this.setRoles(Collections.emptySet());
        this.setLogged(false);
    }

    public boolean isAdmin() {
        return this.roles.stream().anyMatch(role -> role.getName().equals(UserRoles.ADMIN));
    }
}
