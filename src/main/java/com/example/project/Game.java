package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size) { //the constructor should call initialize() and play()
        this.size = size;
        initialize();
        System.out.println("Initialized");
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


        while(player.getLives() != 0 && !player.getWin()){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            System.out.println("Game is running...");
            grid.display();
            System.out.println("Choose your direction: w/a/s/d: ");
            String direction = scan.nextLine();
            
            Sprite current = null;
            if (!player.isValid(size,direction) || !validInput(direction)) {
                System.out.println("This is not a valid move.");
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
                
            }
            player.move(direction);
            grid.placeSprite(player, direction);
                
                

                
        }
        if (player.getWin() == true) {
            grid.win();
        } else {
            grid.gameover();
        }
        
            
    }
            
     
    

    public void initialize(){

        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        this.grid = new Grid(size);
        this.player = new Player(size-1,0);
        grid.placeSprite(player);
        this.enemies = new Enemy[3];
        this.trophy = new Trophy(size-1, size-1);
        grid.placeSprite(trophy);

        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(generateX(), generateY());
            grid.placeSprite(enemies[i]);
        }
        
        this.treasures = new Treasure[5];
        for (int i = 0; i < treasures.length; i++) {
            treasures[i] = new Treasure(generateX(), generateY());
            grid.placeSprite(treasures[i]);
        }
    


        
        
   
    }

    public int generateX() {
        return (int) (Math.random() * size);
    }

    public int generateY() {
        return (int) (Math.random() * size);
    }

    public static void main(String[] args) {
        Game game = new Game(10);
        game.play();
    }
}