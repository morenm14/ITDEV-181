package com.example.classes;

    public class House
    {

        private int numOfWindows;
        private int numOfDoors;
        private String typeOfRoof;
        private String typeOfWall;

        public House()
        {
            numOfWindows = 0;
            numOfDoors = 0;
            typeOfRoof = null;
            typeOfWall = null;
        }

        public House(int numOfWindows, int numOfDoors, String typeOfRoof, String typeOfWall)
        {
            this.numOfWindows = numOfWindows;
            this.numOfDoors = numOfDoors;
            this.typeOfRoof = typeOfRoof;
            this.typeOfWall = typeOfWall;

        }

        public int getNumOfWindows()
        {
            return numOfWindows;
        }

        public void setNumOfWindows(int numOfWindows)
        {
            this.numOfWindows = numOfWindows;
        }

        public int getNumOfDoors()
        {
            return numOfDoors;
        }

        public void setNumOfDoors(int numOfDoors)
        {
            this.numOfDoors = numOfDoors;
        }

        public String getTypeOfRoof()
        {
            return typeOfRoof;
        }

        public void setTypeOfRoof(String typeOfRoof)
        {
            this.typeOfRoof = typeOfRoof;
        }

        public String getTypeOfWall()
        {
            return typeOfWall;
        }

        public void setTypeOfWall(String typeOfWall)
        {
            this.typeOfWall = typeOfWall;
        }

    }


