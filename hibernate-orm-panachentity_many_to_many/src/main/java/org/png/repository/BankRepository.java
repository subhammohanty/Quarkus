package org.png.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.png.entity.Bank;

@ApplicationScoped
public class BankRepository implements PanacheRepository<Bank> {
}
