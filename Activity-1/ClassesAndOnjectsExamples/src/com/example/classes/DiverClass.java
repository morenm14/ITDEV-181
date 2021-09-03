package com.example.classes;

public class DiverClass {
    public static void main(String[] args) {
        House house = new House(3, 5, "shingles ", "drywall");

        System.out.println("House: ");
        System.out.println("Number of windows: " + house.getNumOfWindows());
        System.out.println("Number of doors: " + house.getNumOfDoors());
        System.out.println("Type of roof: " + house.getTypeOfRoof());
        System.out.println("Type of wall: " + house.getTypeOfWall());
    }
}
