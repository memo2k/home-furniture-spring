package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.model.entity.Cart;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.repository.CartRepository;
import bg.softuni.homefurniture.repository.ProductRepository;
import bg.softuni.homefurniture.repository.UserRepository;
import bg.softuni.homefurniture.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    public CartServiceImpl(UserRepository userRepository, CartRepository cartRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Cart getCartById(Long id) {
        Cart cart = cartRepository.getCartById(id);

        return null;
    }

    @Override
    public Cart getUserCart(Long id) {
        Optional<Cart> cart = Optional.ofNullable(cartRepository.findByUserId(id));
        return modelMapper.map(cart, Cart.class);
    }

    @Override
    public void addProductToCart(Cart cart, Product product) {
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    @Override
    public void removeProductFromCart(Cart cart, Product product) {
        cart.getProducts().removeIf(p -> p.getId().equals(product.getId()));
        cartRepository.save(cart);
    }
}
