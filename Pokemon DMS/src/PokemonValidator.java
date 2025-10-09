/**
 * Name: Kiara Caballero
 * Course: CEN 3024C Software Development 1
 * Date: 10/05/2025
 *
 * Class Name: PokemonValidator
 *
 * Description:
 * This class is responsible for verifying that all data entered into the system
 * It makes sure that Pokemon records contain correct types, levels, genders, and trainer names.
 */

public class PokemonValidator {

    /**
     * Method Name: isValidName
     * Purpose: Checks if the given Pokemon name is valid (not null or empty).
     * Arguments: String name - the name to be validated
     * Return Value: boolean - true if valid, false otherwise
     */
    public boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Method Name: isValidLevel
     * Purpose: Validates that the level is between 1 and 100.
     * Arguments: int level - the Pokemon's level
     * Return Value: boolean - true if valid, false otherwise
     */
    public boolean isValidLevel(int level) {
        return level >= 1 && level <= 100;
    }

    /**
     * Method Name: isValidGender
     * Purpose: Checks if the gender input is valid (M, F, or Unknown).
     * Arguments: String gender - the gender to be validated
     * Return Value: boolean - true if valid, false otherwise
     */
    public boolean isValidGender(String gender) {
        if (gender == null) return false;
        gender = gender.trim().toUpperCase();
        return gender.equals("M") || gender.equals("F") || gender.equals("UNKNOWN");
    }

    /**
     * Method Name: isValidDaysStayed
     * Purpose: Ensures that the number of days stayed is not negative.
     * Arguments: int days - the number of days the Pokemon stayed
     * Return Value: boolean - true if valid, false otherwise
     */
    public boolean isValidDaysStayed(int days) {
        return days >= 0;
    }

    /**
     * Method Name: isValidTrainerName
     * Purpose: Checks that the trainer name is not null or empty.
     * Arguments: String trainerName - the trainer's name
     * Return Value: boolean - true if valid, false otherwise
     */
    public boolean isValidTrainerName(String trainerName) {
        return trainerName != null && !trainerName.trim().isEmpty();
    }

    /**
     * Method Name: isValidType
     * Purpose: Validates that the Pokemon type is not null or empty.
     * Arguments: String type - the Pokemon type
     * Return Value: boolean - true if valid, false otherwise
     */
    public boolean isValidType(String type) {
        return type != null && !type.trim().isEmpty();
    }
}