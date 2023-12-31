package bg.softuni.homefurniture.repository;

import bg.softuni.homefurniture.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getCartById(Long id);

    Cart findByUserId(Long id);
}
