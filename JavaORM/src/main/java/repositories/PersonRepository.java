package main.java.repositories;

import main.java.model.Person;
import main.java.orm.CSVOrm;

public class PersonRepository extends CSVOrm<Person> {
    public PersonRepository(String filePath) {
        super(filePath, Person.class);
    }
}

