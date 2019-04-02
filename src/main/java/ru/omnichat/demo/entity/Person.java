package ru.omnichat.demo.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@EqualsAndHashCode
@Entity(name = "Person")
@Table(name = "person")
@NoArgsConstructor
@ToString
public class Person {

//    @TableGenerator(
//            name="person_gen",
//            table="ID_GEN",
//            pkColumnName="GEN_KEY",
//            valueColumnName="GEN_VALUE",
//            pkColumnValue="PERSON_ID",
//            allocationSize=1)
//    @Id
//    @GeneratedValue(strategy=TABLE, generator="person_gen")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_gen")
    @SequenceGenerator(
        name = "person_gen",
        sequenceName = "person_id_seq",
        allocationSize = 1
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "number")
    private Integer number;

    public Person(String name, Integer number) {
        this.name = name;
        this.number = number;
    }
}
