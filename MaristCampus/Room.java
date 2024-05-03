package MaristCampus; // puts Room.java in the same package as the other classes

// public class Room
// the class Room has the purpose of being able to construct a room
// object with its own list of attributes. This class is extended by
// both the Classroom and Office classes meaning that they each inherent
// the attributes and methods in this class. This means that Room objects,
// Classroom objects, and office objects have all of the attributes shown
// below. These attributes are the INT LENGTH which is the length of the
// room in feet, INT WIDTH which is the width of the room in feet, INT HEGHT
// which is the height of the room in feet, INT FLOOR which is the current
// floor the room is on and INT NUMENTRANCES which is the number of entrances
// into that room
public class Room {
    // all measurements are in feet
    private int length; // length of the room
    private int width; // width of the room
    private int height; // height of the room
    private int floor; // floor number of the room
    private int numEntrances; // number of entrances of the room

    public Room() { // no parameter constructor
        length = 20;
        width = 30;
        height = 10;
        floor = 1;
        numEntrances = 1;
    }

    public Room(int length, int width, int height, int floor, int numEntrances) { // constructor with parameters
        this.length = length;
        this.width = width;
        this.height = height;
        this.floor = floor;
        this.numEntrances = numEntrances;
    }

    public int getLength() { // length getter
        return length;
    }

    public int getWidth() { // width getter
        return width;
    }

    public int getHeight() { // height getter
        return height;
    }

    public int getFloor() { // floor getter
        return floor;
    }

    public int getNumEntrances() { // numEntrances getter
        return numEntrances;
    }

    public void setLength(int length) { // length setter
        this.length = length;
    }

    public void setWidth(int width) { // width setter
        this.width = width;
    }

    public void setHeight(int height) { // height setter
        this.height = height;
    }

    public void setFloor(int floor) { // floor setter
        this.floor = floor;
    }

    public void setNumEntrances(int numEntrances) { // numEntrances setter
        this.numEntrances = numEntrances;
    }

    // public int calculateFloorArea()
    // this methods purpose is to calculate the area of the floor of the room you
    // are in, This method takes no parameters and had the return type INT
    public int calculateFloorArea() {
        return (length * width); // returns the formula for the area
    }

    // public int calculateHeightInAir()
    // this methods purpose is to calculate how high the office is in the air using
    // the floor number. This method does assume that each floor is 10 feet tall.
    // This method takes in no parameters and has the return type INT
    public int calculateHeightInAir() {
        return (floor * 10); // assuming the average height of a floor is 10 feet is multiplies the floor
                             // number by 10 feet giving the number of feet the office is in the air
    }

    // public boolean isFireSafe()
    // this methods purpose is to check if the room you are in is fire safe. It does
    // so by checking the number of entrances in the room. This method has no
    // parameters and has the return type INT
    public boolean isFireSafe() {
        if (numEntrances < 2) { // if there are less than 2 entrances into the room it means the room is not
                                // fire safe and return false
            return false;
        } else {
            return true;
        }
    }

    // public String toString()
    // this method is the toString of the Room class. Whenever the toString is
    // called it returns the string in the method. No parameters are taken in and
    // the return type is a String
    // [See MaristCampus.java for toString() explaination lines 42 - 47]
    public String toString() {
        return "Length, Width, Height: " + length + ", " + width + ", " + height + ", Floor #: " + floor
                + ", Number of Entrances: " + numEntrances;
    }

}