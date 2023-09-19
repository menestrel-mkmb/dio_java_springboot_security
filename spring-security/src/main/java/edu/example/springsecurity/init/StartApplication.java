package edu.example.springsecurity.init;

import edu.example.springsecurity.model.User;
import edu.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepo;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = userRepo.findByUsername("admin");
        if(user == null) {
            user = new User();

            user.setName("ADMIN");
            user.setUsername("admin");
            user.setPassword("plaintext");
            user.getRoles().add("MANAGERS");

            userRepo.save(user);
        }
        user = userRepo.findByUsername("user");
        if(user == null) {
            user = new User();
            user.setName("USER");
            user.setUsername("user");
            user.setPassword("plaintext");
            user.getRoles().add("USERS");

            userRepo.save(user);
        }
    }
}
