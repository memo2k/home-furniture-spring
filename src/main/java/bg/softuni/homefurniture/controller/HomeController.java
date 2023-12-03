package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.entity.Comment;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.service.CommentService;
import bg.softuni.homefurniture.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;
    private final CommentService commentService;

    public HomeController(ProductService productService, CommentService commentService) {
        this.productService = productService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");

        List<Product> products = productService.findNewProducts();
        List<Comment> comments = commentService.findTopComments();

        modelAndView.addObject("products", products);
        modelAndView.addObject("comments", comments);

        return modelAndView;
    }
}
