package org.png.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.png.entity.Employee;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {
}
