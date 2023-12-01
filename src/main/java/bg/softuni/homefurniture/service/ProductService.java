package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.dto.AddProductBindingModel;
import bg.softuni.homefurniture.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void addProduct(AddProductBindingModel addProductBindingModel);

    List<Product> getAll();
}
