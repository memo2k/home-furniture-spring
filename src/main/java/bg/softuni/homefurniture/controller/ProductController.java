package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.AddProductBindingModel;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.enums.CategoryName;
import bg.softuni.homefurniture.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/admin/add-product")
    public ModelAndView addProduct() {
        ModelAndView modelAndView = new ModelAndView("admin/add-product");

        modelAndView.addObject("categories", CategoryName.values());

        return modelAndView;
    }

    @PostMapping("/admin/add-product")
    public ModelAndView addProduct(AddProductBindingModel addProductBindingModel) {
        productService.addProduct(addProductBindingModel);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/admin/products-list")
    public ModelAndView productsList() {
        return new ModelAndView("admin/products-list");
    }
}
