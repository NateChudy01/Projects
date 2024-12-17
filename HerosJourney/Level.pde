class Level{
    PImage background, enemy, platform, ground;
    PVector winAreaPosition;
    float winAreaWidth, winAreaHeight;
    Ground[] grounds;
    Enemy[] enemies;
    PImage reward;
    boolean hasReward;
    
    /**
    * Level(String backgroundImgPath, String enemyImgPath, String platformImgPath, String groundImgPath, Ground[] grounds, Enemy[] enemies, PVector winAreaPosition, float winAreaWidth, float winAreaHeight)
    *
    * This is one of the constructors for the Level Class
    *
    * @param - String backgroundImgPath: This is the string path to the background image of the level
    *          String enemyImgPath: This is the string path to the enemy image of the level
    *          String platformImgPath: This is the string path to the platform image that the character lands on
    *          String groundImgPath: This is the string path to the ground image where the spawn and ending platforms are
    *          Ground[] grounds: This is a list of all of the ground elements in the level
    *          Enemy[] enemies: This is a list of all of the enemies in the level
    *          PVector winAreaPosition: This is a PVector of where the win area of the level is
    *          float winAreaWidth: float value of the win area width
    *          float winAreaHeight: float value of the win area height
    * @return - none
    */
    Level(String backgroundImgPath, String enemyImgPath, String platformImgPath, String groundImgPath, Ground[] grounds, Enemy[] enemies, PVector winAreaPosition, float winAreaWidth, float winAreaHeight){
        this.background = loadImage(backgroundImgPath);
        this.enemy = loadImage(enemyImgPath);
        this.platform = loadImage(platformImgPath);
        this.ground = loadImage(groundImgPath);
        this.grounds = grounds;
        this.enemies = enemies;
        this.winAreaPosition = winAreaPosition;
        this.winAreaWidth = winAreaWidth;
        this.winAreaHeight = winAreaHeight;
    }
    
    /**
    * Level(String backgroundImgPath, String enemyImgPath, String groundImgPath, Ground[] grounds, Enemy[] enemies, PVector winAreaPosition, float winAreaWidth, float winAreaHeight)
    * 
    * This is one of the constructors for the Level Class
    *
    * @param - String backgroundImgPath: This is the string path to the background image of the level
    *          String enemyImgPath: This is the string path to the enemy image of the level
    *          String groundImgPath: This is the string path to the ground image where the spawn and ending platforms are
    *          Ground[] grounds: This is a list of all of the ground elements in the level
    *          Enemy[] enemies: This is a list of all of the enemies in the level
    *          PVector winAreaPosition: This is a PVector of where the win area of the level is
    *          float winAreaWidth: float value of the win area width
    *          float winAreaHeight: float value of the win area height
    * @return - none
    */
    Level(String backgroundImgPath, String enemyImgPath, String groundImgPath, Ground[] grounds, Enemy[] enemies, PVector winAreaPosition, float winAreaWidth, float winAreaHeight){
        this.background = loadImage(backgroundImgPath);
        this.enemy = loadImage(enemyImgPath);
        this.ground = loadImage(groundImgPath);
        this.grounds = grounds;
        this.enemies = enemies;
        this.winAreaPosition = winAreaPosition;
        this.winAreaWidth = winAreaWidth;
        this.winAreaHeight = winAreaHeight;
    }
    
    /**
    * boolean checkWinCondition(Hero hero)
    * 
    * This method checks if the hero wins the level
    * 
    * @param - Hero hero: Hero that is being checked if they won
    * @return - boolean value if the hero wins
    */
    boolean checkWinCondition(Hero hero) {
        return (hero.position.x + hero.spriteWidth > winAreaPosition.x &&
                hero.position.x < winAreaPosition.x + winAreaWidth &&
                hero.position.y + hero.spriteHeight > winAreaPosition.y &&
                hero.position.y < winAreaPosition.y + winAreaHeight);
    }
    
    /**
    * void ungenerateReward()
    * 
    * This method ungenerates the reward of the level once the character
    * lands on it
    *
    * @param - none
    * @return - void
    */
    void ungenerateReward(){
        this.hasReward = false;
    }
    
    /**
    * void regenerateReward()
    * 
    * This method regenerates the reward of the level
    *
    * @param - none
    * @return - void
    */
    void regenerateReward(){
        this.hasReward = true;
    }
}
