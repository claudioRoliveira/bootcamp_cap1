package io.claudio.client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.claudio.client.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
