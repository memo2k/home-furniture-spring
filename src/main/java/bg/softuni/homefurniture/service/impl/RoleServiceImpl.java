package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.model.entity.Role;
import bg.softuni.homefurniture.model.enums.UserRoles;
import bg.softuni.homefurniture.repository.RoleRepository;
import bg.softuni.homefurniture.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(UserRoles.valueOf(name));
    }
}
