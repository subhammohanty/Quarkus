package org.hprtech.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.hprtech.entity.Student;
import org.hprtech.exception.BusinessException;
import org.hprtech.exception.TechnicalException;
import org.hprtech.repository.StudentRepository;

import javax.print.attribute.standard.Media;
import java.net.URI;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    private StudentRepository studentRepository;

    @POST
    @Path("addStudent")
    @Transactional
    public Response addStudent(@RequestBody Student student) throws BusinessException {
        if(student == null){
            throw new BusinessException(Response.Status.BAD_REQUEST.getStatusCode(), "Student Object Cannot be null");
        }
        studentRepository.persist(student);
        if(studentRepository.isPersistent(student)){
            return Response.created(URI.create("/student/" +student.getStudentId())).build();
        }
        return Response.ok(Response.status(Response.Status.BAD_REQUEST)).build();
    }

    @GET
    @Path("getAllStudents")
    @Transactional
    public Response getStudentList(){
        return Response.ok(studentRepository.listAll()).build();
    }

    @GET
    @Path("student/{id}")
    @Transactional
    public Response getStudentById(@PathParam("id") Long id) throws BusinessException {
        Student student = studentRepository.findById(id);
        if(student != null){
            return Response.ok(student).build();
        }else {
            throw new BusinessException(Response.Status.NOT_FOUND.getStatusCode(), "Student with ID: " +id+" Not Found In DB");
        }
    }

    //For Technical Exception ex: divide by 0
    @GET
    @Path("divide/{i}")
    @Transactional
    public Response getStudentById(@PathParam("i") Integer i) throws TechnicalException {
        try{
            int out = 8/i;
            return Response.ok("Output after dividing: " + out).build();
        }catch (Exception e){
            throw new TechnicalException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
        }
    }

}
