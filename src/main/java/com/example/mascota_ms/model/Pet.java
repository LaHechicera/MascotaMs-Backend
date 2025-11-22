package com.example.mascota_ms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pets")
public class Pet {

    @Id
    private String id; // ID único autogenerado por MongoDB

    private String ownerId;
    private String name;
    private String imageUrl;
    private String animalType;
    private double weight;
    private String allergies;
    private String food;
    private String bio;

    // Constructor vacío (requerido por frameworks como JPA/MongoDB)
    public Pet() {
    }

    // Constructor con todos los campos
    public Pet(String ownerId, String name, String imageUrl, String animalType, double weight, String allergies, String food, String bio) {
        this.ownerId = ownerId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.animalType = animalType;
        this.weight = weight;
        this.allergies = allergies;
        this.food = food;
        this.bio = bio;
    }

    // --- Getters y Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}