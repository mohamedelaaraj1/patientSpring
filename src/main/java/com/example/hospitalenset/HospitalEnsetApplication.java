package com.example.hospitalenset;

import com.example.hospitalenset.entities.Patient;
import com.example.hospitalenset.repository.PatientRepository;
import com.example.hospitalenset.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class HospitalEnsetApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(HospitalEnsetApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        patientRepository.save(new Patient(null,"MOHAMED",new Date(),false,12 ));
//        patientRepository.save(new Patient(null,"ANAS",new Date(),false,50 ));
//        patientRepository.save(new Patient(null,"MERYEM",new Date(),true,20 ));
    }

    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        PasswordEncoder passwordEncoder = passwordEncoder();
     return args -> {
         UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user1");
         if (u1==null){
         jdbcUserDetailsManager.createUser(
                 User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build()
         );}
         UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user2");
         if (u2==null){
         jdbcUserDetailsManager.createUser(
                 User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build()
         );}
         UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("admin");
         if (u3==null){
         jdbcUserDetailsManager.createUser(
                 User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
         );}

     };
    }

    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1","1234","user1@gmail.com","1234");
            accountService.addNewUser("user2","1234","user2@gmail.com","1234");
            accountService.addNewUser("admin","1234","admin@gmail.com","1234");
            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("admin","ADMIN");


        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
