package ru.savelevvn.SalaryPeople.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/index").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/users")
                        .hasRole("ADMIN")
                        .requestMatchers("/list")
                        .hasRole("ADMIN")
                        .requestMatchers("/addPeopleForm")
                        .hasRole("ADMIN")
                        .requestMatchers("/savePeople")
                        .hasRole("ADMIN")
                        .requestMatchers("/showUpdateForm")
                        .hasRole("ADMIN")
                        .requestMatchers("/deleteStudent")
                        .hasRole("ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/list", true)
                        .failureUrl("/login?error=true")
                        .permitAll()

                ).logout(logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                        );
        return httpSecurity.build();
    }
}
