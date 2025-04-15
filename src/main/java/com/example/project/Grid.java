package com.example.project;

// Construct a grid to display all the sprites for the user
public class Grid{

    private Sprite[][] grid;
    private int size;

    public Grid(int size) { // Initialize and create a grid with all DOT objects
        this.size = size;
        this.grid = new Sprite[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Dot(row, col); // Placing a dot at every index
            }
        }
    }
 
    public Sprite[][] getGrid(){return grid;}



    public void placeSprite(Sprite s){ // Place sprite at it's designated x,y coordinates
        // Check boundaries of the grid
        if (s.getX() >= 0 && s.getX() < size && size - s.getY() - 1 >= 0 && size - s.getY() - 1 < size) {
            grid[size - 1 - s.getY()][s.getX()] = s; // Y is reversed, Y corresponds to row, X in order, X corresponds to column
        } else {
            System.out.println("Out of bounds.");
        }
        
    }



    public void placeSprite(Sprite s, String direction) { // Replace previous sprite location with a dot based on direction Sprite moves in
        placeSprite(s); // Place the sprite
        Dot dot = new Dot(0,0);

        // Each if statement will check grid boundaries
        if (direction.equals("w") && s.getY() - 1 >= 0) { // if W (Player is up), place dot under the sprite
            dot.setY(s.getY()-1);
            dot.setX(s.getX());
        }
        else if (direction.equals("s") && s.getY() + 1 < size) { // if S (Player is down), place dot above
            dot.setY(s.getY() + 1);
            dot.setX(s.getX());
        }
        else if (direction.equals("d") && s.getX() - 1 >= 0) { // if D (Player is right), place dot to the left
            dot.setX(s.getX() - 1);
            dot.setY(s.getY());
        }
        else if (direction.equals("a") && s.getX() < size) { // if A (Player is left), place dot to the right
            dot.setX(s.getX() + 1);
            dot.setY(s.getY());
        }

        placeSprite(dot); // Place the new dot with the proper coordinates
    }

    public void display() {
        System.out.println("DISPLAYING GRID");
         // Print out the grid with the corresponding emojis
         for (Sprite[] row : grid) {
            for (Sprite sprite : row) {
                System.out.print(sprite);
            }
            System.out.println();
         }
    }
    
    public void gameover(){ // Display a loss
        System.out.println("☠️ Game Over.");
    }

    public void win(){ // Display a win
        System.out.println("🎉 You win!");
    }
}