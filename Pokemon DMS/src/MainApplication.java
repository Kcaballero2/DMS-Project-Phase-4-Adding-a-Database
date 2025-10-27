/**
 * Name: Kiara Caballero
 * Course: CEN 3024C Software Development 1
 * Date: 10/08/2025
 *
 * Class Name: MainApplication
 *
 * Description:
 * The MainApplication class is the main area you will see
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Method Name: main
 * Purpose: Main system (with the buttons and what a user would see if this is all there is)
 * Arguments: String[] args
 * Return Value: void
 */


public class MainApplication {
    public static void main(String[] args) {
        DaycareManager manager = new DaycareManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        // Main loop
        while (running) {
            System.out.println("\n Pokémon Daycare Management System ");
            System.out.println("1. Add Pokémon");
            System.out.println("2. Remove Pokémon");
            System.out.println("3. Update Pokémon");
            System.out.println("4. View All Pokémon");
            System.out.println("5. Calculate Fee");
            System.out.println("6. Load Pokémon from File");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            /**
             * Case 1: Add Pokémon
             * Purpose: Allows staff to enter Pokémon details
             * Validates each input for correctness and checks the limits.
             */

            switch (choice) {
                case 1 -> {
                    int id = 0;
                    while (true) {
                        System.out.print("Enter Pokémon ID (1–999999999): ");
                        String input = scanner.nextLine().trim();
                        try {

                            long tempId = Long.parseLong(input);

                            if (tempId < 1 || tempId > 999_999_999) {
                                System.out.println("ID must be between 1 and 999999999. Try again.");
                                continue;
                            }

                            id = (int) tempId;
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter numbers only.");
                        }
                    }

                    String name;
                    while (true) {
                        System.out.print("Enter Name: ");
                        name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("Name cannot be empty.");
                        } else if (name.length() > 50) {
                            System.out.println("Name too long.");
                        } else {
                            break;
                        }
                    }

                    String type;
                    while (true) {
                        System.out.print("Enter Type: ");
                        type = scanner.nextLine().trim();
                        if (type.isEmpty()) {
                            System.out.println("Type cannot be empty.");
                        } else if (type.length() > 20) {
                            System.out.println("Type name too long. Keep it within 20 characters.");
                        } else {
                            break;
                        }
                    }

                    int level = 0;
                    while (true) {
                        System.out.print("Enter Level: ");
                        String input = scanner.nextLine();
                        try {
                            level = Integer.parseInt(input);
                            if (level < 1 || level > 100) {
                                System.out.println("Level must be between 1 and 100.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                        }
                    }

                    String gender;
                    while (true) {
                        System.out.print("Enter Gender (M/F/Unknown): ");
                        gender = scanner.nextLine().trim();
                        if (gender.equalsIgnoreCase("M") ||
                                gender.equalsIgnoreCase("F") ||
                                gender.equalsIgnoreCase("Unknown")) {
                            break;
                        } else {
                            System.out.println("Invalid gender");
                        }
                    }

                    String trainerName;
                    while (true) {
                        System.out.print("Enter Trainer Name: ");
                        trainerName = scanner.nextLine().trim();
                        if (trainerName.isEmpty()) {
                            System.out.println("Trainer name cannot be empty.");
                        } else if (trainerName.length() > 50) {
                            System.out.println("Trainer name too long. Try again");
                        } else {
                            break;
                        }
                    }

                    int days = 0;
                    while (true) {
                        System.out.print("Enter Days Stayed: ");
                        String input = scanner.nextLine();
                        try {
                            days = Integer.parseInt(input);
                            if (days < 0 || days > 365) {
                                System.out.println("Days must be between 0 and 365.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                    }

                    Pokemon p = new Pokemon(id, name, type, level, gender, trainerName, days);
                    if (manager.addPokemon(p)) {
                        System.out.println("Pokémon added successfully!");
                    } else {
                        System.out.println("Error. Pokémon not added.");
                    }
                }

                /**
                 * Case 2: Remove Pokémon
                 * Purpose: Removes a Pokémon from the daycare by its ID.
                 */


                case 2 -> {
                    System.out.print("Enter Pokémon ID to remove: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    if (manager.removePokemon(id)) {
                        System.out.println("Pokémon removed successfully!");
                    } else {
                        System.out.println("Pokémon not found.");
                    }
                }

                /**
                 * Case 3: Update Pokémon
                 * Purpose: Updates an existing Pokémon's via ID.
                 */

                case 3 -> {
                    System.out.print("Enter Pokémon ID to update: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Pokemon existing = manager.findPokemonById(id);
                    if (existing == null) {
                        System.out.println("Pokémon not found.");
                        break;
                    }

                    System.out.print("Enter new Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter new Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter new Level (1–100): ");
                    int level = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new Gender (M/F/Unknown): ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter new Trainer Name: ");
                    String trainerName = scanner.nextLine();
                    System.out.print("Enter new Days Stayed: ");
                    int days = Integer.parseInt(scanner.nextLine());

                    Pokemon updated = new Pokemon(id, name, type, level, gender, trainerName, days);
                    if (manager.updatePokemon(id, updated)) {
                        System.out.println("Pokémon updated successfully!");
                    } else {
                        System.out.println("Error. Pokémon not updated.");
                    }
                }

                /**
                 * Case 4: View All Pokémon
                 * Purpose: Gives you a list of all pokemon
                 */

                case 4 -> {
                    System.out.println("\n All Pokémon ");
                    for (Pokemon p : manager.getAllPokemon()) {
                        System.out.println(p);
                    }
                }

                /**
                 * Case 5: Calculate Fee
                 * Purpose: Calculates the total daycare fee for a Pokémon by ID.
                 */

                case 5 -> {
                    System.out.print("Enter Pokémon ID for fee calculation: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Pokemon p = manager.findPokemonById(id);
                    if (p != null) {
                        System.out.println("Total fee: $" + p.calculateFee());
                    } else {
                        System.out.println("Pokémon not found.");
                    }
                }

                /**
                 * Case 6: Load Pokémon from File
                 * Purpose: Reads Pokémon data from a txt file and loads it into the system.
                 */

                case 6 -> {
                    System.out.print("Enter the filename to load ");
                    String filename = scanner.nextLine();
                    File file = new File(filename);

                    try (Scanner fileScanner = new Scanner(file)) {
                        int count = 0;
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] parts = line.split(",");
                            if (parts.length == 7) {
                                int id = Integer.parseInt(parts[0].trim());
                                String name = parts[1].trim();
                                String type = parts[2].trim();
                                int level = Integer.parseInt(parts[3].trim());
                                String gender = parts[4].trim();
                                String trainerName = parts[5].trim();
                                int days = Integer.parseInt(parts[6].trim());

                                Pokemon p = new Pokemon(id, name, type, level, gender, trainerName, days);
                                manager.addPokemon(p);
                                count++;
                            }
                        }
                        System.out.println(count + " Pokémon loaded successfully from file.");
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found. Try again.");
                    } catch (Exception e) {
                        System.out.println("Error reading file: " + e.getMessage());
                    }
                }

                /**
                 * Case 7: Exit
                 * Purpose: Ends the program loop and closes the application.
                 */

                case 7 -> {
                    running = false;
                    System.out.println("Shutting down...");
                    System.out.println("System is closed.");
                    System.out.println("Have a nice day :D");
                }

                default -> System.out.println("Error. Try again.");
            }
        }

        scanner.close();
    }
}
