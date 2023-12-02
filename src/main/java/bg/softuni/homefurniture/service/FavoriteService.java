package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.entity.Favorite;
import bg.softuni.homefurniture.model.entity.Product;

public interface FavoriteService {
    Favorite getUserFavoritesList(Long id);

    void addProductToFavoritesList(Favorite favorite, Product product);

    void removeProductFromFavorites(Favorite favorite, Product product);
}
