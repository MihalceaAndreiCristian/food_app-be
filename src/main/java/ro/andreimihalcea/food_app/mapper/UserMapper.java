package ro.andreimihalcea.food_app.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ro.andreimihalcea.food_app.dto.user.UserDTO;
import ro.andreimihalcea.food_app.entity.UserEntity;

/**
 * Converter abstract class used by MapStruct.
 */
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    /**
     * Convert entity to DTO.
     *
     * @param userEntity the input {@link UserEntity}
     * @return {@link UserDTO}
     */
    public abstract UserDTO convertToUserDTO(UserEntity userEntity);

    /**
     * Convert DTO to entity.
     *
     * @param userDTO the input {@link UserDTO}
     * @return {@link UserEntity}
     */
    @InheritInverseConfiguration
    public abstract UserEntity convertToUserEntity(UserDTO userDTO);
}