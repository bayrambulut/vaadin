package org.test.vaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.test.vaadin.model.Employee;
import org.test.vaadin.repository.EmployeeRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /*
    @Bean
    public CommandLineRunner loadData(EmployeeRepository repository) {
        return (args) -> {
            repository.save(new Employee("Bill", "Gates"));
            repository.save(new Employee("Mark", "Zuckerberg"));
            repository.save(new Employee("Sundar", "Pichai"));
            repository.save(new Employee("Jeff", "Bezos"));
        };
    }
    */
}