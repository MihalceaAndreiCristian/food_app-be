package ro.andreimihalcea.food_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration of CrossOrigin where we specify what methods are allowed
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Value("${allowedApi}")
    private String allowedApi;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization")
                .allowedHeaders("*")
                .allowedOrigins(allowedApi);
    }
}
