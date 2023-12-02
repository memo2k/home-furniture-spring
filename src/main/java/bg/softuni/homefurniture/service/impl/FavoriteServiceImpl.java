package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.model.entity.Favorite;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.repository.FavoriteRepository;
import bg.softuni.homefurniture.repository.ProductRepository;
import bg.softuni.homefurniture.repository.UserRepository;
import bg.softuni.homefurniture.service.FavoriteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final FavoriteRepository favoriteRepository;
    private final ModelMapper modelMapper;

    public FavoriteServiceImpl(UserRepository userRepository, ProductRepository productRepository, FavoriteRepository favoriteRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.favoriteRepository = favoriteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Favorite getUserFavoritesList(Long id) {
        Optional<Favorite> favorite = Optional.ofNullable(favoriteRepository.findByUserId(id));
        return modelMapper.map(favorite, Favorite.class);
    }

    @Override
    public void addProductToFavoritesList(Favorite favorite, Product product) {
        favorite.getProducts().add(product);
        favoriteRepository.save(favorite);
    }

    @Override
    public void removeProductFromFavorites(Favorite favorite, Product product) {
        favorite.getProducts().removeIf(p -> p.getId().equals(product.getId()));
        favoriteRepository.save(favorite);
    }
}
