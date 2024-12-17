class Ground extends Sprite{

    /**
    * Ground(Coordinate position, int groundWidth, int groundHeight, String imgPath)
    * 
    * This method is the constructor of the Ground class
    *
    * @param - Coordinate position: Coordinate object of the position of the Ground
    *          int groundWidth: Integer value of the width of the ground
    *          int groundHeight: Integer value of the height of the ground
    *          String imgPath: String object of the path of the image being displayed
    * @return - none
    */
    Ground(Coordinate position, int groundWidth, int groundHeight, String imgPath) {
        // Calls the super constructor of the Sprite class
        super(position, groundWidth, groundHeight, imgPath);
    }
}
