package com.example.project;

// Constructing a Dot sprite for every square on the board
public class Dot extends Sprite { // Child of sprite
    
    public Dot (int x, int y) {
        super(x,y);
    }

    @Override // Return a white square emoji for the object
    public String toString() {return "⬜️";}



}
