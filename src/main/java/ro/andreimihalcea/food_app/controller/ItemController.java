package ro.andreimihalcea.food_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.andreimihalcea.food_app.dto.item.ItemDTO;
import ro.andreimihalcea.food_app.service.item.ItemService;
import ro.andreimihalcea.food_app.util.JsonUtil;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private static final String ERROR_MESSAGE = "Wrong use of API.";
    private static final String DELETE_MESSAGE = "Item has been deleted";

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Create API for item.
     *
     * @param itemDTO the input {@link ItemDTO}
     */
    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {
        return ResponseEntity.ok(JsonUtil.objectToJsonString(itemService.createItem(itemDTO)));
    }

    /**
     * Get API for item.
     * If the category is not null, client will receive all items with that category.
     * If the id is not null, client will receive a specific item.
     *
     * @param id       the input {@link Long}
     * @param category the input {@link String}
     */
    @GetMapping
    public ResponseEntity<?> getItems(@RequestParam(required = false) String category,
                                      @RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(JsonUtil.objectToJsonString(itemService.getItemById(id)));
        } else if (category != null) {
            return ResponseEntity.ok(JsonUtil.objectToJsonString(itemService.getAllItemsByCategory(category)));
        }
        return ResponseEntity.ok(JsonUtil.objectToJsonString(ERROR_MESSAGE));
    }

    /**
     * Update API for item.
     *
     * @param id      the input {@link Long}
     * @param itemDTO the input {@link ItemDTO}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable long id, @RequestBody ItemDTO itemDTO) {
        return ResponseEntity.ok(JsonUtil.objectToJsonString(itemService.updateItem(id, itemDTO)));
    }

    /**
     * Delete API for item.
     *
     * @param id the input {@link Long}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok(JsonUtil.objectToJsonString(DELETE_MESSAGE));
    }
}
