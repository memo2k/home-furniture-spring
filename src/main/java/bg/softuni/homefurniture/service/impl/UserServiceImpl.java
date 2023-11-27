package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.repository.UserRepository;
import bg.softuni.homefurniture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> getUser() {
        return null;
    }
}
