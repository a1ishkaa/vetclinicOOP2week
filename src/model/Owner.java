package model;

import exception.InvalidInputException;

public class Owner extends Person {
    private int petsCount;

    public Owner(int id, String name, String phone, int petsCount) {
        super(id, name, phone);
        setPetsCount(petsCount);
    }

    public int getPetsCount() { return petsCount; }

    public void setPetsCount(int petsCount) {
        if (petsCount < 0) throw new InvalidInputException("Pets cannot be negative");
        this.petsCount = petsCount;
    }

    @Override
    public void work() {
        System.out.println(name + " owns " + petsCount + " pets.");
    }

    @Override
    public String getRole() {
        return "Owner";
    }
}
