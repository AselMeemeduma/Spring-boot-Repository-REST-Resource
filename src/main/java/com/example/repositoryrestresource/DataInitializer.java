package com.example.repositoryrestresource;

import com.example.repositoryrestresource.entity.Address;
import com.example.repositoryrestresource.entity.User;
import com.example.repositoryrestresource.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        Address address1 = new Address("123 Main St", "Springfield", "IL", "62701", "USA");
        User user1 = new User("John", "Doe", 30, List.of(address1));

        Address address2 = new Address("456 Oak Ave", "Shelbyville", "TN", "37160", "USA");
        User user2 = new User("Jane", "Smith", 25, List.of(address2));

        userRepository.save(user1);
        userRepository.save(user2);
        log.info("Sample users saved to H2 database.");
    }
}

