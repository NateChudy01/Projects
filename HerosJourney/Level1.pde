class Level1 extends Level {

    /**
    * Level1()
    * 
    * This is the constructor of the Level1 class
    *
    * @param - none
    * @return - none
    */
    Level1() {
        // Calls the super constructor and then passes the parameters necessary
        super("Level1Images/background.png", "Level1Images/spider.png", "Level1Images/pad.png", "Level1Images/platform_img.png", new Ground[7], new Enemy[10], new PVector(width - 145, height - 100.01), 200.0, 5.0);
        // Initialize grounds
        this.grounds[0] = new Ground(new Coordinate(0, height - 100), 150, 150, "Level1Images/platform_img.png"); // Starting Platform
        this.grounds[1] = new Ground(new Coordinate(width - 145, height - 100), 150, 150, "Level1Images/platform_img.png"); // Winning Platform
        this.grounds[2] = new Ground(new Coordinate(200, height - 250), 150, 25, "Level1Images/pad.png");
        this.grounds[3] = new Ground(new Coordinate(50, height - 400), 150, 25, "Level1Images/pad.png");
        this.grounds[4] = new Ground(new Coordinate(200, height - 550), 150, 25, "Level1Images/pad.png");
        this.grounds[5] = new Ground(new Coordinate(525, height - 125), 150, 25, "Level1Images/pad.png");
        this.grounds[6] = new Ground(new Coordinate(825, height - 225), 150, 25, "Level1Images/pad.png");

        // Initialize enemies
        this.enemies[0] = new Enemy(new Coordinate(50, height - 450), 75, 50, "Level1Images/spider.png");
        this.enemies[1] = new Enemy(new Coordinate(400, height - 625), 75, 50, "Level1Images/spider.png");
        this.enemies[2] = new Enemy(new Coordinate(400, height - 500), 75, 50, "Level1Images/spider.png");
        this.enemies[3] = new Enemy(new Coordinate(400, height - 375), 75, 50, "Level1Images/spider.png");
        this.enemies[4] = new Enemy(new Coordinate(400, height - 250), 75, 50, "Level1Images/spider.png");
        this.enemies[5] = new Enemy(new Coordinate(400, height - 125), 75, 50, "Level1Images/spider.png");
        this.enemies[6] = new Enemy(new Coordinate(725, height - 750), 75, 50, "Level1Images/spider.png");
        this.enemies[7] = new Enemy(new Coordinate(725, height - 625), 75, 50, "Level1Images/spider.png");
        this.enemies[8] = new Enemy(new Coordinate(725, height - 500), 75, 50, "Level1Images/spider.png");
        this.enemies[9] = new Enemy(new Coordinate(700, height - 200), 75, 50, "Level1Images/spider.png");
        
        this.hasReward = true;
        this.reward = loadImage("rewards/armor.png");
    }
}
