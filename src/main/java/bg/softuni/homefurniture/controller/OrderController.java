package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.binding.CreateOrderBindingModel;
import bg.softuni.homefurniture.model.dto.view.OrderDetailsViewModel;
import bg.softuni.homefurniture.model.entity.Cart;
import bg.softuni.homefurniture.model.entity.Order;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.model.enums.OrderStatus;
import bg.softuni.homefurniture.service.AuthenticationService;
import bg.softuni.homefurniture.service.OrderService;
import bg.softuni.homefurniture.service.UserService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
public class OrderController {
    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult";
    public static final String DOT = ".";
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
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else {
            modelAndView.setViewName("orders-history");

            User user = userService.getAuth();
            Set<Order> orders = user.getOrders();

            modelAndView.addObject("orders", orders);
        }

        return modelAndView;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(Model model) {
        ModelAndView modelAndView = new ModelAndView();

        User user = authenticationService.getUserById(loggedUser.getId());
        Cart cart = user.getCart();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (cart.getProducts().isEmpty()) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("checkout");

            if (!model.containsAttribute("createOrderBindingModel")) {
                model.addAttribute("createOrderBindingModel", new CreateOrderBindingModel());
            }

            Set<Product> products = cart.getProducts();

            modelAndView.addObject("cart", cart);
            modelAndView.addObject("products", products);
        }

        return modelAndView;
    }

    @PostMapping("/checkout")
    public ModelAndView checkout(@Valid CreateOrderBindingModel createOrderBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else {
            if (bindingResult.hasErrors()) {
                final String attributeName = "createOrderBindingModel";
                redirectAttributes
                        .addFlashAttribute(attributeName, createOrderBindingModel)
                        .addFlashAttribute(BINDING_RESULT_PATH + DOT + attributeName, bindingResult);
                modelAndView.setViewName("redirect:/checkout");
            } else {
                orderService.createOrder(createOrderBindingModel);
                modelAndView.setViewName("redirect:/orders-history");
            }
        }

        return modelAndView;
    }

    @GetMapping("/admin/orders-list")
    public ModelAndView ordersList() {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (!loggedUser.isAdmin()) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("admin/orders-list");

            List<Order> orders = orderService.getAll();
            modelAndView.addObject("orders", orders);
        }

        return modelAndView;
    }

    @GetMapping("/admin/order-details/{orderId}")
    public ModelAndView orderDetailsAdmin(@PathVariable Long orderId) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (!loggedUser.isAdmin()) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("admin/order-details");

            OrderDetailsViewModel order = orderService.getDetails(orderId);
            modelAndView.addObject("order", order);
        }

        return modelAndView;
    }

    @PostMapping("/admin/order-details/{orderId}")
    public ModelAndView updateOrderStatus(@PathVariable Long orderId, @RequestParam("orderStatus") OrderStatus orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
        return new ModelAndView("redirect:/admin/order-details/" + orderId);
    }

    @GetMapping("/order-details/{orderId}")
    public ModelAndView orderDetails(@PathVariable Long orderId) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else {
            modelAndView.setViewName("order-details");
            OrderDetailsViewModel order = orderService.getDetails(orderId);

            modelAndView.addObject("order", order);
        }

        return modelAndView;
    }
}
