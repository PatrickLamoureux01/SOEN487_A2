package com.example.soap.Endpoint;
// Maybe impl should be in Service
import com.example.soap.Service.LogInterfaceImpl;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import java.io.IOException;

import javax.xml.ws.Endpoint;
public class ServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/A2/LogInterface", new
                LogInterfaceImpl());
        System.out.println("Server is running");
    }
}
