package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
        treasureCount = 0;
        numLives = 2;
        win = false;
    }

    public String toString() {return "🧚";}


    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

  
    //move method should override parent class, sprite
    @Override
    public void move(String direction) { //move the (x,y) coordinates of the player
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


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
        if (obj instanceof Enemy) {
            numLives--;
            System.out.println("This is an enemy! You now have " + numLives + " lives left.");
        }
        // if (numLives == 0) {
            
        // }
        else if (obj instanceof Trophy) {
            if (treasureCount == numTreasures) {
                win = true;
            }
            else {
                System.out.println("You still need " + (numTreasures - treasureCount) + " treasures to win.");
            }
        }
        else if (obj instanceof Treasure) {
            treasureCount++;
            System.out.println("You have collected " + treasureCount + "/" + numTreasures + "treasures!");
        }
        
        else if (obj instanceof Dot) {
            move(direction);
        }
    }


    public boolean isValid(int size, String direction){ 
        boolean bool = true;
        //check grid boundaries
        if (direction.equals("w")) {
            if (getY() + 1 >= size) {
                bool = false;
            }
            // if (!grid.getGrid()[getX()][getY() + 1] instanceof Dot) {
            //     bool = false;
            // }
        }

        if (direction.equals("a")) {
            if (getX() - 1 < 0) {
                bool = false;
            }
            // if (!grid.getGrid()[getX() - 1][getY()] instanceof Dot) {
            //     bool = false;
            // }
        }
        if (direction.equals("s")) {
            if (getY() - 1 < 0) {
                bool = false;
            }
            // if (!grid.getGrid()[getX()][getY() - 1] instanceof Dot) {
            //     bool = false;
            // }
        }
        if (direction.equals("d")) {
            if (getX() + 1 >= size) {
                bool = false;
            }
            // if (!grid.getGrid()[getX() + 1][getY()] instanceof Dot) {
            //     bool = false;
            // }
        }
        return bool;
    }


   
}



