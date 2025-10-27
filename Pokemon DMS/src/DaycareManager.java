/**
 * * Name: Kiara Caballero
 *  * Course: CEN 3024C Software Development 1
 *  * Date: 10/06/2025
 * Class Name: DaycareManager
 * Description:
 * The DaycareManager class maintains a list of Pokemon objects and does
 * all CRUD (Create, Read, Update, Delete) operations, as well as the
 * fee calculation. It interacts with the PokemonValidator class to ensure
 * all data is valid before being added or modified.
 */

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;


public class DaycareManager {
    private List<Pokemon> pokemonList;
    private PokemonValidator validator;

    //Constructor
    public DaycareManager() {
        this.pokemonList = new ArrayList<>();
        this.validator = new PokemonValidator();
    }

    /**
     * Method Name: addPokemon
     * Purpose: Adds a new Pokemon to the daycare
     * Arguments: Pokemon p
     * Return Value: boolean
     */
    public boolean addPokemon(Pokemon p) {
        if (p != null &&
                validator.isValidName(p.getName()) &&

                validator.isValidType(p.getType()) &&

                validator.isValidLevel(p.getLevel()) &&

                validator.isValidGender(p.getGender()) &&

                validator.isValidTrainerName(p.getTrainerName()) &&

                validator.isValidDaysStayed(p.getDaysStayed())) {

            pokemonList.add(p);
            return true;
        }
        return false;
    }

    /**
     * Method Name: removePokemon
     * Purpose: Removes a Pokemon by ID.
     * Arguments: int id
     * Return Value: boolean
     */
    public boolean removePokemon(int id) {
        return pokemonList.removeIf(p -> p.getPokemonId() == id);
    }

    /**
     * Method Name: updatePokemon
     * Purpose: Updates Pokemon details based on ID.
     * Arguments: int id, Pokemon updated - Pokemon ID and the updated object
     * Return Value: boolean - true if successful, false otherwise
     */
    public boolean updatePokemon(int id, Pokemon updated) {
        for (int i = 0; i < pokemonList.size(); i++) {
            if (pokemonList.get(i).getPokemonId() == id) {
                // Validate before replacing
                if (validator.isValidName(updated.getName()) &&
                        validator.isValidType(updated.getType()) &&
                        validator.isValidLevel(updated.getLevel()) &&
                        validator.isValidGender(updated.getGender()) &&
                        validator.isValidTrainerName(updated.getTrainerName()) &&
                        validator.isValidDaysStayed(updated.getDaysStayed())) {

                    pokemonList.set(i, updated);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method Name: getAllPokemon
     * Purpose: Retrieves all Pokemon in the daycare.
     * Arguments: None
     * Return Value: List<Pokemon>
     */
    public List<Pokemon> getAllPokemon() {
        return pokemonList;
    }

    /**
     * Method Name: findPokemonById
     * Purpose: Finds a Pokémon based on ID.
     * Arguments: int id
     * Return Value: Pokemon
     */
    public Pokemon findPokemonById(int id) {
        for (Pokemon p : pokemonList) {
            if (p.getPokemonId() == id) return p;
        }
        return null;
    }

    /**
     * Method Name: loadFromFile
     * Purpose: Reads Pokémon data from a text file and adds them to the daycare.
     * Arguments: String filename
     * Return Value: int
     */
    public int loadFromFile(String filename) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    try {
                        int id = Integer.parseInt(data[0].trim());
                        String name = data[1].trim();
                        String type = data[2].trim();
                        int level = Integer.parseInt(data[3].trim());
                        String gender = data[4].trim();
                        String trainerName = data[5].trim();
                        int daysStayed = Integer.parseInt(data[6].trim());

                        Pokemon p = new Pokemon(id, name, type, level, gender, trainerName, daysStayed);
                        if (addPokemon(p)) count++;
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return count;
    }
}


