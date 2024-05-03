package MaristCampus; // puts Building.java in the same package as the other classes

import java.lang.Math;

// public class Building
// this class was created in order to make Building objects with their own
// particular attributes and methods. This class has different building
// attributes like ROOM[] ROOMS which is a list of rooms inside of the building, 
// INT HEIGHT which is the height of the building, SRTING LOCATIONONCAMPUS which 
// is the string location of the building on the campus, and STRING NAME which 
// is the name of the building
public class Building {
    private Room[] rooms; // list of rooms in the building object
    private int height; // height of the building
    private String locationOnCampus; // string location of the building on the campus
    String name; // name of the building

    public Building() { // no parameter constructor
        rooms = new Room[2]; // initilizes three rooms in the rooms list,
                             // a classroom, an office, and a generic room
        rooms[0] = new Classroom();
        rooms[1] = new Office();
        rooms[2] = new Room();
        height = 100; // sets inital height to 100 feet
        locationOnCampus = "West Side of campus"; // inital location on campus
        name = "Donnely"; // default name to the building
    }

    public Building(Room[] rooms, int height, String locationOnCampus, String name) { // constructor with parameters
        this.height = height;
        this.locationOnCampus = locationOnCampus;
        this.rooms = new Room[rooms.length];
        for (int i = 0; i < rooms.length; i++) {
            this.rooms[i] = rooms[i];
        }
        this.name = name;
    }

    public int getHeight() { // height getter
        return height;
    }

    public Room[] getRooms() { // rooms getter
        return rooms;
    }

    public String getLocationOnCampus() { // locationOnCampus getter
        return locationOnCampus;
    }

    public String getName() { // name getter
        return name;
    }

    public void setName(String name) { // name setter
        this.name = name;
    }

    public void setHeight(int height) { // height setter
        this.height = height;
    }

    public void setLocationOnCampus(String locationOnCampus) { // locationOnCampus setter
        this.locationOnCampus = locationOnCampus;
    }

    public void setRooms(Room[] rooms) { // rooms setter
        this.rooms = new Room[rooms.length];
        for (int i = 0; i < rooms.length; i++) {
            this.rooms[i] = rooms[i];
        }
    }

    // public String randomHeight()
    // this methods purpose is to change the buildings height to a random height
    // between 100 and 599 feet. This method takes in no parameters and the return
    // type is a String
    public String randomHeight() {
        int randHeight = (int) (Math.random() * 500) + 100; // generates a random number between 100 and 600 but it is
        // non inclusive (meaning that it is a number between 100 and 599)
        height = randHeight; // sets the class height to the new random height
        return "Changing this buildings height to " + randHeight; // returns the change
    }

    // public String cleanBuilding()
    // this methods purpose is to return that the building is being cleaned. This
    // method takes in no parameters and returns a String
    public String cleanBuilding() {
        return "This building is being cleaned right now!"; // string that is returned by this method
    }

    // public String lockItUp()
    // this methods purpose is to return a string that says that the building is
    // being locked up with the name of the building in the returning string. This
    // method takes in no parameters and returns a string
    public String lockItUp() {
        return "Im sorry, " + name + " is being locked up for the night"; // string statement to return
    }

    // public String toString()
    // this method is the toString of the Building class. Whenever the toString is
    // called it returns the string in the method. No parameters are taken in and
    // the return type is a String
    // [See MaristCampus.java for toString() explaination lines 42 - 47]
    public String toString() {
        String tostring = "The name of this building is " + name + " and has " + rooms.length
                + " rooms inside of it:\n";
        int counter = 0;
        for (Room r : rooms) {
            if (counter != 0) {
                tostring = tostring + "\n        *" + r.toString();
                counter++;
            } else {
                tostring = tostring + "        *" + r.toString();
                counter++;
            }
        }
        return tostring;
    }
}