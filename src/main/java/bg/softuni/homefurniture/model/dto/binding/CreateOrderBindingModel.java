package bg.softuni.homefurniture.model.dto.binding;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderBindingModel {
    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private String country;

    private String city;

    private String postalCode;

    private String paymentType;

    public CreateOrderBindingModel() {

    }
}
