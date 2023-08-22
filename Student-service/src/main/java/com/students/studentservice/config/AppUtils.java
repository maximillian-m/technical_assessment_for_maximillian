package com.students.studentservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.students.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class AppUtils {
    private final StudentRepository appUserRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> appUserRepository.findStudentByEmailAddress(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Does Not Exist"));
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

@Bean
    public RestTemplate getRestTemplate(){
    RestTemplate restTemplate = new RestTemplate();


    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());


    restTemplate.getMessageConverters()
            .stream()
            .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
            .findFirst()
            .ifPresent(converter -> ((MappingJackson2HttpMessageConverter) converter).setObjectMapper(objectMapper));

    return restTemplate;
    }
    @Bean
    public ObjectMapper objectMapper(){return new ObjectMapper();}

    @Bean
    public SecurityUtils securityUtils(){
        return new SecurityUtils();}
}
