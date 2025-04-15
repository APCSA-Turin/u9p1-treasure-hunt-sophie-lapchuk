package com.example.project;

// Constructs all the Sprites on the board
public class Sprite {
    private int x, y;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public void setX(int num){
        x = num;
    }

    public void setY(int num) {
        y = num;
    }

    public String getCoords(){ // Returns "Sprite:(x,y)"
        String str = "";
        if (this instanceof Player) {
            str += "Player:";
        }
        if (this instanceof Treasure) {
            str += "Treasure:";
        }
        if (this instanceof Trophy) {
            str += "Trophy:";
        }
        if (this instanceof Enemy) {
            str += "Enemy:";
        }
        str += "(" + x + "," + y + ")"; // Formatting
        return str;
    }

    public String getRowCol(int size){ // Returns "Sprite:rowcol"
        String str = "";
        if (this instanceof Player) {
            str += "Player:";
        }
        if (this instanceof Treasure) {
            str += "Treasure:";
        }
        if (this instanceof Trophy) {
            str += "Trophy:";
        }
        if (this instanceof Enemy) {
            str += "Enemy:";
        }
        str += "["+ (size - 1 - y)+"]["+x+"]"; // Formatting
        return str;
    }
    

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }
}
