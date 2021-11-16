package org.acme;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class ControllerEmployee {
    @Inject
    RepositoryEmployee rep;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }



    @Path("/con")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getEmployee(){
        return Response.ok(rep.getConnection()).build();
    }

    @GET
    @Path("/AllStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudent(){
        return Response.ok(rep.getAllStudent()).build();
    }

    @GET
    @Path("/get-student-by-id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") Integer id){
        return Response.ok(rep.getStudentById(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setStudent(Student student){
        rep.setStudent(student);
        return Response.ok(rep.getAllStudent()).build();
    }
}