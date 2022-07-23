package ro.andreimihalcea.food_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ro.andreimihalcea.food_app.security_filter.AuthEntryPoint;
import ro.andreimihalcea.food_app.security_filter.AuthenticationFilter;
import ro.andreimihalcea.food_app.security_filter.CustomAuthorizationFilter;
import ro.andreimihalcea.food_app.service.jwt.JwtTokenService;
import ro.andreimihalcea.food_app.service.user.UserDetailsServiceImpl;

import static org.springframework.http.HttpMethod.POST;


/**
 * Configuration class for security
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenService jwtTokenService;
    private final CustomAuthorizationFilter customAuthorizationFilter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService,
                          JwtTokenService jwtTokenService,
                          CustomAuthorizationFilter customAuthorizationFilter,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
        this.customAuthorizationFilter = customAuthorizationFilter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Configures user credentials and storing them in database
     *
     * @param auth - the input {@link AuthenticationManagerBuilder}
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * Grants permission to all users to access the endpoint /health and /login
     *
     * @param http - the input {@link HttpSecurity}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(new AuthEntryPoint());
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers(POST,"/users").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new AuthenticationFilter(authenticationManagerBean(), jwtTokenService));
        http.addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }
}