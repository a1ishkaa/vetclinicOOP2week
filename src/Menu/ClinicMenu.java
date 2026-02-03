package Menu;

import database.PersonDAO;
import model.*;

import java.util.List;
import java.util.Scanner;

public class ClinicMenu implements Menu {

    private final Scanner sc = new Scanner(System.in);
    private final PersonDAO dao = new PersonDAO();

    @Override
    public void displayMenu() {
        System.out.println("\n=== VET CLINIC MENU (Week 8) ===");
        System.out.println("1. Add Owner");
        System.out.println("2. Add Vet");
        System.out.println("3. View All");
        System.out.println("4. View Owners");
        System.out.println("5. View Vets");
        System.out.println("6. Update Owner");
        System.out.println("7. Update Vet");
        System.out.println("8. Delete By ID");
        System.out.println("9. Search by Name");
        System.out.println("10. Search Owners by Pets Range");
        System.out.println("11. Polymorphism Demo (work)");
        System.out.println("0. Exit");
    }

    @Override
    public void run() {
        boolean run = true;

        while (run) {
            displayMenu();
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> addOwner();
                    case 2 -> addVet();
                    case 3 -> print(dao.getAll());
                    case 4 -> print(dao.getOwnersOnly());
                    case 5 -> print(dao.getVetsOnly());
                    case 6 -> updateOwner();
                    case 7 -> updateVet();
                    case 8 -> deleteById();
                    case 9 -> searchName();
                    case 10 -> searchPets();
                    case 11 -> demoWork();
                    case 0 -> run = false;
                    default -> System.out.println("Wrong choice");
                }

            } catch (Exception e) {
                System.out.println("❌ Input error: " + e.getMessage());
            }
        }
    }

    private void addOwner() {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Pets count: ");
        int pets = Integer.parseInt(sc.nextLine());

        Owner o = new Owner(id, name, phone, pets);
        System.out.println(dao.insertOwner(o) ? "✅ Saved" : "❌ Not saved");
    }

    private void addVet() {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Specialization: ");
        String spec = sc.nextLine();

        Veterinarian v = new Veterinarian(id, name, phone, spec);
        System.out.println(dao.insertVet(v) ? "✅ Saved" : "❌ Not saved");
    }

    private void updateOwner() {
        System.out.print("Owner ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        Person p = dao.getById(id);
        if (!(p instanceof Owner o)) {
            System.out.println("❌ Not an owner / not found");
            return;
        }
        System.out.println("Current: " + o);

        System.out.print("New name: ");
        String name = sc.nextLine();
        System.out.print("New phone: ");
        String phone = sc.nextLine();
        System.out.print("New pets count: ");
        int pets = Integer.parseInt(sc.nextLine());

        Owner upd = new Owner(id, name, phone, pets);
        System.out.println(dao.updateOwner(upd) ? "✅ Updated" : "❌ Not updated");
    }

    private void updateVet() {
        System.out.print("Vet ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        Person p = dao.getById(id);
        if (!(p instanceof Veterinarian v)) {
            System.out.println("❌ Not a vet / not found");
            return;
        }
        System.out.println("Current: " + v);

        System.out.print("New name: ");
        String name = sc.nextLine();
        System.out.print("New phone: ");
        String phone = sc.nextLine();
        System.out.print("New specialization: ");
        String spec = sc.nextLine();

        Veterinarian upd = new Veterinarian(id, name, phone, spec);
        System.out.println(dao.updateVet(upd) ? "✅ Updated" : "❌ Not updated");
    }

    private void deleteById() {
        System.out.print("ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        Person p = dao.getById(id);
        if (p == null) {
            System.out.println("❌ Not found");
            return;
        }
        System.out.println("Delete: " + p);
        System.out.print("Type YES to confirm: ");
        String ok = sc.nextLine();

        if ("YES".equalsIgnoreCase(ok)) {
            System.out.println(dao.deleteById(id) ? "✅ Deleted" : "❌ Not deleted");
        } else {
            System.out.println("Cancelled");
        }
    }

    private void searchName() {
        System.out.print("Part of name: ");
        String part = sc.nextLine();
        print(dao.searchByName(part));
    }

    private void searchPets() {
        System.out.print("Min pets: ");
        int min = Integer.parseInt(sc.nextLine());
        System.out.print("Max pets: ");
        int max = Integer.parseInt(sc.nextLine());
        print(dao.searchOwnersByPetsRange(min, max));
    }

    private void demoWork() {
        List<Person> list = dao.getAll();
        for (Person p : list) p.work(); // polymorphism
    }

    private void print(List<Person> list) {
        if (list.isEmpty()) System.out.println("(empty)");
        else for (Person p : list) System.out.println(p);
    }
}
