package com.example.rest.Core;

public class RepException extends Throwable{
    public RepException(String msg) { super("Exception occurred: "+msg); }
}
