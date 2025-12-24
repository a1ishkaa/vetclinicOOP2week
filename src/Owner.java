public class Owner {

    private int ownerId;
    private String name;
    private String phone;
    private int numberOfPets;
    private boolean frequentClient;

    public Owner(int ownerId, String name, String phone, int numberOfPets, boolean frequentClient) {
        this.ownerId = ownerId;
        this.name = name;
        this.phone = phone;
        this.numberOfPets = numberOfPets;
        this.frequentClient = frequentClient;
    }

    public Owner() {
        this.ownerId = 0;
        this.name = "Unknown";
        this.phone = "Unknown";
        this.numberOfPets = 0;
        this.frequentClient = false;
    }

    public int getOwnerId() {
        return ownerId;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public int getNumberOfPets() {
        return numberOfPets;
    }
    public boolean isFrequentClient() {
        return frequentClient;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setNumberOfPets(int numberOfPets) {
        this.numberOfPets = numberOfPets;
    }
    public void setFrequentClient(boolean frequentClient) {
        this.frequentClient = frequentClient;
    }

    public void addPet() {
        numberOfPets++;
        if (numberOfPets >= 3) {
            frequentClient = true;
        }
    }
    public boolean isVIP() {
        return frequentClient;
    }

    @Override
    public String toString() {
        return "Owner{ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", numberOfPets=" + numberOfPets +
                ", frequentClient=" + frequentClient +
                '}';
    }
}
