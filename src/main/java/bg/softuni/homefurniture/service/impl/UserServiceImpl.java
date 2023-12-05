package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.exceptions.UserNotFoundException;
import bg.softuni.homefurniture.model.dto.view.UserProfileViewModel;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.repository.UserRepository;
import bg.softuni.homefurniture.service.UserService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public User getAuth() {
        final String email = loggedUser.getEmail();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " is not present"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdOn"));
    }

    @Override
    public boolean isUniqueEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public UserProfileViewModel getUserProfile() {
        User user = this.getAuth();
        return modelMapper.map(user, UserProfileViewModel.class);
    }
}
