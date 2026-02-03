package model;

public class Pet {
    private final String name;
    private final String type;

    public Pet(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}
