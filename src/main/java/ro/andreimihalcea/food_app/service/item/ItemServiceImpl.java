package ro.andreimihalcea.food_app.service.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.andreimihalcea.food_app.dto.item.ItemDTO;
import ro.andreimihalcea.food_app.entity.ItemEntity;
import ro.andreimihalcea.food_app.enums.ItemCategory;
import ro.andreimihalcea.food_app.enums.ItemSize;
import ro.andreimihalcea.food_app.exception.item.ItemAlreadySavedException;
import ro.andreimihalcea.food_app.exception.item.ItemNotFoundException;
import ro.andreimihalcea.food_app.mapper.ItemMapper;
import ro.andreimihalcea.food_app.repository.ItemRepository;

import java.util.List;

/**
 * Implementation class for item service interface.
 */
@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    /**
     * Method that creates a new item.
     *
     * @param item the input {@link ItemDTO}
     * @return {@link ItemDTO}
     */
    @Override
    @Transactional
    public ItemDTO createItem(ItemDTO item) {
        var itemFoundInDatabase = itemRepository
                .findByNameAndPriceAndSize(item.getName(), item.getPrice(), item.getSize());
        if (itemFoundInDatabase.isPresent()) {
            throw new ItemAlreadySavedException("Item has been already saved.");
        }
        var itemToSave = itemMapper.convertToEntity(item);
        var itemSaved = itemRepository.save(itemToSave);

        return itemMapper.convertToDTO(itemSaved);
    }

    /**
     * Gets a specific item.
     *
     * @param itemId the input {@link Long}
     * @return {@link ItemDTO}
     */
    @Override
    public ItemDTO getItemById(long itemId) {
        var itemFound = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item not found!"));

        return itemMapper.convertToDTO(itemFound);
    }

    /**
     * Gets all items from a specific category.
     *
     * @param category the input {@link String}
     * @return {@link List<ItemDTO>}
     */
    @Override
    public List<ItemDTO> getAllItemsByCategory(String category) {
        var items = itemRepository.findByCategory(category);

        return items.stream().map(itemMapper::convertToDTO).toList();
    }

    /**
     * Modify a specific item.
     *
     * @param id      the input {@link Long}
     * @param itemDTO the input {@link ItemDTO}
     * @return {@link ItemDTO}
     */
    @Override
    @Transactional
    public ItemDTO updateItem(long id, ItemDTO itemDTO) {
        var itemFound = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found."));
        var itemUpdated = setNewValues(itemFound,itemDTO);
        var itemSaved = itemRepository.save(itemUpdated);

        return itemMapper.convertToDTO(itemSaved);
    }

    /**
     * Delete a specific item.
     *
     * @param id the input {@link Long}
     */
    @Override
    @Transactional
    public void deleteItem(long id) {
        var itemFound = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found."));
        itemRepository.deleteById(id);
    }

    /**
     * Method that sets all values to entity from dto.
     *
     * @param entity the input {@link ItemEntity}
     * @param dto    the input {@link ItemDTO}
     * @return {@link ItemEntity}
     */
    private ItemEntity setNewValues(ItemEntity entity, ItemDTO dto) {
        entity.setCategory(dto.getCategory() != null ?
                ItemCategory.valueOf(dto.getCategory().toUpperCase()) : entity.getCategory());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setSize(dto.getSize() != null ?
                ItemSize.valueOf(dto.getSize().toUpperCase()) : entity.getSize());
        return entity;
    }
}
