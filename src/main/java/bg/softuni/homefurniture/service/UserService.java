package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.dto.view.UserProfileViewModel;
import bg.softuni.homefurniture.model.entity.User;

import java.util.List;

public interface UserService {
    User getAuth();

    List<User> getAll();

    boolean isUniqueEmail(String value);

    UserProfileViewModel getUserProfile();
}
