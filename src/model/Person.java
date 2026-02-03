package model;

import exception.InvalidInputException;

public abstract class Person {
    protected int id;
    protected String name;
    protected String phone;

    public Person(int id, String name, String phone) {
        setId(id);
        setName(name);
        setPhone(phone);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    public void setId(int id) {
        if (id <= 0) throw new InvalidInputException("ID must be positive");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 2)
            throw new InvalidInputException("Name too short");
        this.name = name.trim();
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty())
            throw new InvalidInputException("Phone is empty");
        this.phone = phone.trim();
    }

    public abstract void work();
    public abstract String getRole();

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name + " (ID=" + id + ", phone=" + phone + ")";
    }
}
