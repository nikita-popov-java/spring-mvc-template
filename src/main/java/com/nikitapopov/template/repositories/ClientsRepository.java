package com.nikitapopov.template.repositories;

import com.nikitapopov.template.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {

    @Query("FROM Client WHERE email=?1")
    Optional<Client> find(String email);
}
