package bg.softuni.homefurniture.model.dto.binding;

import bg.softuni.homefurniture.model.enums.CategoryName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddProductBindingModel {
    private String name;

    private String image;

    private String description;

    private BigDecimal price;

    private BigDecimal promotionalPrice;

    private Integer quantity;

    private CategoryName categoryName;

    public AddProductBindingModel() {

    }
}
