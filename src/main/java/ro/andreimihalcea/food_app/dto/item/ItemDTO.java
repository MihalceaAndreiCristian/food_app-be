package ro.andreimihalcea.food_app.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ItemDTO {

    private long id;
    private String name;
    private double price;
    private String size;
    private String category;
}
