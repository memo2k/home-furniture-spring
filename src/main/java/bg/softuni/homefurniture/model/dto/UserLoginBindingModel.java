package bg.softuni.homefurniture.model.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class UserLoginBindingModel {
    @Email
    private String email;

    @Length(min = 3, max = 20)
    private String password;

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
}
