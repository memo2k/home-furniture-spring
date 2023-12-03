package bg.softuni.homefurniture.service;

import bg.softuni.homefurniture.model.dto.binding.CreateOrderBindingModel;
import bg.softuni.homefurniture.model.dto.view.OrderDetailsViewModel;
import bg.softuni.homefurniture.model.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();

    void createOrder(CreateOrderBindingModel createOrderBindingModel);

    OrderDetailsViewModel getDetails(Long orderId);
}
