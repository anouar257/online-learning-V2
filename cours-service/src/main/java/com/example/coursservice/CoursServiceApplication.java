package com.example.coursservice;

import com.example.coursservice.entities.Cours;
import com.example.coursservice.repositories.CoursRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CoursServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CoursRepository coursRepository) {
        return args -> {
            coursRepository.save(new Cours(null, "Java Basics", "Introduction to Java"));
            coursRepository.save(new Cours(null, "Spring Boot", "Mastering Spring Boot"));
            coursRepository.save(new Cours(null, "Microservices", "Building Microservices"));
            coursRepository.findAll().forEach(System.out::println);
        };
    }
}
