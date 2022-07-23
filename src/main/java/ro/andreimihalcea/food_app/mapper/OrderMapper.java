package ro.andreimihalcea.food_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ro.andreimihalcea.food_app.dto.item.ItemDTO;
import ro.andreimihalcea.food_app.dto.order.OrderDTO;
import ro.andreimihalcea.food_app.dto.user.UserDTO;
import ro.andreimihalcea.food_app.entity.ItemEntity;
import ro.andreimihalcea.food_app.entity.OrderEntity;
import ro.andreimihalcea.food_app.entity.UserEntity;
import ro.andreimihalcea.food_app.repository.ItemRepository;

import java.util.List;

/**
 * Converter abstract class used by MapStruct.
 */
@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * Converts an order entity into an order dto.
     *
     * @param entity the input {@link OrderEntity}
     * @return {@link OrderDTO}
     */
    @Mapping(target = "items", expression = "java(getItemsForOrder(entity))")
    @Mapping(target = "orderedBy", expression = "java(getUserDtoFromOrder(entity))")
    public abstract OrderDTO convertToDTO(OrderEntity entity);

    /**
     * Converts the item's entity from oder into dto's.
     *
     * @param entity the input {@link OrderEntity}
     * @return {@link List<ItemDTO>}
     */
    public List<ItemDTO> getItemsForOrder(OrderEntity entity) {
        var itemsFromOrder = itemRepository.findItemsFromOrderId(entity.getId());
        return itemsFromOrder.stream().map(itemMapper::convertToDTO).toList();
    }

    /**
     * Convert the user entity of an order to a user dto.
     *
     * @param entity the input {@link OrderEntity}
     * @return {@link UserDTO}
     */
    public UserDTO getUserDtoFromOrder(OrderEntity entity) {
        return userMapper.convertToUserDTO(entity.getUser());
    }

    /**
     * Converts an order dto into an order entity.
     *
     * @param dto the input {@link OrderDTO}
     * @return {@link OrderEntity}
     */
    @Mapping(target = "items", expression = "java(getItemsEntityFromOrderDTO(dto))")
    @Mapping(target = "user", expression = "java(getUserEntityFromOrderDTO(dto))")
    public abstract OrderEntity convertToEntity(OrderDTO dto);

    /**
     * Converts the list of items dto from order dto into a list of items entity.
     *
     * @param dto the input {@link OrderDTO}
     * @return {@link List<ItemEntity>}
     */
    public List<ItemEntity> getItemsEntityFromOrderDTO(OrderDTO dto) {
        return dto.getItems().stream().map(itemMapper::convertToEntity).toList();
    }

    /**
     * Converts the user dto from order dto into an user entity.
     *
     * @param dto the input {@link OrderDTO}
     * @return {@link UserDTO}
     */
    public UserEntity getUserEntityFromOrderDTO(OrderDTO dto) {
        return userMapper.convertToUserEntity(dto.getOrderedBy());
    }
}
