package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.binding.AddCommentBindingModel;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.service.*;
import bg.softuni.homefurniture.service.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comment")
public class CommentController {
    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult";
    public static final String DOT = ".";
    private final ProductService productService;
    private final UserService userService;
    private final LoggedUser loggedUser;
    private final CommentService commentService;

    public CommentController(ProductService productService, UserService userService, LoggedUser loggedUser, CommentService commentService) {
        this.productService = productService;
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.commentService = commentService;
    }

    @PostMapping("/add/{productId}")
    public ModelAndView addComment(@Valid AddCommentBindingModel addCommentBindingModel,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes,
                                   @PathVariable Long productId) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else {
            if (bindingResult.hasErrors()) {
                final String attributeName = "addCommentBindingModel";
                redirectAttributes
                        .addFlashAttribute(attributeName, addCommentBindingModel)
                        .addFlashAttribute(BINDING_RESULT_PATH + DOT + attributeName, bindingResult);
                modelAndView.setViewName("redirect:/product/" + productId);
            } else {
                User user = userService.getAuth();
                Product product = productService.getProductById(productId);

                commentService.addCommentToProduct(user, product, addCommentBindingModel);
                modelAndView.setViewName("redirect:/product/" + productId);
            }
        }

        return modelAndView;
    }

    @DeleteMapping("/{productId}/delete/{id}")
    public ModelAndView deleteComment(@PathVariable("productId") Long productId,
                                      @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (!loggedUser.isAdmin()) {
            modelAndView.setViewName("redirect:/");
        } else {
            commentService.deleteComment(id);
            modelAndView.setViewName("redirect:/product/" + productId);
        }

        return modelAndView;
    }
}
