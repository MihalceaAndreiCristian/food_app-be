package ro.andreimihalcea.food_app.service.order;

import ro.andreimihalcea.food_app.dto.order.OrderDTO;

import java.util.List;

/**
 * Interface for order service.
 */
public interface OrderService {

    /**
     * Create new order.
     *
     * @param newOrder the input {@link OrderDTO}
     * @return {@link OrderDTO}
     */
    OrderDTO createOrder(OrderDTO newOrder);

    /**
     * Gets a specific order by id.
     *
     * @param id the input {@link Long}
     * @return {@link OrderDTO}
     */
    OrderDTO getOrderById(long id);

    /**
     * Retrieve a list of all orders.
     *
     * @return {@link List<OrderDTO>}
     */
    List<OrderDTO> getAllOrders();

    /**
     * Update a specific order by id.
     *
     * @param id       the input {@link Long}
     * @param orderDTO the input {@link OrderDTO}
     * @return {@link OrderDTO}
     */
    OrderDTO updateOrder(long id, OrderDTO orderDTO);

}