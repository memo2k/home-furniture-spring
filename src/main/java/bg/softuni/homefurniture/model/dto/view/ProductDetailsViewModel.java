package bg.softuni.homefurniture.model.dto.view;

import bg.softuni.homefurniture.model.entity.Category;
import bg.softuni.homefurniture.model.entity.Comment;
import bg.softuni.homefurniture.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductDetailsViewModel {
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private BigDecimal price;

    private BigDecimal promotionalPrice;

    private Integer quantity;

    private Double rating;

    private Category category;

    private List<Comment> comments;
}
