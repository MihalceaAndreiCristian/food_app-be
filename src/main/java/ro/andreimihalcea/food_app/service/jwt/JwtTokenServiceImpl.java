package ro.andreimihalcea.food_app.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.andreimihalcea.food_app.dto.user.UserDTO;
import ro.andreimihalcea.food_app.service.user.UserService;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Data
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration.ms}")
    private long jwtExpirationMs;

    @Value("${bearer.token.prefix}")
    private String bearer;
    @Autowired
    private UserService userService;

    /**
     * Generates the jwtToken considering an expiration time and a secret key defined in application.properties
     *
     * @param authentication - the input {@link Authentication}
     * @return a String
     */
    @Override
    @Transactional
    public String generateJwtToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        UserDTO userDTO = userService.getUserByUsername(user.getUsername());

        return JWT.create()
                .withSubject(user.getUsername())
                .withPayload(getPayloadForUser(userDTO))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .sign(algorithm);
    }

    /**
     * Method used to put details in token.
     *
     * @param user the input {@link UserDTO}
     * @return {@link Map<String,String>}
     */
    private Map<String, String> getPayloadForUser(UserDTO user) {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(user.getId()));
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("address", user.getAddress());
        map.put("role", user.getRole());
        return map;
    }
}