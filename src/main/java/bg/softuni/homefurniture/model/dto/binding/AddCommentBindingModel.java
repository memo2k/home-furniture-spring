package bg.softuni.homefurniture.model.dto.binding;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AddCommentBindingModel {
    @NotBlank(message = "You must type something.")
    @Length(max = 1000, message = "Comment is too long.")
    private String description;

    @Valid
    @NotNull(message = "You must rate the product.")
    private Double rating;

    public AddCommentBindingModel() {

    }
}
