public class Veterinarian {

    private int vetId;
    private String name;
    private String specialization;
    private int experienceYears;
    private boolean available;

    public Veterinarian(int vetId, String name, String specialization, int experienceYears, boolean available) {
        this.vetId = vetId;
        this.name = name;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.available = available;
    }

    public Veterinarian() {
        this.vetId = 0;
        this.name = "Unknown";
        this.specialization = "General";
        this.experienceYears = 0;
        this.available = true;
    }

    public int getVetId() {
        return vetId;
    }
    public String getName() {
        return name;
    }
    public String getSpecialization() {
        return specialization;
    }
    public int getExperienceYears() {
        return experienceYears;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isExperienced() {
        return experienceYears >= 5;
    }
    public boolean canTreat(String animalType) {
        return specialization.equalsIgnoreCase(animalType)
                || specialization.equalsIgnoreCase("General");
    }

    @Override
    public String toString() {
        return "Veterinarian{vetId=" + vetId +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experienceYears=" + experienceYears +
                ", available=" + available +
                '}';
    }
}