package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.model.dto.AddProductBindingModel;
import bg.softuni.homefurniture.model.entity.Category;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.repository.CategoryRepository;
import bg.softuni.homefurniture.repository.ProductRepository;
import bg.softuni.homefurniture.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(AddProductBindingModel addProductBindingModel) {
        Product product = modelMapper.map(addProductBindingModel, Product.class);
        product.setCreatedOn(LocalDate.now());

        productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
