package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.binding.AddCommentBindingModel;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.service.*;
import bg.softuni.homefurniture.service.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final ProductService productService;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final FavoriteService favoriteService;
    private final LoggedUser loggedUser;
    private final CommentService commentService;

    public CommentController(ProductService productService, AuthenticationService authenticationService, UserService userService, FavoriteService favoriteService, LoggedUser loggedUser, CommentService commentService) {
        this.productService = productService;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.favoriteService = favoriteService;
        this.loggedUser = loggedUser;
        this.commentService = commentService;
    }

    @PostMapping("/add/{productId}")
    public ModelAndView addComment(AddCommentBindingModel addCommentBindingModel, @PathVariable Long productId) {
        User user = userService.getAuth();
        Product product = productService.getProductById(productId);

        commentService.addCommentToProduct(user, product, addCommentBindingModel);

        return new ModelAndView("redirect:/product/" + productId);
    }
}
