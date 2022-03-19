package com.example.rest.Core;

public class RepException extends Throwable{
    public RepException(String msg) {

        super(msg);
        output();

    }

    public String output() {
        return "You're good";
    }
}
