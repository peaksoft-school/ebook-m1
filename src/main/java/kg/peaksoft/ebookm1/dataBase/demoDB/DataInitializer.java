package kg.peaksoft.ebookm1.dataBase.demoDB;

import kg.peaksoft.ebookm1.dataBase.entities.security.Role;
import kg.peaksoft.ebookm1.dataBase.entities.security.User;
import kg.peaksoft.ebookm1.dataBase.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository) {
        return args -> {

            //role1
            Role role1 = new Role();
            role1.setName("ROLE_ADMIN");

            //role2
            Role role2 = new Role();
            role2.setName("ROLE_CLIENT");

            //role3
            Role role3 = new Role();
            role3.setName("ROLE_VENDOR");

            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword("$2a$12$Z64d.YOOf45UKDGtvMUFtuBw7hAbXbxhaUjtud8AACeye1vWTOC6K\n");
            admin.setCreated(LocalDateTime.now());
            admin.setRoles(Arrays.asList(role1));

            User vendor = new User();
            vendor.setFirstName("Vendor");
            vendor.setLastName("Vendor");
            vendor.setEmail("vendor@gmail.com");
            vendor.setPassword("$2a$12$nlOTMXn.DhalfwkJK.cj1emE59OSFTDazeoaT/pAC5W11RkczKsp6\n");
            vendor.setPhoneNumber("+797979797");
            vendor.setCreated(LocalDateTime.now());
            vendor.setRoles(Arrays.asList(role3));

            User client = new User();
            client.setFirstName("Client");
            client.setEmail("client@gmail.com");
            client.setPassword("$2a$12$i8aqJXMAuTQ6qUHksXhl9uaThtBNxbsNJypTlrTeqCow4c8IK69uS\n");
            client.setActive(true);
            client.setRoles(Arrays.asList(role2));
            client.setCreated(LocalDateTime.now());

//            System.out.println(userRepository.save(admin));
//            System.out.println(userRepository.save(vendor));
//            System.out.println(userRepository.save(client));
        };
    }
}
