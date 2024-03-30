package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Citizen;

@ApplicationScoped
public class CitizenRepository implements PanacheRepository<Citizen> {
}
