package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.dto.binding.UserLoginBindingModel;
import bg.softuni.homefurniture.model.dto.binding.UserRegisterBindingModel;
import bg.softuni.homefurniture.model.entity.User;

public interface AuthenticationService {
    void register(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();

    User getUserById(Long id);
}
