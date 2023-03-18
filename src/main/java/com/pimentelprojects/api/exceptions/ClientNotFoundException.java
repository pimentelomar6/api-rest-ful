package com.pimentelprojects.api.exceptions;

public class ClientNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public ClientNotFoundException(String message){
        super(message);
    }
}
