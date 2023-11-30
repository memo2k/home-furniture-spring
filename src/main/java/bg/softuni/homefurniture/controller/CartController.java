package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @GetMapping("/list")
    public ModelAndView cart() {
        return new ModelAndView("cart");
    }

    @GetMapping("/add")
    public ModelAndView addProduct() {
        return new ModelAndView("cart");
    }
}
