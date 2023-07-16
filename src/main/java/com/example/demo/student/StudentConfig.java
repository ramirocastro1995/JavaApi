package com.example.demo.student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student ramiro = new Student(
                    "Ramiro",
                    "ramiro@gmail.com",
                    LocalDate.of(1995, Month.MAY,9)

            );
            Student federico = new Student(
                    "Federico",
                    "fede@gmail.com",
                    LocalDate.of(1996, Month.APRIL,20)

            );
            repository.saveAll(List.of(ramiro, federico));

        };
    }
}
