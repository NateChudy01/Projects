//-----------------------------------------------
// HeroesJourney.pde v3.3
//-----------------------------------------------
//  Date             Comment                Who
//-------- ------------------------------ -------
//11/02/24    Final Level Development       NAC
//11/11/24    Added S&Q Button              Nihar
//11/12/24    Added Instructions            Nihar
//11/21/24    Added Story Cards             NAC
//11/21/24    Instructions Rework           NAC
//11/22/24    JavaDoc commenting added      NAC
//11/22/24    Game Replay bugs fixed        NAC
//11/22/24    Playthrough video             NAC

PImage backgroundImage, logoImage, buttonImage;
Hero hero;

boolean spacePressed, leftPressed, rightPressed, isHeroDead, hasWon = false;
int currTime, winTime = 0;
int quitX, quitY;
int regDisplayDuration = 1000;
int gameBeatenDisplay = 3000;
int nextLevelDelay = 500; 
boolean loadingNextLevel, collidedWithEnemy, isNextLevelStarted = false;
boolean killEnemy = false;
boolean dragonBeaten = false;
boolean startButtonHovered = false;
boolean quitButtonHovered = false;

Level currentLevel;
Level1 level1;
Level2 level2;
Level3 level3;
FinalLevel finalLevel;

int menuState = 0; // 0 - Main menu, 1 - Instructions, 2 - Game
int buttonX, buttonY, buttonWidth, buttonHeight;
int boxWidth = 500; // Width of the box for logo and button
int boxHeight = 400; // Height of the box
int levelStartTime = 0; // Current time stamp of instructions on screen
                        // Used for the timer
boolean startedInstructionsTimer = false; // Used with levelStartTime

/**
* void setup()
*
* This method runs first to initialize any values needed for the
* execution of the program
*
* @param - none
* @return - void
*/
void setup() {
    size(1200, 800);
    logoImage = loadImage("menu_images/logo.png");
    logoImage.resize(300, 300); // Adjust the size of the logo
    buttonImage = loadImage("menu_images/buttonImage.png"); 

    level1 = new Level1();
    level2 = new Level2();
    level3 = new Level3();
    finalLevel = new FinalLevel();
    
    currentLevel = level1; // Start with level 1
    backgroundImage = level1.background; // Use level1 background for menu and instructions
    
    hero = new Hero(new Coordinate(currentLevel.grounds[0].position.x, currentLevel.grounds[0].position.y - 75), 50, 75, 5, 0.5);
    hero.onGround = true;

    // Define the button dimensions and position
    buttonWidth = 200;
    buttonHeight = 71;
    buttonX = width/2 - buttonWidth/2;
    buttonY = height/2 + 80; // Position inside the black box
}

/**
* void draw()
*
* This method is the constantly running method that simulates the game
* loop. Anything that happens while the game is running is checked in
* here
*
* @param - none
* @return - void
*/
void draw() {
    background(backgroundImage); // Use level1 background for all states
    switch(menuState) {
        case 0:
            drawMenu();
            break;
        case 1:
            drawGame();
            break;
        case 2:
            drawStoryScreen();
            break;
    }
}

/**
* void drawMenu()
*
* This method is called in menuState 0. It displays the main menu where 
* you can either click to start the game or click to quit the game
*
* @param - none
* @return - void
*/
void drawMenu() {
    background(backgroundImage);
    image(logoImage, width / 2 - logoImage.width / 2, height / 2 - logoImage.height - 20);

    // Start button setup
    int buttonX = width / 2 - buttonImage.width / 2;
    int buttonY = height / 2 + 50;

    if (startButtonHovered) {
        fill(255, 255, 0);
    } else {
        fill(255); // Default white text color
    }
    image(buttonImage, buttonX, buttonY);
    textSize(25);
    textAlign(CENTER, CENTER);
    text("Start Game", buttonX + buttonImage.width / 2, buttonY + buttonImage.height / 2);

    // Quit button setup
    int quitY = buttonY + buttonImage.height + 20; // 20 pixels space between buttons

    if (quitButtonHovered) {
        //noFill();
        fill(255, 255, 0);
    } else {
        fill(255); // Default white text color
    }
    image(buttonImage, buttonX, quitY);
    text("Quit Game", buttonX + buttonImage.width / 2, quitY + buttonImage.height / 2);
}

/**
* void drawGame()
*
* This method is called in menuState 1. It can be though of as everything 
* that happens in the game. Collisions, win positions, level checking, and 
* others that determine where the player is and the state they are in
*
* @param - none
* @return - void
*/
void drawGame() {
    background(currentLevel.background);
    
    if(currentLevel == level1 && !startedInstructionsTimer){
        levelStartTime = millis();
        startedInstructionsTimer = true;
    }

    if(currentLevel.hasReward && currentLevel == level1){
        image(currentLevel.reward, width - 110, height - 173, 75, 75);
    } else if (currentLevel.hasReward && currentLevel == level2){
        image(currentLevel.reward, width - 120, height - 180, 100, 100);
    }

    // Display grounds and enemies
    for (Ground ground : currentLevel.grounds) {
        ground.display();
    }
    
    for (Enemy enemy : currentLevel.enemies) {
        enemy.display();
    }

    // Display and update hero
    if (!isHeroDead) {
        hero.display();
        hero.applyGravity();
        checkLandOnGround();
        hero.update();
        hero.handleMovement(width, leftPressed, rightPressed);
    } else {
        hero.display();  // This will show the hero with or without the die image
    }

    // Handle inputs and other game states
    if (spacePressed && hero.onGround) {
        hero.jump();
    }
    
    checkEnemyCollision();
    checkDeath();
    
    if (currentLevel == level1 && millis() - levelStartTime < 3000) {
        fill(255); // White color
        textSize(30); // Adjust text size
        textAlign(CENTER); // Center the text horizontally
        text(
            "Move: ARROW KEYS\n" +
            "Jump: SPACEBAR\n",
            width / 2, height / 2 // Display at the top center of the screen
        );
    }

    // Handle game progression
    if (currentLevel.checkWinCondition(hero) && !hasWon) {
        hasWon = true;
        winTime = millis();
    }

    if (hasWon && millis() - winTime > regDisplayDuration && !loadingNextLevel) {
        loadingNextLevel = true;
        isNextLevelStarted = false;
    }

    if (loadingNextLevel && !isNextLevelStarted && millis() - winTime > regDisplayDuration + nextLevelDelay) {
        isNextLevel();
    }

    // Display "DIED" if the hero is dead
    if (isHeroDead && millis() - currTime <= regDisplayDuration) {
        fill(255, 0, 0);  // Red color
        textSize(100);
        textAlign(CENTER, CENTER);
        text("DIED", width / 2, height / 2);
    }

    // Display "LEVEL COMPLETE" if won
    if (hasWon && millis() - winTime <= regDisplayDuration && currentLevel != finalLevel) {
        fill(0, 255, 0);  // Green color
        textSize(100);
        textAlign(CENTER, CENTER);
        text("LEVEL COMPLETE", width / 2, height / 2);
        currentLevel.ungenerateReward();
        if(currentLevel == level1){
          hero.setImage(2);
        }else if(currentLevel == level2){
          hero.setImage(3);
        }
    }
    
    if (hasWon && millis() - winTime <= gameBeatenDisplay && currentLevel == finalLevel) {
        dragonBeaten = true;
    }
}

/**
* void isNextLevel()
*
* This method is used to generate the next level. It will be called when all
* the conditions for the next level being spawned are met. It increments the 
* level to the next level the character will play
*
* @param - none
* @return - void
*/
void isNextLevel() {
    if (currentLevel == level1) {
        currentLevel = level2; // Move to level 2
        hero.setImage(2);
        // UPDATE SPAWNPOINT
        hero.position = new Coordinate(currentLevel.grounds[0].position.x, currentLevel.grounds[0].position.y - 75);
        hero.velocity = 0; // Reset velocity
        menuState = 2;
    } else if (currentLevel == level2) {
        currentLevel = level3; // Move to level 2
        hero.setImage(3);
        // UPDATE SPAWNPOINT
        hero.position = new Coordinate(currentLevel.grounds[0].position.x, currentLevel.grounds[0].position.y - 75);
        hero.velocity = 0; // Reset velocity
        menuState = 2;
    } else if (currentLevel == level3){
        currentLevel = finalLevel;
        killEnemy = true;
        hero.position = new Coordinate(currentLevel.grounds[0].position.x, currentLevel.grounds[0].position.y - 75);
        hero.velocity = 0;
    } else if (currentLevel == finalLevel){
        menuState = 2;
    }

    // Reset flags for the next level
    hasWon = false;
    loadingNextLevel = false;
}

/**
* void checkDeath()
*
* This method call is inside of the draw() method (the game loop). It constantly
* checks if the user died. This can happen by either the user hitting into an
* enemy or falling off of the map and then revives the character after a specified
* amount of time
*
* @param - none
* @return - void
*/
void checkDeath() {
    // Check for collision with enemies
    if (collidedWithEnemy) {
        if (!isHeroDead && currentLevel != finalLevel) {
            hero.die(true);  // Death by enemy, show die image
        }
    }
    // Check for freefall condition
    else if (hero.position.y > height && !isHeroDead) {
        hero.die(false);  // Death by freefall, do not show die image
    }

    // Handle the revival process after the display duration
    if (isHeroDead && millis() - currTime > regDisplayDuration) {
        hero.revive();  // Call revive function to reset the hero
    }
}

/**
* void checkEnemyCollision()
*
* This method is one of the main components used in the checkDeath() method.
* Specifically the collidedWithEnemy boolean value. This method flips the value
* to true if there is a collision detected with an enemy. This is then used with
* the checkDeath method to determine death
*
* @param - none
* @return - void
*/
void checkEnemyCollision() {
    for (Enemy enemy : currentLevel.enemies) {
        if (enemy.isAlive && // Ensure we only check for living enemies
            hero.position.x + hero.spriteWidth > enemy.position.x &&
            hero.position.x < enemy.position.x + enemy.spriteWidth &&
            hero.position.y + hero.spriteHeight > enemy.position.y &&
            hero.position.y < enemy.position.y + enemy.spriteHeight) {

            collidedWithEnemy = true;

            if (currentLevel == finalLevel) {
                // Kill the enemy and trigger the win
                enemy.die();
                hasWon = true;
                winTime = millis(); // Record the win time to show the "LEVEL COMPLETE" message
                break; // Exit loop as we've handled the collision
            } else {
                if (!killEnemy) {
                    if (!isHeroDead) { // Ensure we don't repeatedly kill the hero
                        hero.dieFromEnemy(); // Use the existing function
                    }

                    if (!hero.isPaused) {
                        hero.pause(1000); // Pause for 1 second
                    }
                } else {
                    currentLevel.enemies[0].position = new Coordinate(1300, 500);
                }
            }
        }
    }

    // Revive the hero after the pause if dead
    if (isHeroDead && millis() - currTime > regDisplayDuration) {
        hero.revive(); // Reset hero to starting position and state
    }
}

/**
* void checkLandOnGround()
*
* This method does something similar to the previous method checkEnemyCollision().
* It loops through all of the ground elements and then if there is a collision
* with the hero, the hero ends up landing on top of the platform
*
* @param - none
* @return - void
*/
void checkLandOnGround() {
    boolean foundGround = false;
    for (Ground ground : currentLevel.grounds) {
        // Check if hero is above or colliding with ground only when falling
        if (hero.velocity > 0 &&
            hero.position.x + hero.spriteWidth > ground.position.x &&
            hero.position.x < ground.position.x + ground.spriteWidth &&
            hero.position.y + hero.spriteHeight >= ground.position.y &&
            hero.position.y < ground.position.y + ground.spriteHeight) {

            // Land only if hero is approaching from above
            if (hero.position.y + hero.spriteHeight <= ground.position.y + 20) { 
                hero.land(ground.position.y - hero.spriteHeight);
                foundGround = true;
                break;
            }
        }
    }

    // Set hero's onGround status
    if (!foundGround && !hero.isJumping()) { 
        hero.onGround = false;
    }
}

/**
* void drawStoryScreen()
*
* This methods purpose is to draw the story cards that are inbetween levels. 
* it is called when the game is in menuState 2. There is an if statement which
* determines which message is printed depending on which level was just complete
*
* @param - none
* @return - void
*/
void drawStoryScreen() {
    if(currentLevel == level1){
      background(0); 
      fill(255); // White color
      textSize(30); // Adjust the text size as needed
      textAlign(CENTER, CENTER);
      
      text("A long time ago, Draco the Dragon ruled over this land. \n" + 
      "Draco would spread his evil throughout the realm, one hero built \n" + 
      "up the courage to fight off Draco and save the world. However, \n" + 
      "some of Dracos followers have brought him back and he is gaining strength.\n\n\n" + 
      "Your name is Leo and you have been chosen to go on the quest to defeat Draco \n" + 
      "once and for all. As you begin by heading through a forest with dangerous \n" + 
      "spiders sent by Draco, you see armor on the other side of the forest that could \n" + 
      "help you with your fight against Draco. You have to maneuver your way through \n" + 
      "the forest to get the armor \n\n\n" + 
      "Press ENTER to continue",
         width / 2, height / 2); // Adjust text position and bounds
    }else if(currentLevel == level2){
      background(0); 
      fill(255); // White color
      textSize(30); // Adjust the text size as needed
      textAlign(CENTER, CENTER);
      
      text("You were able to make it through the forest, as you leave you \n" + 
      "come up to the entrance of a cave. You can see that there is a sword \n" + 
      "buried in the stone at the other end. The only way to get there is \n" + 
      "to jump through the cave avoiding the dangerous bats trying to stop you. \n" + 
      "You have no choice, you need the sword to fight Draco \n\n\n" + 
      "Press ENTER to continue",
         width / 2, height / 2); // Adjust text position and bounds
    }else if(currentLevel == level3){
      background(0); 
      fill(255); // White color
      textSize(30); // Adjust the text size as needed
      textAlign(CENTER, CENTER);
      
      text("You now have everything you need to defeat Draco. You begin to \n" + 
      "travel to Dracos castle, as you make your way there you can hear the \n" + 
      "roars of Draco in the background. Once you enter the castle, you can \n" + 
      "see that everything is on fire from Draco. You can see the door to \n" + 
      "Draco in the background but you need to maneuver through the fire \n" + 
      "in order to fight Draco\n\n\n" + 
      "Press ENTER to continue",
         width / 2, height / 2); // Adjust text position and bounds
    }else if(currentLevel == finalLevel && dragonBeaten){
      background(0); 
      fill(255); // White color
      textSize(30); // Adjust the text size as needed
      textAlign(CENTER, CENTER);
      
      text("Thank you for playing!\n\n\n" + 
      "You have defeated Draco. You helped Leo along the way to saving the world. \n" + 
      "Draco should never be able to come back again!\n\n\n" + 
      "Creators:\n" + 
      "Nate Chudy\n" + 
      "Nihar Lodaya\n" + 
      "Snithika Gaddam\n\n\n" + 
      "Press ENTER to return to the main menu",
         width / 2, height / 2); // Adjust text position and bounds
    }
}

/**
* void mousePressed()
*
* This method is one included with processing, it is automatically called
* when there is a mouse press detected. This is only used for the main menu
* to determine starting the game and quitting the game
*
* @param - none
* @return - void
*/
void mousePressed() {
    int buttonX = width / 2 - buttonImage.width / 2;
    int startButtonY = height / 2 + 50;
    int quitButtonY = startButtonY + buttonImage.height + 20;

    if (menuState == 0) {
        if (mouseX > buttonX && mouseX < buttonX + buttonImage.width &&
            mouseY > startButtonY && mouseY < startButtonY + buttonImage.height) {
            menuState = 2; // Change to instructions screen
        }

        if (mouseX > buttonX && mouseX < buttonX + buttonImage.width &&
            mouseY > quitButtonY && mouseY < quitButtonY + buttonImage.height) {
            exit();
        }
    }
}

/**
* void keyPressed()
*
* This method is one included with processing, it is automatically invoked
* when a key is pressed and then is caught and handled depending on the 
* key pressed (jump, move, move between story cards)
*
* @param - none
* @return - void
*/
void keyPressed() {
    if (menuState == 2 && key == ENTER && currentLevel != finalLevel) {
        menuState = 1; // Start the game when ENTER is pressed in story
    }
    
    if (menuState == 2 && key == ENTER && currentLevel == finalLevel) {
        menuState = 0;
        resetGame();
    }
    
    // Handle game controls if in game state
    if (menuState == 1) {
        handleGameControls();
    }
}

/**
* void resetGame()
*
* This method is here to reset some values that need to be reset if the
* player wants to play the game multiple times in one runtime
*
* @param - none
* @return - void
*/
void resetGame(){
        currentLevel = level1;
        hero.setImage(1);
        hero.revive();
        finalLevel.reviveDragon();
        killEnemy = false;
        dragonBeaten = false;
        level1.regenerateReward();
        level2.regenerateReward();
}

/**
* void handleGameControls()
*
* This method is called from the keyPressed() method when the game is 
* in menuState 2 meaning the character is on the screen moving around. It 
* controls the inputs of the characters movement including moving, and jumping
*
* @param - none
* @return - void
*/
void handleGameControls() {
    if (key == ' ') {
        spacePressed = true;
    }
    if (keyCode == LEFT) {
        leftPressed = true;
    }
    if (keyCode == RIGHT) {
        rightPressed = true;
    }
}

/**
* void mouseMoved()
*
* This method is one included with processing, and is included for a better looking game.
* It highlights the button you are hovering over that you can press. This can be 
* seen in the main menu
*
* @param - none
* @return - void
*/
void mouseMoved() {
    int buttonX = width / 2 - buttonImage.width / 2; // Central X position for buttons
    int startButtonY = height / 2 + 50; // Start button Y position
    int quitButtonY = startButtonY + buttonImage.height + 20; // Quit button below the start button

    // Check if the mouse is hovering over the start button
    startButtonHovered = mouseX > buttonX && mouseX < buttonX + buttonImage.width &&
                         mouseY > startButtonY && mouseY < startButtonY + buttonImage.height;

    // Check if the mouse is hovering over the quit button
    quitButtonHovered = mouseX > buttonX && mouseX < buttonX + buttonImage.width &&
                        mouseY > quitButtonY && mouseY < quitButtonY + buttonImage.height;
}

/**
* void keyReleased()
*
* This method is one included with processing, it is used in conjunction with
* keyPressed(). It helps out with ironing out problems with movement and jumping
* at the same time
*
* @param - none
* @return - void
*/
void keyReleased() {
    if (menuState == 1) { // Only handle game controls if in game state
        if (key == ' ') {
            spacePressed = false;
        }
        if (keyCode == LEFT) {
            leftPressed = false;
        }
        if (keyCode == RIGHT) {
            rightPressed = false;
        }
    }
}
