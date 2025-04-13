package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite { //child  of Sprite
    
    public Enemy(int x, int y) {
        super(x,y);
    }

    @Override
    public String toString() {return "🟥";}


    //the methods below should override the super class 

    @Override
    public String getCoords(){ //returns "Enemy:"+coordinates
        return super.getCoords();
    }

    @Override
    public String getRowCol(int size) { //return "Enemy:"+row col
        return super.getRowCol(size);
    }
}