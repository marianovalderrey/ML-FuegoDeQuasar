package com.ml.springbootml.entities;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

public class Satellite extends Spacecraft {
    
    @Autowired
    private float distance;
    @Autowired
    private String name;
    @Autowired
    private List<String> message = new ArrayList<>();
    //private Position position;

    public void setDistance (float distance){
        this.distance = distance;
    }
    public void setName (String name){
        this.name = name;
    }
    public void setMessage(List<String> message){
        this.message = message;
    }
    /*public void setPosition(Position position){
        this.position = position;
    }*/
    public float getDistance(){
        return this.distance;
    }
    public String getName(){
        return this.name;
    }
    public List<String> getMessage(){
        return this.message;
    }
    /*public Position getPosition(){
        return this.position;
    }*/
}