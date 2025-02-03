package com.prueba.eduardo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.prueba.eduardo.component.JwtFilterComponent;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private JwtFilterComponent jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
	         // Permitir acceso a Swagger sin autenticación
            .antMatchers("/api/swagger-ui.html", "/api/swagger-resources/**", "/api/v2/api-docs", "/api/webjars/**", "/api/configuration/**","/v1/login", "/v1/user")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); 

        return http.build();
    }
}
