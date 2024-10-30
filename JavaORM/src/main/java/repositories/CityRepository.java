package main.java.repositories;

import main.java.model.City;
import main.java.orm.CSVOrm;

public class CityRepository extends CSVOrm<City>{
    public CityRepository(String filePath){
        super(filePath, City.class);
    }
}
