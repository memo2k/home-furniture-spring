package bg.softuni.homefurniture.model.dto.binding;

import bg.softuni.homefurniture.validation.anotations.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
