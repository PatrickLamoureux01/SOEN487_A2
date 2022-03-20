package com.example.rest.Core;

import javax.xml.ws.WebFault;
import java.lang.Exception;

@WebFault(name="RepException", targetNamespace = "http://localhost:8080/A2")
public class RepException extends Exception{
    public RepException(String msg) {
        super(msg);
        System.out.println(msg);
    }
/*
    public void output() {
       return null;
    }*/
}

// THIS IS THE OLD RepException Code
/*
public class RepException extends Throwable{
    public RepException(String msg) {
        super(msg);
    }
 */