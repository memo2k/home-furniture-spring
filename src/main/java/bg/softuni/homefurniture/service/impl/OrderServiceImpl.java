package bg.softuni.homefurniture.service.impl;

import bg.softuni.homefurniture.model.dto.binding.CreateOrderBindingModel;
import bg.softuni.homefurniture.model.dto.view.OrderDetailsViewModel;
import bg.softuni.homefurniture.model.entity.Cart;
import bg.softuni.homefurniture.model.entity.Order;
import bg.softuni.homefurniture.model.entity.Product;
import bg.softuni.homefurniture.model.entity.User;
import bg.softuni.homefurniture.repository.CartRepository;
import bg.softuni.homefurniture.repository.OrderRepository;
import bg.softuni.homefurniture.repository.ProductRepository;
import bg.softuni.homefurniture.repository.UserRepository;
import bg.softuni.homefurniture.service.OrderService;
import bg.softuni.homefurniture.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CartRepository cartRepository, ProductRepository productRepository, ModelMapper modelMapper, LoggedUser loggedUser, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "createdOn"));
    }

    @Override
    public void createOrder(CreateOrderBindingModel createOrderBindingModel) {
        Order order = modelMapper.map(createOrderBindingModel, Order.class);
        User user = userRepository.findByEmail(loggedUser.getEmail());
        Cart cart = user.getCart();

        order.setUser(user);
        order.setProducts(cart.getProducts());
        order.setTotalPrice(cart.getTotalPrice());
        order.setCreatedOn(LocalDateTime.now());

        cart.setTotalPrice(0.0);

        for (Product product : cart.getProducts()) {
            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
        }

        orderRepository.save(order);
        cartRepository.save(cart);
    }

    @Override
    public OrderDetailsViewModel getDetails(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
//                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        return modelMapper.map(order, OrderDetailsViewModel.class);
    }
}
