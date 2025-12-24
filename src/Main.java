public class Main {

    public static void main(String[] args) {

        System.out.println("=== Veterinary Clinic Management System ===\n");

        Pet pet1 = new Pet(1, "Buddy", "Dog", 1, "Ali");
        Pet pet2 = new Pet(2, "Murka", "Cat", 8, "Aigerim");
        Pet pet3 = new Pet();

        Owner owner1 = new Owner(101, "Ali", "+77001112233", 2, false);
        Owner owner2 = new Owner();

        Veterinarian vet1 = new Veterinarian(201, "Dr. Smith", "Dog", 6, true);
        Veterinarian vet2 = new Veterinarian();

        System.out.println("--- PETS ---");
        System.out.println(pet1);
        System.out.println(pet2);
        System.out.println(pet3);

        System.out.println("\n--- OWNERS ---");
        System.out.println(owner1);
        System.out.println(owner2);

        System.out.println("\n--- VETERINARIANS ---");
        System.out.println(vet1);
        System.out.println(vet2);

        System.out.println("\n--- TESTING METHODS ---");
        System.out.println(pet1.getName() + " life stage: " + pet1.getLifeStage());
        System.out.println(pet2.getName() + " is young: " + pet2.isYoung());

        owner1.addPet();
        owner1.addPet();
        System.out.println(owner1.getName() + " is VIP: " + owner1.isVIP());

        System.out.println(vet1.getName() + " experienced: " + vet1.isExperienced());
        System.out.println(vet1.getName() + " can treat cats: " + vet1.canTreat("Cat"));

        System.out.println("\n=== Program Complete ===");
    }
}
