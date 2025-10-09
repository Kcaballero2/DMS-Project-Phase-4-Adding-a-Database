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



public class MainApplication {
    public static void main(String[] args) {
        DaycareManager manager = new DaycareManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
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

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Pokémon ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Level (1–100): ");
                    int level = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Gender (M/F/Unknown): ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter Trainer Name: ");
                    String trainerName = scanner.nextLine();
                    System.out.print("Enter Days Stayed: ");
                    int days = Integer.parseInt(scanner.nextLine());

                    Pokemon p = new Pokemon(id, name, type, level, gender, trainerName, days);
                    if (manager.addPokemon(p)) {
                        System.out.println("Pokémon added successfully!");
                    } else {
                        System.out.println("Error. Pokémon not added.");
                    }
                }

                case 2 -> {
                    System.out.print("Enter Pokémon ID to remove: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    if (manager.removePokemon(id)) {
                        System.out.println("Pokémon removed successfully!");
                    } else {
                        System.out.println("Pokémon not found.");
                    }
                }

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

                case 4 -> {
                    System.out.println("\n All Pokémon ");
                    for (Pokemon p : manager.getAllPokemon()) {
                        System.out.println(p);
                    }
                }

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
