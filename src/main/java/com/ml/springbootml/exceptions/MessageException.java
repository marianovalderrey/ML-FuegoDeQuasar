package com.ml.springbootml.exceptions;

public class MessageException extends Exception{
    
    public MessageException(String error){
        super(error);
    }
}