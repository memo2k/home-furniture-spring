package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // will take an id
    @GetMapping("/product")
    public ModelAndView singleProduct() {
        return new ModelAndView("product");
    }

    @GetMapping("/all-products")
    public ModelAndView allProducts() {
        return new ModelAndView("all-products");
    }

    @GetMapping("/favorite-products")
    public ModelAndView favoriteProducts() {
        return new ModelAndView("favorite-products");
    }

    @ModelAttribute("products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
