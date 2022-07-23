package ro.andreimihalcea.food_app.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ro.andreimihalcea.food_app.dto.item.ItemDTO;
import ro.andreimihalcea.food_app.entity.ItemEntity;

/**
 * Converter abstract class used by MapStruct.
 */
@Mapper(componentModel = "spring")
public interface ItemMapper {

    /**
     * Convert entity to dto.
     *
     * @param entity the input {@link ItemEntity}
     * @return {@link ItemDTO}
     */
    ItemDTO convertToDTO(ItemEntity entity);

    /**
     * Convert dto to entity.
     *
     * @param dto the input {@link ItemDTO}
     * @return {@link ItemDTO}
     */
    @InheritInverseConfiguration
    ItemEntity convertToEntity(ItemDTO dto);
}
