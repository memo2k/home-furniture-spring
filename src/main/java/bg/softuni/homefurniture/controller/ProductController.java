package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.binding.AddProductBindingModel;
import bg.softuni.homefurniture.model.dto.view.ProductDetailsViewModel;
import bg.softuni.homefurniture.model.entity.Comment;
import bg.softuni.homefurniture.model.enums.CategoryName;
import bg.softuni.homefurniture.service.CommentService;
import bg.softuni.homefurniture.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final CommentService commentService;

    public ProductController(ProductService productService, CommentService commentService) {
        this.productService = productService;
        this.commentService = commentService;
    }

    @GetMapping("/product/{id}")
    public ModelAndView singleProduct(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("product");

        ProductDetailsViewModel product = productService.getDetails(id);
        List<Comment> comments = commentService.orderCommentsByDateDesc(product.getComments());

        modelAndView.addObject("product", product);
        modelAndView.addObject("comments", comments);

        return modelAndView;
    }

    @GetMapping("/all-products")
    public ModelAndView allProducts() {
        ModelAndView modelAndView = new ModelAndView("all-products");

        modelAndView.addObject("products", productService.getAll());

        return modelAndView;
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
