package bg.softuni.homefurniture.model.dto.binding;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AddCommentBindingModel {
    @NotNull(message = "You must type something.")
    @Length(max = 255, message = "Comment is too long.")
    private String description;

    @NotNull(message = "You must rate the product.")
    private Double rating;

    public AddCommentBindingModel() {

    }
}
