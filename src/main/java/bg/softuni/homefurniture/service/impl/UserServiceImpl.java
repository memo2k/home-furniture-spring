package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.exceptions.UserNotFoundException;
import bg.softuni.homefurniture.model.dto.binding.EditUserBindingModel;
import bg.softuni.homefurniture.model.dto.view.UserProfileViewModel;
import bg.softuni.homefurniture.model.dto.view.UserViewModel;
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
    public List<UserViewModel> getAll() {
        return userRepository.findAllByOrderByCreatedOnDesc().stream()
                .map(user -> modelMapper.map(user, UserViewModel.class))
                .toList();
    }

    @Override
    public UserProfileViewModel getUserProfile() {
        User user = this.getAuth();
        return modelMapper.map(user, UserProfileViewModel.class);
    }

    @Override
    public EditUserBindingModel findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not present"));

        return modelMapper.map(user, EditUserBindingModel.class);
    }

    @Override
    public void editUser(EditUserBindingModel editUserBindingModel) {
        User user = userRepository.findById(editUserBindingModel.getId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + editUserBindingModel.getId() + " is not present"));

        modelMapper.map(editUserBindingModel, user);
        userRepository.save(user);
    }

    @Override
    public boolean isUniqueEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }
}
