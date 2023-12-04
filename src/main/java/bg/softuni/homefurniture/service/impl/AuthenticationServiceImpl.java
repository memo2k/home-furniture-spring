package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.exceptions.LoginCredentialsException;
import bg.softuni.homefurniture.model.dto.binding.UserLoginBindingModel;
import bg.softuni.homefurniture.model.dto.binding.UserRegisterBindingModel;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.model.enums.UserRoles;
import bg.softuni.homefurniture.repository.RoleRepository;
import bg.softuni.homefurniture.repository.UserRepository;
import bg.softuni.homefurniture.service.AuthenticationService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;
    private final RoleRepository roleRepository;

    public AuthenticationServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        User user = modelMapper.map(userRegisterBindingModel, User.class);

        user.setRoles(Set.of(roleRepository.findByName(UserRoles.USER)));
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        user.setCreatedOn(LocalDateTime.now());

        this.userRepository.save(user);
    }

    @Override
    public void login(UserLoginBindingModel userLoginBindingModel) {
        String email = userLoginBindingModel.getEmail();

        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new LoginCredentialsException("No user found with this email."));

        boolean passwordMatch = passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword());

        if (!passwordMatch){
            throw new LoginCredentialsException("Incorrect password.");
        }

        loggedUser.setId(user.getId());
        loggedUser.setUsername(user.getUsername());
        loggedUser.setEmail(user.getEmail());
        loggedUser.setLogged(true);
        loggedUser.setRoles(user.getRoles());
    }

    @Override
    public void logout() {
        loggedUser.setId(null);
        loggedUser.setUsername(null);
        loggedUser.setEmail(null);
        loggedUser.setLogged(false);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
//                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        return modelMapper.map(user, User.class);
    }
}
