class FinalLevel extends Level{
    
    /**
    * FinalLevel()
    * 
    * This method is the constructor for the finalLevel of the game
    *
    * @param - none
    * @return - none
    */
    FinalLevel(){
        // Call the super constructor for the Level class
        super("Level3Images/castle.jpeg", "Level3Images/fire.png", "Level3Images/castle_brick.png", new Ground[8], new Enemy[1], new PVector(width - 145, height - 100.01), 200.0, 5.0);
        
        // Set all of the ground elements
        this.grounds[0] = new Ground(new Coordinate(0, height - 100), 150, 150, "Level3Images/castle_brick.png");
        this.grounds[1] = new Ground(new Coordinate(150, height - 100), 150, 150, "Level3Images/castle_brick.png");
        this.grounds[2] = new Ground(new Coordinate(300, height - 100), 150, 150, "Level3Images/castle_brick.png");
        this.grounds[3] = new Ground(new Coordinate(450, height - 100), 150, 150, "Level3Images/castle_brick.png");
        this.grounds[4] = new Ground(new Coordinate(600, height - 100), 150, 150, "Level3Images/castle_brick.png");
        this.grounds[5] = new Ground(new Coordinate(750, height - 100), 150, 150, "Level3Images/castle_brick.png");
        this.grounds[6] = new Ground(new Coordinate(900, height - 100), 150, 150, "Level3Images/castle_brick.png");
        this.grounds[7] = new Ground(new Coordinate(1050, height - 100), 150, 150, "Level3Images/castle_brick.png");
        
        // Set the enemy element
        this.enemies[0] = new Enemy(new Coordinate((width/2) - 200, height - 500), 400, 400, "CharacterImages/Dragon/dragon.png");
     }
     
     /**
     * void reviveDragon()
     *
     * This method revives the Dragon for replay
     *
     * @param - none
     * @return - void
     */
     void reviveDragon(){
         this.enemies[0].revive();
     }
}
