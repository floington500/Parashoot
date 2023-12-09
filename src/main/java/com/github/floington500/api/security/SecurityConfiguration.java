package com.github.floington500.api.security;

import com.github.floington500.api.security.filter.AuthenticationFilter;
import com.github.floington500.api.security.filter.ResourceFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationFilter authenticationFilter;
    private final ResourceFilter resourceFilter;

    /**
     * Authenticates users based on what endpoint they are accessing and the authorization they provide.
     *
     * <p>
     *     Requests that access files, or any status are all permitted.
     * </p>
     *
     * <p>
     *     The client is expected to add a valid header for authentication when uploading a file.
     * </p>
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)                              // for receiving POST requests
                .addFilterBefore(resourceFilter, FilterSecurityInterceptor.class)   // add CORS headers for 3rd parties
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/files/**", "/upload/status")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/files/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.PUT, "/files/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/upload/**")
                        .authenticated()
                )
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
