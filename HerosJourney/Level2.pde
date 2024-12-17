class Level2 extends Level {
  
    /**
    * Level2()
    * 
    * This is the constructor of the Level2 class
    *
    * @param - none
    * @return - none
    */
    Level2() {
        // Calls the super constructor and then passes the parameters necessary
        super("Level2Images/cave_background.jpg", "Level2Images/bat.png", "Level2Images/rock_processed.png", "Level2Images/rocks_processed.png", new Ground[17], new Enemy[17], new PVector(width - 145, height - 100.01), 200.0, 5.0);

        // Initialize grounds
        this.grounds[0] = new Ground(new Coordinate(0, height - 100), 150, 150, "Level2Images/rocks_processed.png"); // Starting Platform
        this.grounds[1] = new Ground(new Coordinate(width - 145, height - 100), 150, 150, "Level2Images/rocks_processed.png"); // Winning Platform
        this.grounds[2] = new Ground(new Coordinate(400, height - 50), 50, 50, "Level2Images/rock_processed.png"); 
        this.grounds[3] = new Ground(new Coordinate(200, height - 450), 50, 50, "Level2Images/rock_processed.png"); 
        this.grounds[4] = new Ground(new Coordinate(240, height - 600), 50, 50, "Level2Images/rock_processed.png"); 
        this.grounds[5] = new Ground(new Coordinate(400, height - 650), 50, 50, "Level2Images/rock_processed.png"); 
        this.grounds[6] = new Ground(new Coordinate(650, height - 670), 50, 50, "Level2Images/rock_processed.png"); 
        this.grounds[7] = new Ground(new Coordinate(725, height - 570), 50, 50, "Level2Images/rock_processed.png");
        this.grounds[8] = new Ground(new Coordinate(825, height - 570), 50, 50, "Level2Images/rock_processed.png");
        this.grounds[9] = new Ground(new Coordinate(900, height - 670), 50, 50, "Level2Images/rock_processed.png");
        this.grounds[10] = new Ground(new Coordinate(width - 50, height - 500), 50, 50, "Level2Images/rock_processed.png");
        this.grounds[11] = new Ground(new Coordinate(1000, height - 425), 50, 50, "Level2Images/rock_processed.png");
        this.grounds[12] = new Ground(new Coordinate(850, height - 350), 50, 50, "Level2Images/rock_processed.png");
        this.grounds[13] = new Ground(new Coordinate(650, height - 50), 50, 50, "Level2Images/rock_processed.png");
        this.grounds[14] = new Ground(new Coordinate(950, height - 25), 50, 50, "Level2Images/rock_processed.png");
        this.grounds[15] = new Ground(new Coordinate(450, height - 200), 50, 50, "Level2Images/rock_processed.png");
        this.grounds[16] = new Ground(new Coordinate(325, height - 350), 50, 50, "Level2Images/rock_processed.png");

        // Initialize enemies
        this.enemies[0] = new Enemy(new Coordinate(250, height - 150), 75, 40, "Level2Images/bat.png"); 
        this.enemies[1] = new Enemy(new Coordinate(125, height - 550), 75, 40, "Level2Images/bat.png");
        this.enemies[2] = new Enemy(new Coordinate(513, height - 725), 75, 40, "Level2Images/bat.png");
        this.enemies[3] = new Enemy(new Coordinate(760, height - 690), 75, 40, "Level2Images/bat.png");
        this.enemies[4] = new Enemy(new Coordinate(760, height - 800), 75, 40, "Level2Images/bat.png");
        this.enemies[5] = new Enemy(new Coordinate(1000, height - 625), 75, 40, "Level2Images/bat.png");
        this.enemies[6] = new Enemy(new Coordinate(1070, height - 450), 75, 40, "Level2Images/bat.png");
        this.enemies[7] = new Enemy(new Coordinate(925, height - 375), 75, 40, "Level2Images/bat.png");
        this.enemies[8] = new Enemy(new Coordinate(565, height - 50), 75, 40, "Level2Images/bat.png");
        this.enemies[9] = new Enemy(new Coordinate(565, height - 150), 75, 40, "Level2Images/bat.png");
        this.enemies[10] = new Enemy(new Coordinate(565, height - 250), 75, 40, "Level2Images/bat.png");
        this.enemies[11] = new Enemy(new Coordinate(565, height - 350), 75, 40, "Level2Images/bat.png");
        this.enemies[12] = new Enemy(new Coordinate(625, height - 450), 75, 40, "Level2Images/bat.png");
        this.enemies[13] = new Enemy(new Coordinate(762, height - 510), 75, 40, "Level2Images/bat.png");
        this.enemies[14] = new Enemy(new Coordinate(892, height - 570), 75, 40, "Level2Images/bat.png");
        this.enemies[15] = new Enemy(new Coordinate(785, height - 150), 75, 40, "Level2Images/bat.png");
        this.enemies[16] = new Enemy(new Coordinate(230, height - 380), 75, 40, "Level2Images/bat.png");
        
        this.hasReward = true;
        this.reward = loadImage("rewards/sword.png");

    }
}
