package ro.andreimihalcea.food_app.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.andreimihalcea.food_app.dto.user.UserDTO;
import ro.andreimihalcea.food_app.entity.UserEntity;
import ro.andreimihalcea.food_app.exception.user.UserNotFoundException;
import ro.andreimihalcea.food_app.exception.user.UsernameHasBeenTakenException;
import ro.andreimihalcea.food_app.mapper.UserMapper;
import ro.andreimihalcea.food_app.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static ro.andreimihalcea.food_app.enums.AppRole.USER;

/**
 * Implementation class for user service.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Gets a user by username.
     *
     * @param username the input {@link String}
     * @return {@link UserDTO}
     */
    @Override
    public UserDTO getUserByUsername(String username) {
        var userFound = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found by username: " + username));
        return userMapper.convertToUserDTO(userFound);
    }

    /**
     * Gets a specific user by id.
     *
     * @param userId the input {@link Long}
     * @return {@link UserDTO}
     */
    @Override
    public UserDTO getUserById(long userId) {
        var userFound = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        return userMapper.convertToUserDTO(userFound);
    }

    /**
     * Get all users.
     *
     * @return {@link List<UserDTO>}
     */
    @Override
    public List<UserDTO> getAllUsers() {
        var users = userRepository.findAll();
        return users.stream().map(userMapper::convertToUserDTO).toList();
    }

    /**
     * Create new user.
     *
     * @param newUser the input {@link UserDTO}
     * @return {@link UserDTO}
     */
    @Override
    @Transactional
    public UserDTO createUser(UserDTO newUser) {
        newUser.setRole(null);
        var userFound = userRepository.findByUsername(newUser.getUsername());
        if (userFound.isPresent()) {
            throw new UsernameHasBeenTakenException("Username " + newUser.getUsername() + " has been taken.");
        }
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        var userToSave = userMapper.convertToUserEntity(newUser);
        userToSave.setRole(USER);
        userToSave.setCreatedAt(LocalDate.now());
        var userSaved = userRepository.save(userToSave);
        var userToReturn = userMapper.convertToUserDTO(userSaved);
        userToReturn.setPassword(null);

        return userToReturn;
    }

    /**
     * Updates a specific user.
     *
     * @param userId  the input {@link Long}
     * @param userDTO the input {@link UserDTO}
     * @return {@link UserDTO}
     */
    @Override
    @Transactional
    public UserDTO updateUser(long userId, UserDTO userDTO) {
        var userFound = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        var userUpdated = setUserUpdated(userFound, userDTO);
        var userSaved = userRepository.save(userUpdated);

        return userMapper.convertToUserDTO(userSaved);
    }

    /**
     * Delete a specific user by id.
     *
     * @param userId the input {@link Long}
     */
    @Override
    public void deleteUser(long userId) {
        var userFound = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        userRepository.delete(userFound);
    }

    /**
     * This method sets fields for entity.
     *
     * @param userFound   the input {@link UserEntity}
     * @param userUpdated the input {@link UserDTO}
     * @return {@link UserEntity}
     */
    private UserEntity setUserUpdated(UserEntity userFound, UserDTO userUpdated) {
        userFound.setAddress(userUpdated.getAddress());
        userFound.setLastName(userUpdated.getLastName());
        userFound.setFirstName(userUpdated.getFirstName());
        return userFound;
    }
}
