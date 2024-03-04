package com.slizokav.JwtAuthorizationApplication.configuration;

import com.slizokav.JwtAuthorizationApplication.security.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringConfiguration {


    private PersonDetailsService personDetailsService;

    @Autowired
    public SpringConfiguration(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((request) -> request
                        .requestMatchers("/", "/login", "/registration").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((login) -> login
                        .loginPage("/login").loginProcessingUrl("/auth").defaultSuccessUrl("/admin")
                )
                .logout((logout) -> logout.permitAll().logoutUrl("/logout").logoutSuccessUrl("/"));

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
