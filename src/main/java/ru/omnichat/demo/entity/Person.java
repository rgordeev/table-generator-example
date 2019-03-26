package ru.omnichat.demo.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import static javax.persistence.GenerationType.TABLE;

@Slf4j
@EqualsAndHashCode
@Entity(name = "Person")
@Table(name = "person")
@NoArgsConstructor
public class Person {

    @TableGenerator(
            name="person_gen",
            table="ID_GEN",
            pkColumnName="GEN_KEY",
            valueColumnName="GEN_VALUE",
            pkColumnValue="PERSON_ID",
            allocationSize=1)
    @Id
    @GeneratedValue(strategy=TABLE, generator="person_gen")
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;


    public Person(String name) {
        this.name = name;
    }
}
