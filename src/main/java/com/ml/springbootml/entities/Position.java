package com.ml.springbootml.entities;

public class Position {
    
    private float x;
    private float y;
    
    public Position (String position){
        String[] positionArray = position.split(",");
        this.x = Float.parseFloat(positionArray[0]);
        this.y = Float.parseFloat(positionArray[1]);
    }
    
    public Position (float x, float y){
        this.x = x;
        this.y = y;
    }
    public void setX (float x){
        this.x = x;
    }
    public void setY (float y){
        this.y = y;
    }
    public float getX (){
        return this.x;
    }
    public float getY (){
        return this.y;
    }
}