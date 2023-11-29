package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.model.dto.UserLoginBindingModel;
import bg.softuni.homefurniture.model.dto.UserRegisterBindingModel;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.repository.UserRepository;
import bg.softuni.homefurniture.service.UserService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        User user = modelMapper.map(userRegisterBindingModel, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        this.userRepository.save(user);
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        User user = this.userRepository.findByEmail(userLoginBindingModel.getEmail());

        String email = userLoginBindingModel.getEmail();

        if (user == null) {
            throw new IllegalArgumentException("User with email " +  email + " does not exist");
        }

        boolean passwordMatch = passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword());

        if (!passwordMatch) {
            throw new IllegalArgumentException("Password is incorrect");
        }

        loggedUser.setUsername(user.getUsername());
        loggedUser.setEmail(user.getEmail());
        loggedUser.setLogged(true);

        return true;
    }

    @Override
    public void logout() {
        loggedUser.setUsername(null);
        loggedUser.setEmail(null);
        loggedUser.setLogged(false);
    }
}
