package com.example.project;

// Constructing a treasure that the user collects
public class Treasure extends Sprite{ // Child of sprite
    public Treasure(int x, int y) {
        super(x,y);
    }

    public String toString() {return "🍭";} // Return lollipop to display on the board

}