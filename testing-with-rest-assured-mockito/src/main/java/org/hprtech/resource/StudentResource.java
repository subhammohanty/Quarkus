package org.hprtech.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jdk.javadoc.doclet.Reporter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.hprtech.entity.Student;
import org.hprtech.repository.StudentRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/")
public class StudentResource {

    @Inject
    private StudentRepository studentRepository;

    @GET
    @Path("getAllStudent")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentList(){
        List<Student> studentList = studentRepository.listAll();
        return Response.ok(studentList).build();
    }

    @GET
    @Path("getAllCSEStudent")
    @RolesAllowed({"admin", "teacher"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCSEStudents(){
        List<Student> studentList = studentRepository.listAll();
        List<Student> cseStudentList = studentList.stream()
                .filter(s -> s.getBranch().equalsIgnoreCase("CSE"))
                .collect(Collectors.toList());
        return Response.ok(cseStudentList).build();
    }

    @POST
    @Path("saveStudent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed({"admin", "teacher"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveStudent(@RequestBody Student student){
        studentRepository.persist(student);
        if(studentRepository.isPersistent(student)){
            return Response.created(URI.create("/student/"+student.getStudentId())).build();
        }
        return Response.ok(Response.status(Response.Status.BAD_REQUEST)).build();
    }

    @GET
    @Path("student/{id}")
    @RolesAllowed({"admin", "teacher", "Student"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") Long id){
        Optional<Student> studentByIdOptional = studentRepository.findByIdOptional(id);
        if(studentByIdOptional.isPresent()){
            return Response.ok(studentByIdOptional.get()).build();
        }
        return Response.ok(Response.status(Response.Status.NOT_FOUND)).build();
    }
}
