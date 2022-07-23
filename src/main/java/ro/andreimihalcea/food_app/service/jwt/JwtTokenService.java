package ro.andreimihalcea.food_app.service.jwt;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {

    /**
     * Generates the jwtToken considering an expiration time and a secret key defined in application.properties
     *
     * @param authentication - the input {@link Authentication}
     * @return a String
     */
    String generateJwtToken(Authentication authentication);

}