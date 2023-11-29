package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.repository.ProductRepository;
import bg.softuni.homefurniture.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
