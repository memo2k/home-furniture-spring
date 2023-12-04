package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.model.entity.Category;
import bg.softuni.homefurniture.model.enums.CategoryName;
import bg.softuni.homefurniture.repository.CategoryRepository;
import bg.softuni.homefurniture.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getAllByNameIn(Set<CategoryName> categoryNames) {
        return categoryRepository.getAllByNameIn(categoryNames);
    }
}
