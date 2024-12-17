class Coordinate {
    int x; // X-value of the Coordinate
    int y; // Y-value of the Coordinate

    /**
    * Coordinate(int x, int y)
    *
    * This method is a constructor for the Coordinate Class
    *
    * @param - int x: X-value of the coordinate being created
    *          int y: Y-value of the coordinate being created
    * @return - none
    */
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
    * void set(int newX, int newY)
    *
    * This method is a setter for the Coordinate Class. It
    * allows anyone to edit the X-value and Y-value of a 
    * Coordinate
    *
    * @param - int newX: The new X-value of the coordinate that
    *                    that is being changed
    *          int newY: The new Y-value of the coordinate that
    *                    that is being changed
    * @return - void
    */
    void set(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}
