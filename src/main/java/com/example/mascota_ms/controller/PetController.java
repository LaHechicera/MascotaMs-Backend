package com.example.mascota_ms.controller;

import com.example.mascota_ms.model.Pet;
import com.example.mascota_ms.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetRepository petRepository;

    @Autowired
    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * POST /api/pets
     * Crea una nueva mascota.
     */
    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        try {
            Pet savedPet = petRepository.save(pet);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPet);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * GET /api/pets/owner/{ownerId}
     * Obtiene todas las mascotas de un usuario.
     */
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Pet>> getPetsByOwner(@PathVariable String ownerId) {
        List<Pet> pets = petRepository.findByOwnerId(ownerId);
        return ResponseEntity.ok(pets); // Devuelve 200 OK con la lista (puede estar vacía)
    }

    /**
     * GET /api/pets/{id}
     * Obtiene una mascota por su ID.
     * Usamos ResponseEntity<?> para devolver un Pet en caso de éxito o una respuesta vacía en caso de error 404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable String id) {
        return petRepository.findById(id)
                .map(pet -> ResponseEntity.ok(pet)) // Si se encuentra -> 200 OK con el Pet
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra -> 404 Not Found
    }

    /**
     * PUT /api/pets/{id}
     * Actualiza una mascota existente.
     * Usamos ResponseEntity<?> para devolver un Pet en caso de éxito o una respuesta vacía en caso de error 404.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@PathVariable String id, @RequestBody Pet updatedPet) {
        return petRepository.findById(id)
                .map(existingPet -> { // Si se encuentra...
                    existingPet.setName(updatedPet.getName());
                    existingPet.setImageUrl(updatedPet.getImageUrl());
                    existingPet.setAnimalType(updatedPet.getAnimalType());
                    existingPet.setWeight(updatedPet.getWeight());
                    existingPet.setAllergies(updatedPet.getAllergies());
                    existingPet.setFood(updatedPet.getFood());
                    existingPet.setBio(updatedPet.getBio());

                    Pet savedPet = petRepository.save(existingPet);
                    return ResponseEntity.ok(savedPet); // -> 200 OK con el Pet actualizado
                })
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra -> 404 Not Found
    }

    /**
     * DELETE /api/pets/{id}
     * Elimina una mascota por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable String id) {
        if (!petRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            petRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // -> 204 No Content (éxito en borrado)
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}