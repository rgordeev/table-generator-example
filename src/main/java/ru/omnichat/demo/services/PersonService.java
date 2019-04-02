package ru.omnichat.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.omnichat.demo.entity.Person;
import ru.omnichat.demo.repositories.PersonRepository;

@Slf4j
@Service
@Transactional(rollbackFor = PersonService.Canceliator.class)
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save1(Person person) {
        personRepository.save(person);
        return person;
    }

    public Person save4(Integer number) {
        String name = String.format("Ivan %d", number);
        Person p = new Person(name, number);
        personRepository.save(p);
        if (number % 10 == 0) throw new Canceliator();
        return p;
    }


    public Person save2(Person person) {
        Person p = save3(person);
        p = personRepository.save(p);
        return p;
    }

    private Person save3(Person person) {
        return personRepository.saveAndFlush(person);
    }


    public static class Canceliator extends Error {
        public Canceliator() {
        }
    }
}
