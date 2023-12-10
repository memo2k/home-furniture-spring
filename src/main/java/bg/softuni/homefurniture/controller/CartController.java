package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.entity.Cart;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.service.CartService;
import bg.softuni.homefurniture.service.ProductService;
import bg.softuni.homefurniture.service.UserService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;
    private final LoggedUser loggedUser;

    public CartController(CartService cartService, ProductService productService, UserService userService, LoggedUser loggedUser) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/list")
    public ModelAndView cart() {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else {
            modelAndView.setViewName("cart");

            User user = userService.getAuth();
            Cart cart = cartService.getUserCart(user.getId());

            if (cart != null) {
                Set<Product> products = cart.getProducts();
                modelAndView.addObject("products", products);
                modelAndView.addObject("cart", cart);
            }
        }

        return modelAndView;
    }

    @PostMapping("/add/{productId}")
    public ModelAndView addProduct(@PathVariable Long productId) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else {
            User user = userService.getAuth();

            Cart cart = cartService.getUserCart(user.getId());
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
            }

            Product product = productService.getProductById(productId);

            cartService.addProductToCart(cart, product);

            modelAndView.setViewName("redirect:/cart/list");
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
            Cart cart = cartService.getUserCart(user.getId());
            Product product = productService.getProductById(productId);

            cartService.removeProductFromCart(cart, product);

            modelAndView.setViewName("redirect:/cart/list");
        }

        return modelAndView;
    }
}
