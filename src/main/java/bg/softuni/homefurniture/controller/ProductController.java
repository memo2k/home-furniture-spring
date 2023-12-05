package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.binding.AddProductBindingModel;
import bg.softuni.homefurniture.model.dto.view.ProductViewModel;
import bg.softuni.homefurniture.model.dto.view.ProductDetailsViewModel;
import bg.softuni.homefurniture.model.entity.Comment;
import bg.softuni.homefurniture.model.enums.CategoryName;
import bg.softuni.homefurniture.service.CommentService;
import bg.softuni.homefurniture.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult";
    public static final String DOT = ".";
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
        List<ProductViewModel> products = productService.getAll();

        modelAndView.addObject("products", products);

        return modelAndView;
    }

    @GetMapping("/admin/add-product")
    public ModelAndView addProduct(Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/add-product");

        if (!model.containsAttribute("addProductBindingModel")) {
            model.addAttribute("addProductBindingModel", new AddProductBindingModel());
        }

        modelAndView.addObject("categories", CategoryName.values());

        return modelAndView;
    }

    @PostMapping("/admin/add-product")
    public ModelAndView addProduct(@Valid AddProductBindingModel addProductBindingModel,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            final String attributeName = "addProductBindingModel";
            redirectAttributes
                    .addFlashAttribute(attributeName, addProductBindingModel)
                    .addFlashAttribute(BINDING_RESULT_PATH + DOT + attributeName, bindingResult);
            modelAndView.setViewName("redirect:/admin/add-product");
        } else {
            productService.addProduct(addProductBindingModel);
            modelAndView.setViewName("redirect:/admin/products-list");
        }

        return modelAndView;
    }

    @GetMapping("/admin/products-list")
    public ModelAndView productsList() {
        ModelAndView modelAndView = new ModelAndView("admin/products-list");
        modelAndView.addObject("products", productService.getAll());

        return modelAndView;
    }
}
