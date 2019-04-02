package ru.omnichat.demo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.omnichat.demo.entity.Person;
import ru.omnichat.demo.services.PersonService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @AllArgsConstructor
    public static class Inserter implements Runnable {

        private Integer number;
        private PersonService personService;

        @Override
        public void run() {
            Person p = personService.save4(number);
            log.info("Person saved {}", p);
        }
    }

    @Bean
    CommandLineRunner runner(final PersonService personService) {

        return args -> {
            ExecutorService executorService = Executors.newFixedThreadPool(200);

            for (int i = 1; i <= 200; i++) {
                Runnable task = new Inserter(i, personService);
                executorService.submit(task);
            }

            executorService.shutdown();
        };
    }

}
