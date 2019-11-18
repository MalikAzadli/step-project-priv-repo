package model;

import java.io.Serializable;

public class Passenger implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    public Passenger(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public Passenger(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
