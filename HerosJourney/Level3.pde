class Level3 extends Level {

    /**
    * Level3()
    * 
    * This is the constructor of the Level3 class
    *
    * @param - none
    * @return - none
    */
    Level3() {
        // Calls the super constructor and then passes the parameters necessary
        super("Level3Images/castle.jpeg", "Level3Images/fire.png", "Level3Images/castle_brick_pad.png", "Level3Images/castle_brick.png", new Ground[16], new Enemy[24], new PVector(width - 145, height - 100.01), 200.0, 5.0);

        // Initialize grounds
        this.grounds[0] = new Ground(new Coordinate(0, height - 100), 200, 150, "Level3Images/castle_brick.png"); // Starting Platform
        this.grounds[1] = new Ground(new Coordinate(width - 145, height - 100), 150, 150, "Level3Images/castle_brick.png"); // Winning Platform
        this.grounds[2] = new Ground(new Coordinate(200, height - 250), 50, 50, "Level3Images/castle_brick_pad.png"); 
        this.grounds[3] = new Ground(new Coordinate(50, height - 400), 50, 50, "Level3Images/castle_brick_pad.png"); 
        this.grounds[4] = new Ground(new Coordinate(200, height - 550), 50, 50, "Level3Images/castle_brick_pad.png");
        this.grounds[5] = new Ground(new Coordinate(450, height - 700), 50, 50, "Level3Images/castle_brick_pad.png");
        this.grounds[6] = new Ground(new Coordinate(400, height - 100), 50, 50, "Level3Images/castle_brick_pad.png"); 
        this.grounds[7] = new Ground(new Coordinate(525, height - 25), 50, 50, "Level3Images/castle_brick_pad.png");
        this.grounds[8] = new Ground(new Coordinate(650, height - 25), 50, 50, "Level3Images/castle_brick_pad.png");
        this.grounds[9] = new Ground(new Coordinate(775, height - 25), 50, 50, "Level3Images/castle_brick_pad.png");
        this.grounds[10] = new Ground(new Coordinate(width - 310, height - 175), 50, 50, "Level3Images/castle_brick_pad.png");
        this.grounds[11] = new Ground(new Coordinate(710, height - 325), 50, 50, "Level3Images/castle_brick_pad.png");
        this.grounds[12] = new Ground(new Coordinate(width - 237, height - 700), 50, 50, "Level3Images/castle_brick_pad.png");
        this.grounds[13] = new Ground(new Coordinate(710, height - 475), 50, 50, "Level3Images/castle_brick_pad.png");
        this.grounds[14] = new Ground(new Coordinate(760, height - 475), 50, 50, "Level3Images/castle_brick_pad.png");
        this.grounds[15] = new Ground(new Coordinate(843, height - 620), 50, 50, "Level3Images/castle_brick_pad.png");

        // Initialize enemies
        this.enemies[0] = new Enemy(new Coordinate(105, height - 340), 75, 75, "Level3Images/fire.png"); 
        this.enemies[1] = new Enemy(new Coordinate(245, height - 485), 75, 75, "Level3Images/fire.png");
        this.enemies[2] = new Enemy(new Coordinate(30, height - 575), 75, 75, "Level3Images/fire.png"); 
        this.enemies[3] = new Enemy(new Coordinate(315, height - 600), 75, 75, "Level3Images/fire.png");
        this.enemies[4] = new Enemy(new Coordinate(325, height - 350), 75, 75, "Level3Images/fire.png");
        this.enemies[5] = new Enemy(new Coordinate(300, height - 200), 75, 75, "Level3Images/fire.png");
        this.enemies[6] = new Enemy(new Coordinate(425, height - 600), 75, 75, "Level3Images/fire.png");
        this.enemies[7] = new Enemy(new Coordinate(625, height - 800), 75, 75, "Level3Images/fire.png");
        this.enemies[8] = new Enemy(new Coordinate(625, height - 700), 75, 75, "Level3Images/fire.png");
        this.enemies[9] = new Enemy(new Coordinate(625, height - 600), 75, 75, "Level3Images/fire.png");
        this.enemies[10] = new Enemy(new Coordinate(625, height - 500), 75, 75, "Level3Images/fire.png");
        this.enemies[11] = new Enemy(new Coordinate(625, height - 400), 75, 75, "Level3Images/fire.png");
        this.enemies[12] = new Enemy(new Coordinate(625, height - 300), 75, 75, "Level3Images/fire.png");
        this.enemies[13] = new Enemy(new Coordinate(625, height - 200), 75, 75, "Level3Images/fire.png");
        this.enemies[14] = new Enemy(new Coordinate(525, height - 250), 75, 75, "Level3Images/fire.png");
        this.enemies[15] = new Enemy(new Coordinate(width - 250, height - 200), 75, 75, "Level3Images/fire.png");
        this.enemies[16] = new Enemy(new Coordinate(width - 250, height - 600), 75, 75, "Level3Images/fire.png");
        this.enemies[17] = new Enemy(new Coordinate(width - 250, height - 500), 75, 75, "Level3Images/fire.png");
        this.enemies[18] = new Enemy(new Coordinate(width - 250, height - 400), 75, 75, "Level3Images/fire.png");
        this.enemies[19] = new Enemy(new Coordinate(width - 250, height - 300), 75, 75, "Level3Images/fire.png");
        this.enemies[20] = new Enemy(new Coordinate(width - 425, height - 275), 75, 75, "Level3Images/fire.png");
        this.enemies[21] = new Enemy(new Coordinate(830, height - 550), 75, 75, "Level3Images/fire.png");
        this.enemies[22] = new Enemy(new Coordinate(width - 75, -50), 75, 75, "Level3Images/fire.png");
        this.enemies[23] = new Enemy(new Coordinate(width - 150, -50), 75, 75, "Level3Images/fire.png");
        
        this.hasReward = false;
    }
}
