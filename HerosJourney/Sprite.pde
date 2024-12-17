class Sprite{
    Coordinate position;
    int spriteWidth;
    int spriteHeight;
    PImage spriteImg;
    
    /**
    * Sprite(Coordinate position, int spriteWidth, int spriteHeight, String imgPath)
    * 
    * This is one of the constructors for the Sprite class
    * 
    * @param - Coordinate position: Coordinate object of where the sprite is
    *          int spriteWidth: Integer of the width of the sprite
    *          int spriteHeight: Integer of the height of the sprite
    *          String imgPath: This is the path to the image of the sprite
    * @return - none
    */
    Sprite(Coordinate position, int spriteWidth, int spriteHeight, String imgPath){
        this.position = position;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.spriteImg = loadImage(imgPath);
    }
    
    /**
    * Sprite(Coordinate position, int spriteWidth, int spriteHeight)
    * 
    * This is one of the constructors for the Sprite class
    * 
    * @param - Coordinate position: Coordinate object of where the sprite is
    *          int spriteWidth: Integer of the width of the sprite
    *          int spriteHeight: Integer of the height of the sprite
    * @return - none
    */
    Sprite(Coordinate position, int spriteWidth, int spriteHeight){
        this.position = position;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }
    
    /**
    * void display()
    * 
    * This method displays the image of the sprite
    * 
    * @param - none
    * @return - void
    */
    void display() {
        image(spriteImg, position.x, position.y, spriteWidth, spriteHeight);
    }
}
