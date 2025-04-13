package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size;
        this.grid = new Sprite[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Dot(row, col);
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;}



    public void placeSprite(Sprite s){ //place sprite in new spot
        if (s.getX() >= 0 && s.getX() < size && size - s.getY() - 1 >= 0 && size - s.getY() - 1 < size) {
            grid[size - 1 - s.getY()][s.getX()] = s;
        } else {
            System.out.println("Out of bounds.");
        }
        
    }



    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
   
        placeSprite(s);
        
        Dot dot = new Dot(0,0);



        if (direction.equals("w") && s.getY() - 1 >= 0) {

            dot.setY(s.getY()-1);
            dot.setX(s.getX());
            
        }
        else if (direction.equals("s") && s.getY() + 1 < size) {

            dot.setY(s.getY() + 1);
            dot.setX(s.getX());
            
        }
        else if (direction.equals("d") && s.getX() - 1 >= 0) {

  
            dot.setX(s.getX() - 1);
            dot.setY(s.getY());
            
        }
        else if (direction.equals("a") && s.getX() < size) {
            

            dot.setX(s.getX() + 1);
            dot.setY(s.getY());

        }

        placeSprite(dot);  

    
        
    }



    public void display() {
        System.out.println("DISPLAYING GRID");
         //print out the current grid to the screen 
         for (Sprite[] row : grid) {
            for (Sprite sprite : row) {
                System.out.print(sprite);
            }
            System.out.println();
         }
            
         
    }
    
    public void gameover(){ //use this method to display a loss
        System.out.println("☠️ Game Over.");
    }

    public void win(){ //use this method to display a win 
        System.out.println("🎉 You win!");
    }

    
  


}