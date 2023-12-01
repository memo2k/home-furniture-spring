package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.AddProductBindingModel;
import bg.softuni.homefurniture.model.enums.CategoryName;
import bg.softuni.homefurniture.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
        ModelAndView modelAndView = new ModelAndView("admin/products-list");
        modelAndView.addObject("products", productService.getAll());

        return modelAndView;
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

        return new ModelAndView("redirect:/admin/products-list");
    }

    @GetMapping("/admin/products-list")
    public ModelAndView productsList() {
        ModelAndView modelAndView = new ModelAndView("admin/products-list");
        modelAndView.addObject("products", productService.getAll());

        return modelAndView;
    }
}
