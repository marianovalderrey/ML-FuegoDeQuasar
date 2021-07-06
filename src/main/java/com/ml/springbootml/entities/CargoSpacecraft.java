package com.ml.springbootml.entities;

public class CargoSpacecraft extends Spacecraft {
	
    private String message;

	public CargoSpacecraft(Position position, String message) {
		this.setPosition(position);
        this.message = message;
	}

    public String getMessage (){
        return this.message;
    }
    public void setMessage (String message){
        this.message = message;
    }
}
