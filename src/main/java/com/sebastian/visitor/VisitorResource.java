package com.sebastian.visitor;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/visitors")
@Produces(MediaType.APPLICATION_JSON)
public class VisitorResource {

    @GET
    public Response getVisitors() {
        return Response.ok().build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVisitor(VisitorEntity visitor) {
        return Response.ok().entity(visitor).build();
    }
}
