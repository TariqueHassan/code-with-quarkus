package org.acme;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class ControllerEmployee {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @Inject
    RepositoryEmployee rep;

    @Path("/con")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getEmployee(){
        return Response.ok(rep.getConnection()).build();
    }

    @GET
    @Path("/AllStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPerson(){
        return Response.ok(rep.getAllStudent()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setStudent(Student student){
        rep.setStudent(student);
        return Response.ok(rep.getAllStudent()).build();
    }
}