package com.example.rest.Core;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class Mapper implements ExceptionMapper<RepException> {
    @Override
    public Response toResponse(RepException e) {
        return Response.status(404).entity("Error: "+ e.getMessage()).build();
    }
}
