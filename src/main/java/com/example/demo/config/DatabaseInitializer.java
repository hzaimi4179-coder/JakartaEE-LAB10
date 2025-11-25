package com.example.demo.config;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DatabaseInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, BCryptPasswordEncoder encoder) {
        return args -> {
            Role adminRole = roleRepo.save(new Role(null, "ROLE_ADMIN"));
            Role userRole = roleRepo.save(new Role(null, "ROLE_USER"));

            User admin = new User(null, "admin", encoder.encode("1234"), true, List.of(adminRole, userRole));
            User user = new User(null, "user", encoder.encode("1111"), true, List.of(userRole));

            userRepo.saveAll(List.of(admin, user));
        };
    }
}