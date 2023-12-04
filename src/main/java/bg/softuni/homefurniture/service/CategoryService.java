package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.entity.Category;
import bg.softuni.homefurniture.model.enums.CategoryName;

import java.util.Set;

public interface CategoryService {
    Set<Category> getAllByNameIn(Set<CategoryName> categoryNames);
}
