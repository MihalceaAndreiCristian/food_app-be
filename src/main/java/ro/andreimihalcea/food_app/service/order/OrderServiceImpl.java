package ro.andreimihalcea.food_app.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.andreimihalcea.food_app.dto.item.ItemDTO;
import ro.andreimihalcea.food_app.dto.order.OrderDTO;
import ro.andreimihalcea.food_app.entity.OrderEntity;
import ro.andreimihalcea.food_app.exception.order.OrderFailedException;
import ro.andreimihalcea.food_app.mapper.ItemMapper;
import ro.andreimihalcea.food_app.mapper.OrderMapper;
import ro.andreimihalcea.food_app.mapper.UserMapper;
import ro.andreimihalcea.food_app.repository.OrderRepository;
import ro.andreimihalcea.food_app.service.user.UserService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            ItemMapper itemMapper,
                            UserService userService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.itemMapper = itemMapper;
        this.userService = userService;
    }

    /**
     * Create new order.
     *
     * @param newOrder the input {@link OrderDTO}
     * @return {@link OrderDTO}
     */
    @Override
    public OrderDTO createOrder(OrderDTO newOrder) {
        newOrder.setPrice(0);
        newOrder.setNumberOfProducts(0);
        var orderToSave = orderMapper.convertToEntity(newOrder);
        orderToSave.setPrice(newOrder.getItems().stream().mapToDouble(ItemDTO::getPrice).sum());
        orderToSave.setNumberOfProducts(newOrder.getItems().size());
        var orderSaved = orderRepository.save(orderToSave);

        return orderMapper.convertToDTO(orderSaved);
    }

    /**
     * Gets a specific order by id.
     *
     * @param id the input {@link Long}
     * @return {@link OrderDTO}
     */
    @Override
    public OrderDTO getOrderById(long id) {
        var orderFound = orderRepository.findById(id)
                .orElseThrow(() -> new OrderFailedException("Order not found."));

        return orderMapper.convertToDTO(orderFound);
    }

    /**
     * Retrieve a list of all orders.
     *
     * @return {@link List<OrderDTO>}
     */
    @Override
    public List<OrderDTO> getAllOrders() {
        PageRequest thirtyElements = PageRequest.of(0, 30, Sort.by("price").descending());
        var orders = orderRepository.findAll(thirtyElements);
        return orders.map(orderMapper::convertToDTO).stream().toList();
    }

    /**
     * Update a specific order by id.
     *
     * @param id       the input {@link Long}
     * @param orderToUpdate the input {@link OrderDTO}
     * @return {@link OrderDTO}
     */
    @Override
    public OrderDTO updateOrder(long id, OrderDTO orderToUpdate) {
        var orderFound = orderRepository.findById(id)
                .orElseThrow(() -> new OrderFailedException("Order not found."));
        var orderUpdated = setValuesOnOrderFound(orderMapper.convertToDTO(orderFound),orderToUpdate);
        var orderEntityUpdated = orderMapper.convertToEntity(orderUpdated);
        var orderToSave = orderRepository.save(orderEntityUpdated);

        return orderMapper.convertToDTO(orderToSave);
    }

    /**
     * Method that sets new values and check if any changes has major impact.
     *
     * @param dtoFromEntity the input {@link OrderDTO}
     * @param dtoForUpdate  the input {@link OrderDTO}
     * @return {@link OrderDTO}
     */
    private OrderDTO setValuesOnOrderFound(OrderDTO dtoFromEntity, OrderDTO dtoForUpdate) {
        if (dtoForUpdate.getItems().size() < 1) {
            throw new OrderFailedException("You can't make an order without items.");
        }
        if (!dtoFromEntity.getItems().equals(dtoForUpdate.getItems())) {
            dtoFromEntity.setItems(dtoForUpdate.getItems());
        }
        dtoFromEntity.setNumberOfProducts(dtoForUpdate.getItems().size());
        dtoFromEntity.setPrice(dtoForUpdate.getItems().stream().mapToDouble(ItemDTO::getPrice).sum());
        var orderWasMadeBy = userService.getUserById(dtoForUpdate.getOrderedBy().getId());
        if (!orderWasMadeBy.equals(dtoForUpdate.getOrderedBy())) {
            dtoFromEntity.setOrderedBy(orderWasMadeBy);
        }
        return dtoFromEntity;
    }
}