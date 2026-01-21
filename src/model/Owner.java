package model;

import exception.InvalidInputException;

public class Owner extends Person {

    private int numberOfPets;

    public Owner(int id, String name, String phone, int numberOfPets) {
        super(id, name, phone);
        setNumberOfPets(numberOfPets);
    }

    public void setNumberOfPets(int numberOfPets) {
        if (numberOfPets < 0) {
            throw new InvalidInputException("Pets cannot be negative");
        }
        this.numberOfPets = numberOfPets;
    }

    @Override
    public void work() {
        System.out.println(name + " owns " + numberOfPets + " pets.");
    }

    @Override
    public String getRole() {
        return "Owner";
    }
}