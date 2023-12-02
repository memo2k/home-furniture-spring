package bg.softuni.homefurniture.model.dto.binding;

public class AddCommentBindingModel {
    private String description;

    private Double rating;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
