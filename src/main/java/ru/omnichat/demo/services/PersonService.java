package ru.omnichat.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.omnichat.demo.entity.Person;
import ru.omnichat.demo.repositories.PersonRepository;

@Service
@Transactional
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


    public Person save2(Person person) {
        Person p = save3(person);
        p = personRepository.save(p);
        return p;
    }

    private Person save3(Person person) {
        return personRepository.saveAndFlush(person);
    }

}
