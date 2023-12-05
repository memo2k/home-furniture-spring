package bg.softuni.homefurniture.model.dto.binding;

import bg.softuni.homefurniture.model.enums.CategoryName;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@Setter
public class AddProductBindingModel {
    @Length(min = 3, message = "Product name must be at least 3 characters long.")
    @NotNull
    private String name;

    @Pattern(regexp = "(?i)\\.(jpg|jpeg|png|gif|bmp)$", message = "Image URL is not valid.")
    @NotNull
    private String imageUrl;

    @NotNull
    @Length(max = 255, message = "Description must be no more than 255 characters long.")
    private String description;

    @DecimalMin(value = "0.0", message = "Price must be a positive number.")
    @NotNull
    private BigDecimal price;

    @DecimalMin(value = "0.0", message = "Promotional price must be a positive number.")
    @NotNull
    private BigDecimal promotionalPrice;

    @Min(value = 0, message = "Quantity must be a positive number.")
    @NotNull
    private Integer quantity;

    @NotNull(message = "You must choose a category.")
    private CategoryName categoryName;

    public AddProductBindingModel() {

    }
}
