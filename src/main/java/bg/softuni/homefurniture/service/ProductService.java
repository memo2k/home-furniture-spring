package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.dto.binding.AddProductBindingModel;
import bg.softuni.homefurniture.model.dto.binding.EditProductBindingModel;
import bg.softuni.homefurniture.model.dto.view.ProductViewModel;
import bg.softuni.homefurniture.model.dto.view.ProductDetailsViewModel;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.enums.CategoryName;

import java.util.List;

public interface ProductService {
    void addProduct(AddProductBindingModel addProductBindingModel);

    void editProduct(EditProductBindingModel editProductBindingModel);

    List<ProductViewModel> getAll();

    ProductDetailsViewModel getDetails(Long id);

    Product getProductById(Long productId);

    List<ProductViewModel> findNewProducts();

    EditProductBindingModel findById(Long id);

    List<ProductViewModel> getProductsFromCategory(CategoryName categoryName);

    List<ProductViewModel> searchProducts(String keyword);
}
