package bg.softuni.homefurniture.init;

import bg.softuni.homefurniture.model.entity.Role;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.model.enums.UserRoles;
import bg.softuni.homefurniture.repository.RoleRepository;
import bg.softuni.homefurniture.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class AdminUserInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInit(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Role userRole = roleRepository.findByName(UserRoles.USER);
        Role adminRole = roleRepository.findByName(UserRoles.ADMIN);

        if (!userRepository.existsByEmail("admin@admin.com")) {
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(userRole);
            adminRoles.add(adminRole);

            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@admin.com");
            adminUser.setPassword(passwordEncoder.encode("1234"));
            adminUser.setCreatedOn(LocalDateTime.now());
            adminUser.setRoles(adminRoles);

            userRepository.save(adminUser);
        }
    }
}
