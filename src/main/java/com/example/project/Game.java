package com.example.project;
import java.util.Scanner;

// Construct the game with proper logic
public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size) { // The constructor calls initialize() and play()
        this.size = size;
        initialize();
        play();
    }

    public boolean validInput(String direction) {
        boolean bool = false;
        if (!direction.equals("w")) {
            bool = true;
        } 
        if (!direction.equals("a")) {
            bool = true;
        } 
        if (!direction.equals("s")) {
            bool = true;
        }
        if (!direction.equals("d")) {
            bool = true;
        }
        return bool;
    }

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

    public void play(){ //write your game logic here
        Scanner scan = new Scanner(System.in);
        initialize();

        while(player.getLives() != 0 && !player.getWin()) { //Keep the game playing when player has all lives and has not won
            
            //Clear the screen
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clearScreen(); // Clear the screen at the beggining of the while loop

            System.out.println("Game is running...");
            grid.display(); // Display grid
            System.out.println("Choose your direction: w/a/s/d: ");
            String direction = scan.nextLine();
            
          
        }


        if (player.getWin() == true) {
            grid.win();
        } else {
            grid.gameover();
        }
        
            
    }
            
     
    

    public void initialize() {
        this.grid = new Grid(size); // Create a new Grid full of dots based on size parameter
        this.player = new Player(size-1,0); // Create a new player starting at the bottom left
        grid.placeSprite(player);
        // Create lists for Sprites with multiple locations
        this.enemies = new Enemy[3]; 
        this.treasures = new Treasure[5]; 
        this.trophy = new Trophy(size-1, size-1); // Create a trophy placed at the top right
        grid.placeSprite(trophy);

        // Place each sprite on the board based on randomly generated coordinates
        for (int i = 0; i < enemies.length; i++) { 
            int x = generateCoord();
            int y = generateCoord();
            
            while (!(grid.getGrid()[x][y] instanceof Dot)) { // Generate new coordinates if the current coord is NOT a dot object
                x = generateCoord();
                y = generateCoord();
            }
            enemies[i] = new Enemy(x, y);
            grid.placeSprite(enemies[i]);
        }
        

        for (int i = 0; i < treasures.length; i++) {
            int x = generateCoord();
            int y = generateCoord();
            
            while (!(grid.getGrid()[x][y] instanceof Dot)) { // Generate new coordinates if the current coord is NOT a dot object
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
        Game game = new Game(10);
    }
}

  // Sprite current = null;
            // if (!player.isValid(size,direction) || !validInput(direction)) {
            //     System.out.println("This is not a valid move.");
            // } else {
            //     if (direction.equals("w")) {
            //         current = grid.getGrid()[player.getY()-1][player.getX()];
            //     }
            //     else if (direction.equals("a")) {
            //         current = grid.getGrid()[player.getY()][player.getX()];
            //     }
            //     else if (direction.equals("s")) {
            //         current = grid.getGrid()[player.getY()][player.getX() - 1];

            //     }
            //     else  {
            //         current = grid.getGrid()[player.getY() + 1][player.getX()];
            //     }
                
               //player.interact(size, direction, 5, current);
                
            
            //player.move(direction);
            //grid.placeSprite(player, direction);
                
                

                