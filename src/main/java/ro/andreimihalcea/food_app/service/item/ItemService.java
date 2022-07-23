package ro.andreimihalcea.food_app.service.item;

import ro.andreimihalcea.food_app.dto.item.ItemDTO;

import java.util.List;

/**
 * Service interface for items.
 */
public interface ItemService {

    /**
     * Method that creates a new item.
     *
     * @param item the input {@link ItemDTO}
     * @return {@link ItemDTO}
     */
    ItemDTO createItem(ItemDTO item);

    /**
     * Gets a specific item.
     *
     * @param itemId the input {@link Long}
     * @return {@link ItemDTO}
     */
    ItemDTO getItemById(long itemId);

    /**
     * Gets all items from a specific category.
     *
     * @param category the input {@link String}
     * @return {@link List<ItemDTO>}
     */
    List<ItemDTO> getAllItemsByCategory(String category);

    /**
     * Modify a specific item.
     *
     * @param id      the input {@link Long}
     * @param itemDTO the input {@link ItemDTO}
     * @return {@link ItemDTO}
     */
    ItemDTO updateItem(long id, ItemDTO itemDTO);

    /**
     * Delete a specific item.
     *
     * @param id the input {@link Long}
     */
    void deleteItem(long id);
}
