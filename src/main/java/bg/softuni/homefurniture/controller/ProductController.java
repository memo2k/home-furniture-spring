package bg.softuni.homefurniture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    // will take an id
    @GetMapping("/product")
    public ModelAndView singleProduct() {
        return new ModelAndView("product.html");
    }

    @GetMapping("/all-products")
    public ModelAndView allProducts() {
        return new ModelAndView("all-products.html");
    }

    @GetMapping("/favorite-products")
    public ModelAndView favoriteProducts() {
        return new ModelAndView("favorite-products.html");
    }
}
