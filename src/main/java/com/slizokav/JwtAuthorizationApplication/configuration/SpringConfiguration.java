package com.slizokav.JwtAuthorizationApplication.configuration;

import com.slizokav.JwtAuthorizationApplication.services.PersonService;
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


    private PersonService personService;

    @Autowired
    public SpringConfiguration(PersonService personService) {
        this.personService = personService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((request) -> request
                        .requestMatchers("/", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((login) -> login
                        .loginPage("/login").loginProcessingUrl("/auth").defaultSuccessUrl("/admin")
                )
                .logout((logout) -> logout.permitAll().logoutUrl("/logout").logoutSuccessUrl("/"));

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(personService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
