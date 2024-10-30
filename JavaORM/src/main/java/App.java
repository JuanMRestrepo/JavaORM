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
        Person newPerson = new Person(1, "Juan", 30);
        personRepo.save(newPerson);

        // Get all persons
        personRepo.getAll().forEach(System.out::println);

        // Update person 
        newPerson.setName("Juan Actualizado");
        personRepo.update(1, newPerson);
        // find person by id
        Person person = personRepo.findById(1);
        System.out.println("Encontrado: " + person);

        // Delete person 
        personRepo.delete(1);

        //create city
        City newCity = new City(1, "Armenia", "Risaralda", "Colombia");
        cityRepo.save(newCity);

        // Get all cities
        cityRepo.getAll().forEach(System.out::println);

        // Update city 
        newCity.setState("Quindio");;
        cityRepo.update(1, newCity);
        // find city by id
        City city = cityRepo.findById(1);
        System.out.println("Encontrado: " + city);

        // Delete person 
        cityRepo.delete(1);
    }
}