package com.example.project;

// Constructing a trophy that the player collects to win
public class Trophy extends Treasure { // Child of Treasure, grandchild of Sprite
    public Trophy(int x, int y) {
        super(x,y);
    }

    public String toString() {return "🏆";} // Return trophy emoji for the board
}
