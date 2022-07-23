package ro.andreimihalcea.food_app.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ro.andreimihalcea.food_app.dto.item.ItemDTO;
import ro.andreimihalcea.food_app.dto.user.UserDTO;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderDTO {

    private long id;
    private double price;
    private int numberOfProducts;
    private UserDTO orderedBy;
    private List<ItemDTO> items;
}
