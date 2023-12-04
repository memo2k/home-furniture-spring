package bg.softuni.homefurniture.repository;

import bg.softuni.homefurniture.model.entity.Role;
import bg.softuni.homefurniture.model.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(UserRoles role);
}
