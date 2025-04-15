package com.example.project;
import java.util.Scanner;

// Construct the game with proper logic
// NOTE: x,y -> row col: row is reversed and corresponds to y (size - 1 - y), col corresponds to x (x)
// example: an object at 0,0 on a size 10 grid is [10 - 1 - 0][0]
public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 
    private int numTreasures;
    private int numEnemies;

    public Game(int size) { // The constructor calls initialize() and play()
        this.size = size;
        Scanner scan = new Scanner(System.in);
        int input = 1;

        while (input != 2) { // Loop that determines if the user plays the game again or not
            initialize(); // First, initialize the grid
            play(); // Then, play the game
            System.out.println("Would you like to play again?");
            System.out.println("1 - Play Again");
            System.out.println("2 - Quit");
            System.out.print("Enter your choice here: ");
            input = scan.nextInt(); // Last, ask the user if they would play again
        }
    }

    // clearScreen method given by Ms. Turin
    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Game logic here: 
    // User loses the game when lives reach 0
    // User wins when they reach the trophy after collecting all the treasure
    public void play(){ 
        Scanner scan = new Scanner(System.in);

            while(player.getLives() != 0 && !player.getWin()) { //Keep the game playing when player has all lives and has not won
                
                // Clear the screen (Code given by Ms. Turin)
                try {
                    Thread.sleep(100); // Wait for 1/10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                clearScreen(); // Clear the screen at the beggining of the while loop
                
                grid.display(); // Display grid
                // Display all the player's stats
                System.out.println("Treasure Collected: " + player.getTreasureCount());
                System.out.println("Lives remaining: " + player.getLives());
                System.out.println("Choose your direction: w/a/s/d: OR exit to exit: ");

                String direction = scan.nextLine(); // Direction stores user input

                if (!direction.equals("w") 
                && !direction.equals("a") 
                && !direction.equals("s") 
                && !direction.equals("d")
                && !direction.equals("exit") ) {
                    System.out.println("Invalid input."); // Send the user back to input if they do not enter a valid input
                } 
                
                else if (direction.equals("exit")) {
                    System.out.println("You have exited.");
                    break; // Stop the game if user enters "exit".

                } else if (!player.isValid(size, direction)) {
                    System.out.println("Out of bounds."); // Send the user back to input if their move is out of bounds
                }

                else { // Start the move
                    int spriteX = player.getX();
                    int spriteY = player.getY();

                    // Modify the coordinates based on to where the player is moving to
                    if (direction.equals("w")) {spriteY++;}
                    else if (direction.equals("a")) {spriteX--;}
                    else if (direction.equals("s")) {spriteY--;}
                    else if (direction.equals("d")) {spriteX++;}

                    Sprite sprite = grid.getGrid()[size - 1 - spriteY][spriteX]; // Store the Sprite that the Player moves to

                    player.interact(size, direction, numTreasures, sprite); // Interact with the sprite object

                    if (!(sprite instanceof Trophy && player.getTreasureCount() < numTreasures)) { // The player cannot move to the spot with a trophy if they do not have enough treasure
                        player.move(direction); // Player successfully moves
                        grid.placeSprite(player, direction); // Grid replaces previous player location with a dot
                    }
                }
        }
        // End of the move loop
        clearScreen();
        if (player.getWin() == true) {
            grid.win(); // Display the win if the player wins
        } else {
            grid.gameover(); // Display the gameover screen if the user did not win
        } 
    }
    
    // Create the grid based on size, place all the sprites in their proper locations
    // The user can choose difficulty which influences size, enemies, treasures, and lives
    public void initialize() {
        Scanner scan = new Scanner(System.in);
 
        // User chooses difficulty here
        System.out.println();
        System.out.println("Welcome user!! There are 3 difficulties:");
        System.out.println("1 - Easy: size 10 board, 5 enemies, 5 treasures, 3 lives");
        System.out.println("2 - Medium: size 7 board, 7 enemies, 8 treasures, 2 lives");
        System.out.println("3 - Hard: size 7 board, 10 enemies, 12 treasures, 1 life");
        
        System.out.print("Please enter the number for your difficulty: ");

        int difficulty = scan.nextInt();

        while (difficulty != 1 && difficulty != 2 && difficulty != 3) { // Checks for a valid user input
            System.out.print("Invalid input. Try again: ");
            difficulty = scan.nextInt();
        }

        int lives;
        // Initializes values for the variables based on difficulty entered
        if (difficulty == 1) {
            size = 10;
            this.numEnemies = 5;
            this.numTreasures = 5;
            lives = 3;
        }

        else if (difficulty == 2) {
            size = 7;
            this.numEnemies = 7;
            this.numTreasures = 8;
            lives = 2;
        }

        else {
            size = 7;
            this.numEnemies = 10;
            this.numTreasures = 12;
            lives = 1;
        }

        this.grid = new Grid(size); // Create a new Grid full of dots based on size parameter
        this.player = new Player(size-1,0); // Create a new player starting at the bottom left (0, 0)
        this.trophy = new Trophy(size-1, size-1); // Create a trophy placed at the top right (size, size)

        // Create lists for Sprites with multiple locations
        this.enemies = new Enemy[numEnemies]; 
        this.treasures = new Treasure[numTreasures]; 

        player.setLives(lives); // Set player lives based on difficulty chosen
        grid.placeSprite(player); // Place the player based on internal coordinates
        grid.placeSprite(trophy);

        // Place each enemy on the board based on randomly generated coordinates
        for (int i = 0; i < enemies.length; i++) { 
            int x = generateCoord();
            int y = generateCoord();
            
            while (!(grid.getGrid()[size - 1 - y][x] instanceof Dot)) { // Generate new coordinates if the current coord is NOT a dot object
                x = generateCoord();
                y = generateCoord();
            }
            enemies[i] = new Enemy(x, y);
            grid.placeSprite(enemies[i]);
        }
        
        // Place each treasure based on randomly generated coordinates
        for (int i = 0; i < treasures.length; i++) {
            int x = generateCoord();
            int y = generateCoord();
            
            while (!(grid.getGrid()[size - 1 - y][x] instanceof Dot)) { // Generate new coordinates if the current coord is NOT a dot object
                x = generateCoord();
                y = generateCoord();
            }
            treasures[i] = new Treasure(x, y);
            grid.placeSprite(treasures[i]);
        }
    }

    // Random coordinate generator based on size
    public int generateCoord() {
        return (int) (Math.random() * size);
    }

    public static void main(String[] args) {
        Game game = new Game(10); // Construct a game object to play
    }
}
