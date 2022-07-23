package ro.andreimihalcea.food_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.andreimihalcea.food_app.dto.order.OrderDTO;
import ro.andreimihalcea.food_app.service.order.OrderService;
import ro.andreimihalcea.food_app.util.JsonUtil;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Create API for orders.
     *
     * @param newOrder the input {@link OrderDTO}
     */
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO newOrder) {
        return ResponseEntity.ok(JsonUtil.objectToJsonString(orderService.createOrder(newOrder)));
    }

    /**
     * Get API for orders.
     *
     * @param id the input {@link Long}
     */
    @GetMapping
    public ResponseEntity<?> getOrder(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(JsonUtil.objectToJsonString(orderService.getOrderById(id)));
        }
        return ResponseEntity.ok(JsonUtil.objectToJsonString(orderService.getAllOrders()));
    }

    @PatchMapping
    public ResponseEntity<?> updateOrder(@PathVariable long id, @RequestBody OrderDTO updatedOrder){
        return ResponseEntity.ok(JsonUtil.objectToJsonString(orderService.updateOrder(id,updatedOrder)));
    }
}
