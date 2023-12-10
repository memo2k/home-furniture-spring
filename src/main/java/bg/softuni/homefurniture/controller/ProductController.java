package bg.softuni.homefurniture.controller;

import bg.softuni.homefurniture.model.dto.binding.AddCommentBindingModel;
import bg.softuni.homefurniture.model.dto.binding.AddProductBindingModel;
import bg.softuni.homefurniture.model.dto.binding.EditProductBindingModel;
import bg.softuni.homefurniture.model.dto.view.ProductViewModel;
import bg.softuni.homefurniture.model.dto.view.ProductDetailsViewModel;
import bg.softuni.homefurniture.model.entity.Comment;
import bg.softuni.homefurniture.model.enums.CategoryName;
import bg.softuni.homefurniture.service.CommentService;
import bg.softuni.homefurniture.service.ProductService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import jakarta.validation.Valid;
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

@Controller
public class ProductController {
    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult";
    public static final String DOT = ".";
    private final ProductService productService;
    private final CommentService commentService;
    private final LoggedUser loggedUser;

    public ProductController(ProductService productService, CommentService commentService, LoggedUser loggedUser) {
        this.productService = productService;
        this.commentService = commentService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/products")
    public ModelAndView showAllProducts(@RequestParam(name = "keyword", required = false) String keyword) {
        ModelAndView modelAndView = new ModelAndView("products");

        List<ProductViewModel> products;

        if (keyword != null && !keyword.isEmpty()) {
            products = productService.searchProducts(keyword);
        } else {
            products = productService.getAll();
        }

        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/products/{categoryName}")
    public ModelAndView getAllProductsByCategory(@PathVariable CategoryName categoryName) {
        List<ProductViewModel> products = productService.getProductsFromCategory(categoryName);

        ModelAndView modelAndView = new ModelAndView("products");

        modelAndView.addObject("products", products);
        modelAndView.addObject("categoryName", categoryName);

        return modelAndView;
    }

    @GetMapping("/product/{id}")
    public ModelAndView singleProduct(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView("product");

        if (!model.containsAttribute("addCommentBindingModel")) {
            model.addAttribute("addCommentBindingModel", new AddCommentBindingModel());
        }

        ProductDetailsViewModel product = productService.getDetails(id);
        List<Comment> comments = commentService.orderCommentsByDateDesc(product.getComments());

        modelAndView.addObject("product", product);
        modelAndView.addObject("comments", comments);

        return modelAndView;
    }

    @GetMapping("/admin/add-product")
    public ModelAndView addProduct(Model model) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (!loggedUser.isAdmin()) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("admin/add-product");

            if (!model.containsAttribute("addProductBindingModel")) {
                model.addAttribute("addProductBindingModel", new AddProductBindingModel());
            }

            modelAndView.addObject("categories", CategoryName.values());
        }

        return modelAndView;
    }

    @PostMapping("/admin/add-product")
    public ModelAndView addProduct(@Valid AddProductBindingModel addProductBindingModel,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (!loggedUser.isAdmin()) {
            modelAndView.setViewName("redirect:/");
        } else {
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
        }

        return modelAndView;
    }

    @GetMapping("/admin/products-list")
    public ModelAndView productsList() {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (!loggedUser.isAdmin()) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("admin/products-list");
            modelAndView.addObject("products", productService.getAll());
        }

        return modelAndView;
    }

    @GetMapping("/admin/edit-product/{id}")
    public ModelAndView editProduct(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (!loggedUser.isAdmin()) {
            modelAndView.setViewName("redirect:/");
        } else {
            EditProductBindingModel editProductBindingModel = productService.findById(id);

            model.addAttribute("editProductBindingModel", editProductBindingModel);
            modelAndView.setViewName("admin/edit-product");
        }

        return modelAndView;
    }

    @PostMapping("/admin/edit-product/{id}")
    public ModelAndView editProduct(@PathVariable Long id,
                                    @Valid EditProductBindingModel editProductBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (!loggedUser.isLogged()) {
            modelAndView.setViewName("redirect:/user/login");
        } else if (!loggedUser.isAdmin()) {
            modelAndView.setViewName("redirect:/");
        } else {
            if (bindingResult.hasErrors()) {
                final String attributeName = "editProductBindingModel";
                redirectAttributes
                        .addFlashAttribute(attributeName, editProductBindingModel)
                        .addFlashAttribute(BINDING_RESULT_PATH + DOT + attributeName, bindingResult);
                modelAndView.setViewName("redirect:/admin/edit-product/" + id);
            } else {
                productService.editProduct(editProductBindingModel);
                modelAndView.setViewName("redirect:/admin/products-list");
            }
        }

        return modelAndView;
    }
}
