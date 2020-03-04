package com.freedom.noteservice;

import com.freedom.noteservice.entities.User;
import com.freedom.noteservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
public class NoteServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NoteServiceApplication.class, args);
    }

    @Bean
    @Autowired
    public CommandLineRunner demoData(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("james@gosling.com", new BCryptPasswordEncoder().encode("developer")));
            userRepository.save(new User("joshua@bloch.com", new BCryptPasswordEncoder().encode("developer")));
        };
    }
}
