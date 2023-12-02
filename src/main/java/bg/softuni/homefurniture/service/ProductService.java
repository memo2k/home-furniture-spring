package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.dto.binding.AddProductBindingModel;
import bg.softuni.homefurniture.model.dto.view.ProductDetailsViewModel;
import bg.softuni.homefurniture.model.entity.Comment;
import bg.softuni.homefurniture.model.entity.Product;

import java.util.List;

public interface ProductService {
    void addProduct(AddProductBindingModel addProductBindingModel);

    List<Product> getAll();

    ProductDetailsViewModel getDetails(Long id);

    Product getProductById(Long productId);
}
