package com.example.project;

// Construct a player object the user uses to play with
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { // The player starts with 0 treasures and 2 lives
        super(x, y);
        treasureCount = 0;
        numLives = 2;
        win = false;
    }

    public String toString() {return "🧚";} // Return fairy emoji for the player
    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

    public void setLives(int lives) {
        numLives = lives;
    }

    @Override
    public void move(String direction) { // Changes the player coordinates based on user input
        if (direction.equals("w")) {
            this.setY(this.getY() + 1);
        }
        if (direction.equals("a")) {
            this.setX(this.getX() - 1);
        }
        if (direction.equals("s")) {
            this.setY(this.getY() - 1);
        }
        if (direction.equals("d")) {
            this.setX(this.getX() + 1);
        }
    }

    public void interact(int size, String direction, int numTreasures, Object obj) { // Interact with an object in the position you are moving to 
        if (obj instanceof Enemy) {
            numLives--; // Get rid of a life if an enemy is interacted with
            System.out.println("This is an enemy! You now have " + numLives + " lives left.");
        }

        else if (obj instanceof Trophy) {
            if (treasureCount == numTreasures) {
                win = true; // User wins ONLY IF they have all the treasures
            }
            else { // If not, tell the user how many more treasures they need
                System.out.println("You still need " + (numTreasures - treasureCount) + " treasures to win.");
            }
        }
        else if (obj instanceof Treasure) {
            treasureCount++; // Increase treasure count if user comes across them
        }
    }


    public boolean isValid(int size, String direction) { // Check if the movement is valid within the boundaries of the grid
        boolean bool = true;
        // Check grid boundaries
        if (direction.equals("w")) {
            if (getY() + 1 >= size) {
                bool = false;
            }
        }
        if (direction.equals("a")) {
            if (getX() - 1 < 0) {
                bool = false;
            }
        }
        if (direction.equals("s")) {
            if (getY() - 1 < 0) {
                bool = false;
            }
        }
        if (direction.equals("d")) {
            if (getX() + 1 >= size) {
                bool = false;
            }
        }
        return bool;
    }
}