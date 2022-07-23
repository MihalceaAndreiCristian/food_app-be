package ro.andreimihalcea.food_app.service.user;

import ro.andreimihalcea.food_app.dto.user.UserDTO;

import java.util.List;

/**
 * Interface for user service.
 */
public interface UserService {

    /**
     * Gets a user by username.
     *
     * @param username the input {@link String}
     * @return {@link UserDTO}
     */
    UserDTO getUserByUsername(String username);

    /**
     * Gets a specific user by id.
     *
     * @param userId the input {@link Long}
     * @return {@link UserDTO}
     */
    UserDTO getUserById(long userId);

    /**
     * Get all users.
     *
     * @return {@link List<UserDTO>}
     */
    List<UserDTO> getAllUsers();

    /**
     * Create new user.
     *
     * @param newUser the input {@link UserDTO}
     * @return {@link UserDTO}
     */
    UserDTO createUser(UserDTO newUser);

    /**
     * Updates a specific user.
     *
     * @param userId  the input {@link Long}
     * @param userDTO the input {@link UserDTO}
     * @return {@link UserDTO}
     */
    UserDTO updateUser(long userId, UserDTO userDTO);

    /**
     * Delete a specific user by id.
     *
     * @param userId the input {@link Long}
     */
    void deleteUser(long userId);
}
