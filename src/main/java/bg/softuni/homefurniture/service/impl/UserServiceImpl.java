package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.repository.UserRepository;
import bg.softuni.homefurniture.service.UserService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public User getAuth() {
        return userRepository.findByEmail(loggedUser.getEmail());
    }
}
