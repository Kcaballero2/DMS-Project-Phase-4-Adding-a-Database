/**
 * Name: Kiara Caballero
 * Course: CEN 3024C Software Development 1
 * Date: 10/04/2025
 *
 * Class Name: Pokemon
 *
 * Description:
 * The Pokemon class will show one Pokémon in the Daycare Management System.
 * It stores all details such as name, type, level, gender, trainer name, and days stayed.
 * It will also give a method to calculate the daycare fee based on days stayed.
 */

public class Pokemon {
    private int pokemonId;
    private String name;
    private String type;
    private int level;
    private String gender;
    private String trainerName;
    private int daysStayed;

    // Constructor
    public Pokemon(int pokemonId, String name, String type, int level, String gender, String trainerName, int daysStayed) {
        this.pokemonId = pokemonId;
        this.name = name;
        this.type = type;
        this.level = level;
        this.gender = gender;
        this.trainerName = trainerName;
        this.daysStayed = daysStayed;
    }

    // Getter

    public int getPokemonId() { return pokemonId; }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public String getGender() {
        return gender;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public int getDaysStayed() {
        return daysStayed;
    }

    // Setters
    public void setPokemonId(int pokemonId) { this.pokemonId = pokemonId; }

    public void setName(String name) { this.name = name; }

    public void setType(String type) { this.type = type; }

    public void setLevel(int level) { this.level = level; }

    public void setGender(String gender) { this.gender = gender; }

    public void setTrainerName(String trainerName) { this.trainerName = trainerName; }

    public void setDaysStayed(int daysStayed) { this.daysStayed = daysStayed; }


    /**
     * Method: calculateFee
     * Why its needed: Calculates the daycare fee based on the number of days stayed.
     * There are no arguments
     * Return: the total fee calculated
     * Fee Structure:
     * $5 for the first day
     * $3 for each day after that first day
     */
    public double calculateFee() {
        if (daysStayed <= 0) {
            return 0.0;
        } else if (daysStayed == 1) {
            return 5.0;
        } else {
            return 5.0 + (daysStayed - 1) * 3.0;
        }
    }

    @Override
    public String toString() {
        return "Pokemon ID: " + pokemonId +
                ", Name: " + name +
                ", Type: " + type +
                ", Level: " + level +
                ", Gender: " + gender +
                ", Trainer: " + trainerName +
                ", Days Stayed: " + daysStayed +
                ", Fee: $" + calculateFee();
    }

}
