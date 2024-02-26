package org.hprtech.repository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.hprtech.entity.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class StudentRepositoryTest {

    @Inject
    private StudentRepository studentRepository;

    @Test
    void findAll(){
        List<Student> studentList = studentRepository.listAll();
        assertFalse(studentList.isEmpty());
        assertEquals(studentList.size(), 4);
    }

    @Test
    void findById(){
        Student student = studentRepository.findById(1L);
        assertEquals(student.getName(), "Pritish");
        assertEquals(student.getBranch(), "ME");
    }

}