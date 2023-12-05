package bg.softuni.homefurniture.model.dto.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductViewModel {
    private Long id;

    private String imageUrl;

    private String name;

    private Double rating;

    private Double price;

    private Double promotionalPrice;

    private Integer quantity;
}
