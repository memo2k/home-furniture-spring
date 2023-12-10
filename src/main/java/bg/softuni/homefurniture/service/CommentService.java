package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.dto.binding.AddCommentBindingModel;
import bg.softuni.homefurniture.model.entity.Comment;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.entity.User;

import java.util.List;

public interface CommentService {
    void addCommentToProduct(User user, Product product, AddCommentBindingModel addCommentBindingModel);

    List<Comment> orderCommentsByDateDesc(List<Comment> comments);

    List<Comment> findTopComments();

    void deleteComment(Long id);
}
