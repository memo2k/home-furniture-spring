package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.entity.Favorite;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.service.*;
import bg.softuni.homefurniture.service.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/favorite-products")
public class FavoriteController {
    private final ProductService productService;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final FavoriteService favoriteService;
    private final LoggedUser loggedUser;

    public FavoriteController(ProductService productService, AuthenticationService authenticationService, UserService userService, FavoriteService favoriteService, LoggedUser loggedUser) {
        this.productService = productService;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.favoriteService = favoriteService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/list")
    public ModelAndView favoriteProducts() {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else {
            modelAndView.setViewName("favorite-products");

            User user = authenticationService.getUserById(loggedUser.getId());
            Favorite favorite = favoriteService.getUserFavoritesList(user.getId());

            if (favorite != null) {
                Set<Product> products = favorite.getProducts();
                modelAndView.addObject("products", products);
            }
        }

        return modelAndView;
    }

    @PostMapping("/add/{productId}")
    public ModelAndView addProductToFavorites(@PathVariable Long productId) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else {
            User user = authenticationService.getUserById(loggedUser.getId());

            Favorite favorite = favoriteService.getUserFavoritesList(user.getId());
            if (favorite == null) {
                favorite = new Favorite();
                favorite.setUser(user);
            }

            Product product = productService.getProductById(productId);
            favoriteService.addProductToFavoritesList(favorite, product);

            modelAndView.setViewName("redirect:/favorite-products/list");
        }

        return modelAndView;
    }

    @PostMapping("/remove/{productId}")
    public ModelAndView removeProduct(@PathVariable Long productId) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else {
            User user = userService.getAuth();
            Favorite favorite = favoriteService.getUserFavoritesList(user.getId());
            Product product = productService.getProductById(productId);

            favoriteService.removeProductFromFavorites(favorite, product);

            modelAndView.setViewName("redirect:/favorite-products/list");
        }

        return modelAndView;
    }
}
