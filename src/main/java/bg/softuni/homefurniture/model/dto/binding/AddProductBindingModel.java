package bg.softuni.homefurniture.model.dto.binding;

import bg.softuni.homefurniture.model.enums.CategoryName;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@Setter
public class AddProductBindingModel {
    @Length(min = 3, message = "Product name must be at least 3 characters long.")
    @NotNull(message = "Name is required.")
    private String name;

    @Pattern(regexp = "(https?:)?//?[^\\'\"\"<>]+?\\.(jpg|jpeg|gif|png)", message = "Image URL is not valid.")
    @NotNull(message = "Image is required.")
    private String imageUrl;

    @Length(max = 255, message = "Description must be no more than 255 characters long.")
    @NotNull(message = "Description is required.")
    private String description;

    @Positive(message = "Price must be a positive value")
    @NotNull(message = "Price is required.")
    private Double price;

    @Positive(message = "Promotional price must be a positive value")
    private Double promotionalPrice;

    @Min(value = 0, message = "Quantity must be a positive number.")
    @NotNull(message = "Quantity is required.")
    private Integer quantity;

    @NotNull(message = "Category is required.")
    private CategoryName categoryName;

    public AddProductBindingModel() {

    }
}
