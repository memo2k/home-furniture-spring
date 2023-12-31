package bg.softuni.homefurniture.repository;

import bg.softuni.homefurniture.model.dto.view.ProductViewModel;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop8ByOrderByCreatedOnDesc();

    List<Product> findAllByOrderByCreatedOnDesc();

    List<Product> findByCategoryName(CategoryName categoryName);

    List<Product> findAllByNameContainingIgnoreCaseOrderByCreatedOnDesc(String keyword);
}
