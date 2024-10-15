package ru.unibell.tarasov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.unibell.tarasov.entity.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findById(int id);
}
