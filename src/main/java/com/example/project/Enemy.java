package com.example.project;

// Constructing an Enemy that takes player lives when interacted with
public class Enemy extends Sprite { // Child of Sprite
    
    public Enemy(int x, int y) {
        super(x,y);
    }

    @Override // Return a red square to symbolize an enemy on the board 
    public String toString() {return "🟥";}

    @Override
    public String getCoords(){ // Returns "Enemy: coordinates"
        return super.getCoords();
    }

    @Override
    public String getRowCol(int size) { // Returns "Enemy: row col"
        return super.getRowCol(size);
    }
}