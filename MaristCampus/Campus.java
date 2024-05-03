package MaristCampus; // puts Campus.java in the same package as the other classes

import java.lang.Math;

// public class Campus
// this class was created in order to make Campus object which incorporate
// all of the clases that are included in the assignment3 package. This
// campus itself has its own unique attributes including BUILDING[] BUILDINGS
// which is a list of buildings that are on the campus, STRING LOCATION which 
// is the string location of the campus, STRING WEATHER which is the weather on
// the campus and STRING NAME which is the name of the campus
public class Campus {
    private Building[] buildings; // list of buildings on the campus
    private String location; // location of the campus
    private String weather; // weather on campus
    private String name; // name of the campus

    public Campus() { // no parameter constructor
        buildings = new Building[2]; // initializes the building list to have
        // two buildings by defalut
        location = "Poughkeepsie"; // initial location
        weather = "Sunny"; // weather on campus
        name = "Marist"; // name of the campus is initialized to marist
    }

    public Campus(Building[] buildings, String location, String weather, String name) { // constructor with parameters
        this.buildings = new Building[buildings.length];
        for (int i = 0; i < buildings.length; i++) {
            this.buildings[i] = buildings[i];
        }
        this.location = location;
        this.weather = weather;
        this.name = name;
    }

    public String getName() { // name getter
        return name;
    }

    public Building[] getBuildings() { // buildings getter
        return buildings;
    }

    public String getWeather() { // weather getter
        return weather;
    }

    public String getLocation() { // location getter
        return location;
    }

    public void setName(String name) { // name setter
        this.name = name;
    }

    public void setBuildings(Building[] buildings) { // buildings setter
        this.buildings = new Building[buildings.length];
        for (int i = 0; i < buildings.length; i++) {
            this.buildings[i] = buildings[i];
        }
    }

    public void setLocation(String location) { // location setter
        this.location = location;
    }

    public void setWeather(String weather) { // weather setter
        this.weather = weather;
    }

    // public void playGod()
    // this methods purpose is to change the weather on campus to a random weather.
    // This method does not take in any parameters and does not return anything
    public void playGod() { // generates random weather
        String[] weathers = { "Sunny", "Cloudy", "Rainy", "Thunderstorming", "Hailing", "Snowy", "an earthquake" };
        // creates an array of stringgs to store different weather conditions to chose
        // from
        int randomWeather = (int) (Math.random() * weathers.length); // creates a random number from 0 to the length of
        // the array - 1 in order to use it as a random index to generate a random
        // weather condition
        System.out.println("The weather is now " + weathers[randomWeather]); // changes the weather and prints it out
        weather = weathers[randomWeather];
    }

    // public void hostSportingEvent(String[] activeTeams)
    // this methods purpose is to create a sporting event. This method takes in one
    // paramater STRING[] ACTIVETEAMS which is a string array of teams that are
    // active allowing for it to print out that the team is having an event. This
    // method does not return anything
    public void hostSportingEvent(String[] activeTeams) {
        int randIndex = 0; // creates random index place holder
        if (activeTeams.length <= 0) { // if there was an empty array entered
            System.out.println("The list input was empty");
        } else {
            randIndex = (int) (Math.random() * activeTeams.length); // generates a random index and uses it to then
                                                                    // chose a random team at tha index
            System.out.println("The " + activeTeams[randIndex] + " team is having a game, you should go watch!");
        }
    }

    // public void randomCapmusLocation()
    // this methods purpose is to generate a random location for the campus to move
    // to. This method takes in no parameters and has no return type
    public void randomCapmusLocation() {
        int randIndex = 0; // random index place holder
        String[] newLocations = { "New York City", "Los Angeles", "The Moon", "Houston", "North Pole", "Atlantis" };
        // generates a list of random locations for the campus to move to
        randIndex = (int) (Math.random() * newLocations.length); // generates random number to be used for the index of
                                                                 // the previous list
        System.out.println("New Location: " + newLocations[randIndex]); // choses a random location using the random
        // index and changes the location to that while also printing it out
        location = newLocations[randIndex];
    }

    // public String toString()
    // this method is the toString of the Campus class. Whenever the toString is
    // called it returns the string in the method. No parameters are taken in and
    // the return type is a String
    // [See MaristCampus.java for toString() explaination lines 42 - 47]
    public String toString() {
        String tostring = "On " + name + " campus we have " + buildings.length + " buildings:\n";
        int counter = 0;
        for (Building b : buildings) {
            if (counter != 0) {
                tostring = tostring + "\n    *" + b.toString();
                counter++;
            } else {
                tostring = tostring + "    *" + b.toString();
                counter++;
            }
        }
        return tostring;
    }
}