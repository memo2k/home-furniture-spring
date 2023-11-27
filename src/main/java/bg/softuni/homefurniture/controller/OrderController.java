package bg.softuni.homefurniture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {
    @GetMapping("/orders-history")
    public ModelAndView ordersHistory() {
        return new ModelAndView("orders-history.html");
    }

    @GetMapping("/checkout")
    public ModelAndView orderProducts() {
        return new ModelAndView("checkout.html");
    }
}
