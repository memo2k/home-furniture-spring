package bg.softuni.homefurniture.init;

import bg.softuni.homefurniture.model.entity.Role;
import bg.softuni.homefurniture.model.enums.UserRoles;
import bg.softuni.homefurniture.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserRolesInit implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public UserRolesInit(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean hasRoles = roleRepository.count() > 0;

        if (!hasRoles) {
            Set<Role> roles = new HashSet<>();

            Arrays.stream(UserRoles.values())
                    .forEach(userRole -> {
                        Role role = new Role();
                        role.setName(userRole);
                        roles.add(role);
                    });

            roleRepository.saveAll(roles);
        }
    }
}
