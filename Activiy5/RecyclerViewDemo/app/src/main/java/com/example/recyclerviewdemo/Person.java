package com.example.recyclerviewdemo;

public class Person {

    private String name, lastName, preference;

    public Person(){
    }

    public Person(String name, String lastName, String preference) {
        this.name = name;
        this.lastName = lastName;
        this.preference = preference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

}
