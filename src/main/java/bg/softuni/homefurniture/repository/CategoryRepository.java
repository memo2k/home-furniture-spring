package bg.softuni.homefurniture.repository;

import bg.softuni.homefurniture.model.entity.Category;
import bg.softuni.homefurniture.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> getAllByNameIn(Set<CategoryName> categoryNames);

    Category findByName(CategoryName categoryName);
}
