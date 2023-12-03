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
        if(!isProductInCart(cart, product)) {
            cart.getProducts().add(product);

            double price = product.getPromotionalPrice() != null
                    ? product.getPromotionalPrice()
                    : product.getPrice();

            cart.setTotalPrice(cart.getTotalPrice() + price);
            cartRepository.save(cart);
        } else {

        }
    }

    @Override
    public void removeProductFromCart(Cart cart, Product product) {
        double price = product.getPromotionalPrice() != null
                    ? product.getPromotionalPrice()
                    : product.getPrice();

        cart.setTotalPrice(cart.getTotalPrice() - price);
        cart.getProducts().removeIf(p -> p.getId().equals(product.getId()));
        cartRepository.save(cart);
    }

    public boolean isProductInCart(Cart cart, Product product) {
        for (Product cartProduct : cart.getProducts()) {
            if (cartProduct.getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }
}
