package model;

import exception.InvalidInputException;

public class Veterinarian extends Person implements Treatable {

    private String specialization;

    public Veterinarian(int id, String name, String phone, String specialization) {
        super(id, name, phone);
        setSpecialization(specialization);
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isEmpty()) {
            throw new InvalidInputException("Specialization required");
        }
        this.specialization = specialization;
    }

    @Override
    public void treatPet(Pet pet) {
        System.out.println("Dr. " + name + " treats " + pet);
    }

    @Override
    public void work() {
        System.out.println("Dr. " + name + " works as " + specialization);
    }

    @Override
    public String getRole() {
        return "Veterinarian";
    }
}