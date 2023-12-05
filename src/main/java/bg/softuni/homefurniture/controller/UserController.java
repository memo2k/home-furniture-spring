package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.exceptions.LoginCredentialsException;
import bg.softuni.homefurniture.model.dto.binding.UserLoginBindingModel;
import bg.softuni.homefurniture.model.dto.binding.UserRegisterBindingModel;
import bg.softuni.homefurniture.model.dto.view.UserProfileViewModel;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.service.AuthenticationService;
import bg.softuni.homefurniture.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult";
    public static final String DOT = ".";
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
        authenticationService.login(userLoginBindingModel);
        return new ModelAndView("redirect:/");
    }

    @ExceptionHandler(LoginCredentialsException.class)
    public ModelAndView handleLoginCredentialsError(LoginCredentialsException e,
                                                    RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("badCredentials", true);
        System.out.println(e.getMessage());

        return new ModelAndView("redirect:login");
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        final ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            final String attributeName = "userRegisterBindingModel";
            redirectAttributes
                    .addFlashAttribute(attributeName, userRegisterBindingModel)
                    .addFlashAttribute(BINDING_RESULT_PATH + DOT + attributeName, bindingResult);
            modelAndView.setViewName("redirect:register");
        } else {
            this.authenticationService.register(userRegisterBindingModel);
            modelAndView.setViewName("redirect:login");
        }

        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout() {
        this.authenticationService.logout();

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        UserProfileViewModel userProfile = userService.getUserProfile();
        ModelAndView modelAndView = new ModelAndView("profile");

        modelAndView.addObject("user", userProfile);
        return modelAndView;
    }
}
