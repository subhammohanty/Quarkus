package org.png.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.png.entity.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
