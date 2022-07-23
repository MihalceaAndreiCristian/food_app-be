package ro.andreimihalcea.food_app.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.andreimihalcea.food_app.dto.user.UserDTO;

import java.util.List;

/**
 * Class which loads user specific data
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;


    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Locates the user based on the username or email
     *
     * @param username - the input {@link String}
     * @return a User
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userService.getUserByUsername(username);

        log.info("User found in the database");
        return new User(userDTO.getUsername(), userDTO.getPassword(), List.of(new SimpleGrantedAuthority(userDTO.getRole())));
    }

}
