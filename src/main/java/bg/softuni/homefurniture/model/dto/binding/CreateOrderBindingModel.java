package bg.softuni.homefurniture.model.dto.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderBindingModel {
    @NotBlank(message = "First name is required.")
    @Size(max = 50, message = "First name is too long.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 50, message = "Last name is too long.")
    private String lastName;

    @NotBlank(message = "Address is required.")
    @Size(max = 255, message = "Address is too long.")
    private String address;

    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number.")
    private String phoneNumber;

    @NotBlank(message = "Country is required.")
    private String country;

    @NotBlank(message = "City is required.")
    private String city;

    @NotBlank(message = "Postal code is required.")
    private String postalCode;

    private String paymentType;

    public CreateOrderBindingModel() {

    }
}
