package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.model.dto.binding.AddCommentBindingModel;
import bg.softuni.homefurniture.model.entity.Comment;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.repository.CommentRepository;
import bg.softuni.homefurniture.repository.ProductRepository;
import bg.softuni.homefurniture.repository.UserRepository;
import bg.softuni.homefurniture.service.CommentService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ProductRepository productRepository, UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCommentToProduct(User user, Product product, AddCommentBindingModel addCommentBindingModel) {
        Comment comment = modelMapper.map(addCommentBindingModel, Comment.class);
        comment.setAuthor(user);
        comment.setProduct(product);
        comment.setCreatedOn(LocalDateTime.now());

        // Recalculates average rating of product
        double currentRating = product.getRating();
        int totalComments = product.getComments().size();
        double newRating = ((currentRating * totalComments) + comment.getRating()) / (totalComments + 1);
        product.setRating(newRating);

        commentRepository.save(comment);
        productRepository.save(product);
    }

    @Override
    public List<Comment> orderCommentsByDateDesc(List<Comment> comments) {
        return comments.stream()
                .sorted(Comparator.comparing(Comment::getCreatedOn).reversed())
                .collect(Collectors.toList());
    }
}
