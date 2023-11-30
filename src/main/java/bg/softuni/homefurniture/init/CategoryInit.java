package bg.softuni.homefurniture.init;

import bg.softuni.homefurniture.model.entity.Category;
import bg.softuni.homefurniture.model.enums.CategoryName;
import bg.softuni.homefurniture.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CategoryInit implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public CategoryInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean hasCategories = categoryRepository.count() > 0;

        if (!hasCategories) {
            List<Category> categories = new ArrayList<>();

            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        Category category = new Category();
                        category.setName(categoryName);
                        categories.add(category);
                    });

            categoryRepository.saveAll(categories);
        }
    }
}
