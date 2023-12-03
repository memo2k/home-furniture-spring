package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.binding.UserLoginBindingModel;
import bg.softuni.homefurniture.model.dto.binding.UserRegisterBindingModel;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.service.AuthenticationService;
import bg.softuni.homefurniture.service.UserService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public UserController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginBindingModel userLoginBindingModel) {
        boolean isLogged = authenticationService.login(userLoginBindingModel);

        if (isLogged) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("login");

    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterBindingModel userRegisterBindingModel) {
        this.authenticationService.register(userRegisterBindingModel);

        return new ModelAndView("redirect:login");
    }

    @PostMapping("/logout")
    public ModelAndView logout() {
        this.authenticationService.logout();

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("profile");
        User user = userService.getAuth();

        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
