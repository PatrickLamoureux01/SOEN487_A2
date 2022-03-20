package com.example.rest.Core;

//import javax.xml.ws.WebFault;

import org.glassfish.grizzly.utils.Exceptions;

import javax.ws.rs.core.Response;
import java.lang.Exception;
import jakarta.xml.ws.WebFault;

@WebFault(name="RepException", targetNamespace = "http://localhost:8086/A2")
public class RepException extends Exception{
    public RepException(String msg) {
        super(msg);
    }

}
