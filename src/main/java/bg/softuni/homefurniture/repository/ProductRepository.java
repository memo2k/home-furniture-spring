package bg.softuni.homefurniture.repository;

import bg.softuni.homefurniture.model.dto.view.ProductViewModel;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop8ByOrderByCreatedOnDesc();

    List<Product> findAllByOrderByCreatedOnDesc();

    List<Product> findAllByCategory(CategoryName categoryName);
}
