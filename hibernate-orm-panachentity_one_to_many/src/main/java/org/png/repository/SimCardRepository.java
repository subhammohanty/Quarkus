package org.png.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.png.entity.SimCard;

@ApplicationScoped
public class SimCardRepository implements PanacheRepository<SimCard> {
}
