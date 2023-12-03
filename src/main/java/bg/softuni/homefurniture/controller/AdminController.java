package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users-list")
    public ModelAndView productsList() {
        ModelAndView modelAndView = new ModelAndView("admin/users-list");
        List<User> users = userService.getAll();

        modelAndView.addObject("users", users);

        return modelAndView;
    }
}
