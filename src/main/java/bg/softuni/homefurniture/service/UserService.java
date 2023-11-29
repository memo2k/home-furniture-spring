package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.dto.UserLoginBindingModel;
import bg.softuni.homefurniture.model.dto.UserRegisterBindingModel;
import bg.softuni.homefurniture.model.entity.User;

import java.util.Set;

public interface UserService {
    void register(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();
}
