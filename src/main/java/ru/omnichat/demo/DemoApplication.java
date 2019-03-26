package ru.omnichat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.omnichat.demo.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.function.Consumer;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @FunctionalInterface
    protected interface JPATransactionVoidFunction extends Consumer<EntityManager> {
        default void beforeTransactionCompletion() {

        }

        default void afterTransactionCompletion() {

        }
    }

    protected void doInJPA(JPATransactionVoidFunction function) {
        EntityManager entityManager = null;
        EntityTransaction txn = null;
        try {
            entityManager = emf.createEntityManager();
            function.beforeTransactionCompletion();
            txn = entityManager.getTransaction();
            txn.begin();
            function.accept(entityManager);
            txn.commit();
        } catch (RuntimeException e) {
            if ( txn != null && txn.isActive()) txn.rollback();
            throw e;
        } finally {
            function.afterTransactionCompletion();
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Bean
    @Transactional
    CommandLineRunner runner() {

        return args -> doInJPA(entityManager -> {
            for ( int i = 1; i <= 3; i++ ) {
                entityManager.persist(
                        new Person(
                                String.format(
                                        "Russian Ivan %d", i
                                )
                        )
                );
            }
        });
    }
}
