package main.java;

import java.util.List;

import main.java.config.Config;
import main.java.model.Person;
import main.java.repositories.PersonRepository;

public class App {
    public static void main(String[] args) {
        PersonRepository personaRepo = new PersonRepository(Config.PERSON_CSV_PATH);

        // Obtener todos
        List<Person> persons = personaRepo.getAll();
        persons.forEach(System.out::println);
    }
}
