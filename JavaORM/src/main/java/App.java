package main.java;


import main.java.config.Config;
import main.java.model.City;
import main.java.model.Person;
import main.java.repositories.CityRepository;
import main.java.repositories.PersonRepository;

public class App {
    public static void main(String[] args) {
        PersonRepository personRepo = new PersonRepository(Config.PERSON_CSV_PATH);
        CityRepository cityRepo = new CityRepository(Config.CITY_CSV_PATH);
        
        //create person
        Person newPerson1 = new Person(1, "Juan", 30);
        Person newPerson2 = new Person(2, "Maria", 17);
        personRepo.save(newPerson1);
        personRepo.save(newPerson2);

        // Get all persons
        personRepo.getAll().forEach(System.out::println);

        // Update person 
        newPerson1.setName("Juan Actualizado");
        personRepo.update(1, newPerson1);
        // find person by id after update 
        Person person = personRepo.findById(1);
        System.out.println("Encontrado: " + person);

        // Delete person 
        personRepo.delete(1);

        //create city
        City newCity1 = new City(1, "Armenia", "Risaralda", "Colombia");
        cityRepo.save(newCity1);
        City newCity2 = new City(2, "Pereira", "Risaralda", "Colombia");
        cityRepo.save(newCity2);

        // Get all cities
        cityRepo.getAll().forEach(System.out::println);

        // Update city 
        newCity1.setState("Quindio");
        cityRepo.update(1, newCity1);
        // find city by id
        City city = cityRepo.findById(1);
        System.out.println("Encontrado: " + city);

        // Delete person 
        cityRepo.delete(1);
    }
}