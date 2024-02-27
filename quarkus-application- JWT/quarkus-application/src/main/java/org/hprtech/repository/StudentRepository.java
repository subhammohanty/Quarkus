package org.hprtech.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.hprtech.entity.Student;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {
}
