package org.png.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.png.entity.Aadhar;

@ApplicationScoped
public class AadharRepository implements PanacheRepository<Aadhar> {
}
