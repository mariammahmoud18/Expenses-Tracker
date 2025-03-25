package com.expensesTracker.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

   @Bean
   //makes sure every user has one role at least, no matter what the role is
    public UserDetailsManager DetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        userDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, TRUE FROM users WHERE username = ?"
        );

        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT u.username,  CONCAT('ROLE_', r.role_name) FROM roles r " +
                        "JOIN user_roles ur ON r.roleid = ur.roleid " +
                        "JOIN users u ON ur.userid = u.userid " +
                        "WHERE u.username = ?"
        );

        return userDetailsManager;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/tracker/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tracker/users/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/tracker/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/tracker/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/tracker/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tracker/expenses").hasRole("ADMIN")
                        .requestMatchers("/tracker/expenses/chart").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tracker/expenses/*").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.GET, "/tracker/expenses/*/users").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.POST, "/tracker/expenses").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.DELETE, "/tracker/expenses/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tracker/roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tracker/roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/tracker/roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/tracker/roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tracker/category").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.GET, "/tracker/category/**").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.DELETE, "/tracker/category/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/tracker/category").hasAnyRole("ADMIN","USER")

        );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();


    }


}
