package bg.softuni.homefurniture.model.dto.binding;

import bg.softuni.homefurniture.validation.anotations.PasswordMatch;
import bg.softuni.homefurniture.validation.anotations.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@PasswordMatch
public class UserRegisterBindingModel {
    @Length(min = 3, max = 20, message = "User name must be between 3 and 20 characters long.")
    @NotNull
    private String username;

    @Email(message = "Email must be valid.")
    @UniqueEmail(message = "Email is not available.")
    @NotNull
    private String email;

    @Length(min = 4, message = "Password must be at least 4 characters long.")
    private String password;

    @Length(min = 4)
    private String confirmPassword;
}
