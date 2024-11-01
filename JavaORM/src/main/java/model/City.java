package main.java.model;

public class City {

    private int id;
    private String name;
    private String state;
    private String country;

    public City() {
    }
    
    public City(int id, String name, String state, String country) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString(){
        return "City{" +
        "id=" + id +
        ", name=" + name +
        ", state=" + state +
        ", country=" + country +
        '}';
    }
}


