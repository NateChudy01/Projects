class Enemy extends Sprite{
  
  boolean isAlive = true; // Boolean value to check if the enemy is alive.
                          // This is main useful for the Dragon at the end
    
  /**
  * Enemy(Coordinate position, int spriteWidth, int spriteHeight, String imgPath)
  *
  * This method is a constructor for the Enemy Class
  *
  * @param - Coordinate position: The posotion of the enemy, is a Coordinate object
  *          int spriteWidth: Integer value of the width of the enemy sprite
  *          int spriteHeight: Integer value of the height of the enemy sprite
  *          String imgPath: String of the path to the image of the enemy sprite
  * @return - none          
  */
  Enemy(Coordinate position, int spriteWidth, int spriteHeight, String imgPath){
      // Call the super constructor of the Sprite Class
      super(position, spriteWidth, spriteHeight, imgPath);
  }
  
  /**
  * void display()
  * 
  * This method here to display the enemy, it only displays if the enemy is alive
  *
  * @param - none
  * @return - void
  */
  void display() {
      if(isAlive){
          image(spriteImg, position.x, position.y, spriteWidth, spriteHeight);
      }
  }
  
  /**
  * void die()
  * 
  * This method kills the enemy so its not displayed anymore
  *
  * @param - none
  * @return - void
  */
  void die(){
      this.isAlive = false;
  }
  
  /**
  * void revive()
  *
  * This method revives an enemy, typically the dragon
  *
  * @param - none
  * @return - void
  */
  void revive(){
      this.isAlive = true;  
  }
}
