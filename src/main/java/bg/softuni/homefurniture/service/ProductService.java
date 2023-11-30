package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.dto.AddProductBindingModel;
import bg.softuni.homefurniture.model.entity.Product;

import java.util.List;

public interface ProductService {
    void addProduct(AddProductBindingModel addProductBindingModel);
}
