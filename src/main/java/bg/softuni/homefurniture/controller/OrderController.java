package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.binding.CreateOrderBindingModel;
import bg.softuni.homefurniture.model.dto.view.OrderDetailsViewModel;
import bg.softuni.homefurniture.model.entity.Cart;
import bg.softuni.homefurniture.model.entity.Order;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.service.AuthenticationService;
import bg.softuni.homefurniture.service.OrderService;
import bg.softuni.homefurniture.service.UserService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final LoggedUser loggedUser;

    public OrderController(OrderService orderService, UserService userService, AuthenticationService authenticationService, LoggedUser loggedUser) {
        this.orderService = orderService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/orders-history")
    public ModelAndView ordersHistory() {
        ModelAndView modelAndView = new ModelAndView("orders-history");

        User user = userService.getAuth();
        Set<Order> orders = user.getOrders();

        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout() {
        ModelAndView modelAndView = new ModelAndView("checkout");

        User user = authenticationService.getUserById(loggedUser.getId());
        Cart cart = user.getCart();
        Set<Product> products = cart.getProducts();

        modelAndView.addObject("cart", cart);
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @PostMapping("/checkout")
    public ModelAndView checkout(CreateOrderBindingModel createOrderBindingModel) {
        orderService.createOrder(createOrderBindingModel);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/admin/orders-list")
    public ModelAndView ordersList() {
        ModelAndView modelAndView = new ModelAndView("admin/orders-list");
        List<Order> orders = orderService.getAll();

        modelAndView.addObject("orders", orders);

        return modelAndView;
    }

    @GetMapping("/admin/order-details/{orderId}")
    public ModelAndView orderDetailsAdmin(@PathVariable Long orderId) {
        ModelAndView modelAndView = new ModelAndView("admin/order-details");
        OrderDetailsViewModel order = orderService.getDetails(orderId);

        modelAndView.addObject("order", order);

        return modelAndView;
    }

    @GetMapping("/order-details/{orderId}")
    public ModelAndView orderDetails(@PathVariable Long orderId) {
        ModelAndView modelAndView = new ModelAndView("order-details");
        OrderDetailsViewModel order = orderService.getDetails(orderId);

        modelAndView.addObject("order", order);

        return modelAndView;
    }
}
