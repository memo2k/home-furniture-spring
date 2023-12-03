package bg.softuni.homefurniture.repository;

import bg.softuni.homefurniture.model.entity.Order;
import bg.softuni.homefurniture.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
