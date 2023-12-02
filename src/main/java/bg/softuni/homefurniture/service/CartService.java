package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.entity.Cart;
import bg.softuni.homefurniture.model.entity.Product;

public interface CartService {
    Cart getCartById(Long id);

    Cart getUserCart(Long id);

    void addProductToCart(Cart cart, Product product);

    void removeProductFromCart(Cart cart, Product product);
}
