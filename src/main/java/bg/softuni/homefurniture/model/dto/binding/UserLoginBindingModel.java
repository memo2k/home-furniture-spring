package bg.softuni.homefurniture.model.dto.binding;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserLoginBindingModel {
    @Email
    private String email;

    @Length(min = 3, max = 20)
    private String password;

    public UserLoginBindingModel() {

    }
}
