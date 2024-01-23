package com.hillel.glovo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.formLogin(form ->
                // loginPage("/login")
                        form.permitAll())
                .authorizeRequests(authorize -> authorize
                        .regexMatchers("/").permitAll()
                        .regexMatchers("/api/v1/orders").hasRole("USER")
//                        .antMatchers( "/api/v1/table/save").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .logout(logout -> logout.permitAll())
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)//resolving HTTP 403 for POST with Basic Authentication
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //user input password in form
        System.out.println(encoder.encode("password"));
        boolean isMatchPassword = encoder.matches("password", "{bcrypt}$2a$10$wsn2Z3ylDAKKeNaSoeMfkeneWlCr36qNw1RyunT3vGAY4ZSUEJub.");
        System.out.println(isMatchPassword);

        UserDetails user =
                User
                        .withUsername("user")
                        .password("{bcrypt}$2a$10$wsn2Z3ylDAKKeNaSoeMfkeneWlCr36qNw1RyunT3vGAY4ZSUEJub.")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
