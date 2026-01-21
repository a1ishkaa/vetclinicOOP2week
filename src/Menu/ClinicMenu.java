package Menu;

import model.*;
import exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClinicMenu implements Menu {

    private ArrayList<Person> people = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("\n1. Add Owner");
        System.out.println("2. Add Vet");
        System.out.println("3. View All");
        System.out.println("4. Demo Work");
        System.out.println("0. Exit");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addOwner();
                    case 2 -> addVet();
                    case 3 -> viewAll();
                    case 4 -> demoWork();
                    case 0 -> running = false;
                }

            } catch (InvalidInputException e) {
                System.out.println("Validation error: " + e.getMessage());
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
    }

    private void addOwner() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Pets: ");
        int pets = scanner.nextInt();

        people.add(new Owner(id, name, phone, pets));
    }

    private void addVet() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Spec: ");
        String spec = scanner.nextLine();

        people.add(new Veterinarian(id, name, phone, spec));
    }

    private void viewAll() {
        for (Person p : people) {
            System.out.println(p);
        }
    }

    private void demoWork() {
        for (Person p : people) {
            p.work();
        }
    }
}