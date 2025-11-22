package com.example.mascota_ms.repository;

import com.example.mascota_ms.model.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends MongoRepository<Pet, String> {

    /**
     * Busca todas las mascotas que pertenecen a un dueño específico.
     */
    List<Pet> findByOwnerId(String ownerId);
}