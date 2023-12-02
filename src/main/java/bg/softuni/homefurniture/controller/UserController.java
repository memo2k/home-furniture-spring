package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.binding.UserLoginBindingModel;
import bg.softuni.homefurniture.model.dto.binding.UserRegisterBindingModel;
import bg.softuni.homefurniture.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {
    private final AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
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
}
