package ru.omnichat.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.omnichat.demo.entity.Person;
import ru.omnichat.demo.services.PersonService;

import java.util.Random;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(final PersonService personService) {

        return args -> {
            for (int i = 1; i <= 200; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Person p = new Person(String.format("Ivan %d", new Random().nextInt()));
                        personService.save2(p);
                    }
                }).start();

            }
        };
    }

}
