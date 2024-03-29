package com.pimentelprojects.api.repository;

import com.pimentelprojects.api.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsById(Long id);
}
