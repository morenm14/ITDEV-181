package com.example.fragmentsrecyclerviewdemo;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Person> people;

    @Override
    public void onCreate() {
        super.onCreate();

        people = new ArrayList<Person>();
        people.add(new Person("Mario Moreno", "41565132324"));
        people.add(new Person("Bruce Lee", "555-555-6666"));
        people.add(new Person("Chuck Norris", "41565132324"));
        people.add(new Person("Jackie Chan", "111-888-9999"));
        people.add(new Person("Clint Eastwood", "41565132324"));
        people.add(new Person("Keanu Reeves", "414.467.7467"));
    }
}
