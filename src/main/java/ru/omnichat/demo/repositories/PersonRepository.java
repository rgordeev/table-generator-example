package ru.omnichat.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.omnichat.demo.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}