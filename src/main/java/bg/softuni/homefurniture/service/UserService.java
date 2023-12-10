package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.dto.binding.EditUserBindingModel;
import bg.softuni.homefurniture.model.dto.view.UserProfileViewModel;
import bg.softuni.homefurniture.model.dto.view.UserViewModel;
import bg.softuni.homefurniture.model.entity.User;

import java.util.List;

public interface UserService {
    User getAuth();

    List<UserViewModel> getAll();

    boolean isUniqueEmail(String value);

    UserProfileViewModel getUserProfile();

    EditUserBindingModel findById(Long id);

    void editUser(EditUserBindingModel editUserBindingModel);
}
