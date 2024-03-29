package org.hprtech.resource;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.hprtech.entity.Student;
import org.hprtech.repository.StudentRepository;
import org.jboss.resteasy.specimpl.ResponseBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class StudentResourceTest {

    @Inject
    private StudentResource studentResource;

    @InjectMock
    private StudentRepository studentRepository;

    Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setName("Aakash");
        student.setBranch("CSE");
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void getStudentList() {
        List<Student> studentList = Arrays.asList(new Student(1L, "Amit", "CSE"),
                new Student(2L, "Prakash", "ME"),
                new Student(3L, "Nikhil", "EE"));
        Mockito.when(studentRepository.listAll()).thenReturn(studentList);

        Response response = studentResource.getStudentList();
        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        List<Student> students = (List<Student>) response.getEntity();
        assertEquals(students.size(), 3);
    }

    @Test
    void getCSEStudents() {
    }

    @Test
    @TestSecurity(user = "testUser", roles = "teacher")
    void saveStudent() {
        Mockito.doNothing().when(studentRepository).persist(Mockito.any(Student.class));
        Mockito.when(studentRepository.isPersistent(Mockito.any(Student.class))).thenReturn(true);
        Response response = studentResource.saveStudent(student);
        assertNotNull(response);
        assertNotNull(response.getLocation());
        assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void getStudentById() {
        Student student1 = new Student(3L, "Subham", "CSE");
        Mockito.when(studentRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(student1));
        Response studentById = studentResource.getStudentById(3l);
        assertNotNull(studentById);
        assertNotNull(studentById.getEntity());
        assertEquals(studentById.getStatus(), Response.Status.OK.getStatusCode());
        Student studentFromResponse = (Student) studentById.getEntity();
        assertEquals(studentFromResponse.getName(), "Subham");
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void getStudentByIdForNoContent() {
        Student student1 = new Student(3L, "Subham", "CSE");
        Mockito.when(studentRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.empty());
        Response response = studentResource.getStudentById(3l);
        ResponseBuilderImpl rsp= (ResponseBuilderImpl)response.getEntity();
        System.out.println(rsp);
//        assertEquals(studentFromResponse.getName(), "Subham");
    }
}