package bg.softuni.homefurniture.model.dto.binding;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCommentBindingModel {
    private String description;

    private Double rating;

    public AddCommentBindingModel() {

    }
}
