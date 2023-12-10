package bg.softuni.homefurniture.model.dto.binding;

import bg.softuni.homefurniture.validation.anotations.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class EditUserBindingModel {
    private Long id;

    @Length(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
    @NotNull
    private String username;

    @Email(message = "Email must be valid.")
    @UniqueEmail(message = "Email is not available.")
    @NotNull
    private String email;
}
