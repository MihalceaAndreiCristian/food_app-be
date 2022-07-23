package ro.andreimihalcea.food_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.andreimihalcea.food_app.dto.user.UserDTO;
import ro.andreimihalcea.food_app.service.user.UserService;
import ro.andreimihalcea.food_app.util.JsonUtil;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * API for create new users.
     *
     * @param newUser the input {@link UserDTO}
     * @return {@link UserDTO}
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO newUser) {
        return ResponseEntity.ok(JsonUtil.objectToJsonString(userService.createUser(newUser)));
    }

    /**
     * API for get users.
     * If request is with query param will return a specific user.
     * Otherwise will get all users.
     *
     * @param id the input {@link Long}
     * @return {@link UserDTO} / {@link List<UserDTO>}
     */
    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(JsonUtil.objectToJsonString(userService.getUserById(id)));
        }
        return ResponseEntity.ok(JsonUtil.objectToJsonString(userService.getAllUsers()));
    }

    /**
     * API for update user.
     *
     * @param id      the input {@link Long}
     * @param userDTO the input {@link UserDTO}
     * @return {@link UserDTO}
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(JsonUtil.objectToJsonString(userService.updateUser(id, userDTO)));
    }

    /**
     * API for delete user.
     *
     * @param id the input {@link Long}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User has been deleted with success.");
    }
}

