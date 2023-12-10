package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.exceptions.ProductNotFoundException;
import bg.softuni.homefurniture.model.dto.binding.AddProductBindingModel;
import bg.softuni.homefurniture.model.dto.binding.EditProductBindingModel;
import bg.softuni.homefurniture.model.dto.view.ProductViewModel;
import bg.softuni.homefurniture.model.dto.view.ProductDetailsViewModel;
import bg.softuni.homefurniture.model.entity.Category;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.enums.CategoryName;
import bg.softuni.homefurniture.repository.CategoryRepository;
import bg.softuni.homefurniture.repository.ProductRepository;
import bg.softuni.homefurniture.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(AddProductBindingModel addProductBindingModel) {
        Product product = modelMapper.map(addProductBindingModel, Product.class);
        product.setCreatedOn(LocalDateTime.now());

        Category category = categoryRepository.findByName(CategoryName.valueOf(addProductBindingModel.getCategoryName()));
        product.setCategory(category);

        productRepository.save(product);
    }

    @Override
    public void editProduct(EditProductBindingModel editProductBindingModel) {
        Product product = productRepository.findById(editProductBindingModel.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        Category category = categoryRepository.findByName(CategoryName.valueOf(editProductBindingModel.getCategoryName()));
        product.setCategory(category);

        modelMapper.map(editProductBindingModel, product);
        productRepository.save(product);
    }

    @Override
    public List<ProductViewModel> getAll() {
        return productRepository.findAllByOrderByCreatedOnDesc().stream()
                .map(product -> modelMapper.map(product, ProductViewModel.class))
                .toList();
    }

    @Override
    public ProductDetailsViewModel getDetails(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        return modelMapper.map(product, ProductDetailsViewModel.class);
    }

    @Override
    public Product getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        return modelMapper.map(product, Product.class);
    }

    @Override
    public List<ProductViewModel> findNewProducts() {
        return productRepository.findTop8ByOrderByCreatedOnDesc().stream()
                .map(product -> modelMapper.map(product, ProductViewModel.class))
                .toList();
    }

    @Override
    public List<ProductViewModel> getProductsFromCategory(CategoryName categoryName) {
        return productRepository.findByCategoryName(categoryName).stream()
                .map(product -> modelMapper.map(product, ProductViewModel.class))
                .toList();
    }

    @Override
    public List<ProductViewModel> searchProducts(String keyword) {
        return productRepository.findAllByNameContainingIgnoreCaseOrderByCreatedOnDesc(keyword).stream()
                .map(product -> modelMapper.map(product, ProductViewModel.class))
                .toList();
    }

    @Override
    public EditProductBindingModel findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found."));

        return modelMapper.map(product, EditProductBindingModel.class);
    }
}
