package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.binding.EditUserBindingModel;
import bg.softuni.homefurniture.model.dto.view.UserViewModel;
import bg.softuni.homefurniture.service.UserService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult";
    public static final String DOT = ".";
    private final UserService userService;
    private final LoggedUser loggedUser;

    public AdminController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/users-list")
    public ModelAndView productsList() {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (loggedUser.isAdmin()) {
            modelAndView.setViewName("admin/users-list");
            List<UserViewModel> users = userService.getAll();

            modelAndView.addObject("users", users);
        } else {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView editUser(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (!loggedUser.isAdmin()) {
            modelAndView.setViewName("redirect:/");
        } else {
            EditUserBindingModel editUserBindingModel = userService.findById(id);

            model.addAttribute("editUserBindingModel", editUserBindingModel);
            modelAndView.setViewName("admin/edit-user");
        }

        return modelAndView;
    }

    @PostMapping("/admin/edit-product/{id}")
    public ModelAndView editUser(@PathVariable Long id,
                                    @Valid EditUserBindingModel editUserBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (!loggedUser.isAdmin()) {
            modelAndView.setViewName("redirect:/");
        } else {
            if (bindingResult.hasErrors()) {
                final String attributeName = "editUserBindingModel";
                redirectAttributes
                        .addFlashAttribute(attributeName, editUserBindingModel)
                        .addFlashAttribute(BINDING_RESULT_PATH + DOT + attributeName, bindingResult);
                modelAndView.setViewName("redirect:/admin/edit-user/" + id);
            } else {
                userService.editUser(editUserBindingModel);
                modelAndView.setViewName("redirect:/admin/users-list");
            }
        }

        return modelAndView;
    }
}
