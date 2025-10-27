/**
 * Name: Kiara Caballero
 * Course: CEN 3024C Software Development 1
 * Date: 10/19/2025
 *
 * Class Name: DaycareGUI
 * Description:
 * This provides a GUI for the Pokémon Daycare Management System.
 * It allows users (staff) to add, remove, update, and view Pokémon data visually, as well as calculate fees
 * and load Pokémon data from a file.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Scanner;



public class DaycareGUI {

    private final DaycareManager manager = new DaycareManager();
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"ID", "Name", "Type", "Level", "Gender", "Trainer", "Days Stayed"}, 0
    );


    private static final String DATA_FILE = "pokemon_data.csv";

    /**
     * Method Name: GUI
     * Purpose: Builds and displays the GUI
     * Arguments: None
     * Return Value: None
     */
    public void GUI() {
        JFrame frame = new JFrame("Pokémon Daycare Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);


        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu manageMenu = new JMenu("Manage Pokémon");
        JMenu toolsMenu = new JMenu("Tools");


        JMenuItem loadItem = new JMenuItem("Load Pokémon from File");
        JMenuItem exitItem = new JMenuItem("Exit");


        JMenuItem addItem = new JMenuItem("Add Pokémon");
        JMenuItem removeItem = new JMenuItem("Remove Pokémon");
        JMenuItem updateItem = new JMenuItem("Update Pokémon");
        JMenuItem viewItem = new JMenuItem("View All Pokémon");


        JMenuItem feeItem = new JMenuItem("Calculate Fee");


        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        manageMenu.add(addItem);
        manageMenu.add(removeItem);
        manageMenu.add(updateItem);
        manageMenu.addSeparator();
        manageMenu.add(viewItem);

        toolsMenu.add(feeItem);


        menuBar.add(fileMenu);
        menuBar.add(manageMenu);
        menuBar.add(toolsMenu);

        frame.setJMenuBar(menuBar);


        JTable table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);


        addItem.addActionListener(e -> addPokemon());
        removeItem.addActionListener(e -> removePokemon(table));
        updateItem.addActionListener(e -> updatePokemon(table));
        viewItem.addActionListener(e -> viewAll());
        feeItem.addActionListener(e -> calculateFee(table));
        loadItem.addActionListener(e -> loadFromFile());
        exitItem.addActionListener(e -> System.exit(0));


        frame.setVisible(true);
    }



    /**
     * Method Name: addPokemon
     * Purpose: Adds a new Pokémon and saves to file
     * Arguments: None
     * Return Value: None
     */
    private void addPokemon() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Pokémon ID (1–999999999):"));
            String name = JOptionPane.showInputDialog("Enter Pokémon Name:");
            String type = JOptionPane.showInputDialog("Enter Type:");
            int level = Integer.parseInt(JOptionPane.showInputDialog("Enter Level (1–100):"));
            String gender = JOptionPane.showInputDialog("Enter Gender (M/F/Unknown):");
            String trainer = JOptionPane.showInputDialog("Enter Trainer Name:");
            int days = Integer.parseInt(JOptionPane.showInputDialog("Enter Days Stayed:"));

            Pokemon p = new Pokemon(id, name, type, level, gender, trainer, days);
            if (manager.addPokemon(p)) {
                tableModel.addRow(new Object[]{id, name, type, level, gender, trainer, days});
                saveToFile();
                JOptionPane.showMessageDialog(null, "Pokémon added successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Pokémon not added.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid number entered.");
        }
    }

    /**
     * Method Name: removePokemon
     * Purpose: Removes the selected Pokémon
     * Arguments: JTable table
     * Return Value: None
     */
    private void removePokemon(JTable table) {
        int selected = table.getSelectedRow();
        if (selected != -1) {
            int id = (int) tableModel.getValueAt(selected, 0);
            manager.removePokemon(id);
            tableModel.removeRow(selected);
            saveToFile();
            JOptionPane.showMessageDialog(null, "Pokémon removed.");
        } else {
            JOptionPane.showMessageDialog(null, "Select a Pokémon to remove.");
        }
    }

    /**
     * Method Name: updatePokemon
     * Purpose: Updates the selected Pokémon’s name
     * Arguments: JTable table
     * Return Value: None
     */
    private void updatePokemon(JTable table) {
        int selected = table.getSelectedRow();
        if (selected != -1) {
            try {
                int id = (int) tableModel.getValueAt(selected, 0);

                // Ask user for new details
                String newName = JOptionPane.showInputDialog("Enter new name:", tableModel.getValueAt(selected, 1));
                String newType = JOptionPane.showInputDialog("Enter new type:", tableModel.getValueAt(selected, 2));
                int newLevel = Integer.parseInt(JOptionPane.showInputDialog("Enter new level (1–100):", tableModel.getValueAt(selected, 3)));
                String newGender = JOptionPane.showInputDialog("Enter new gender (M/F/Unknown):", tableModel.getValueAt(selected, 4));
                String newTrainer = JOptionPane.showInputDialog("Enter new trainer name:", tableModel.getValueAt(selected, 5));
                int newDays = Integer.parseInt(JOptionPane.showInputDialog("Enter new days stayed:", tableModel.getValueAt(selected, 6)));


                Pokemon updated = new Pokemon(id, newName, newType, newLevel, newGender, newTrainer, newDays);


                if (manager.updatePokemon(id, updated)) {

                    tableModel.setValueAt(newName, selected, 1);
                    tableModel.setValueAt(newType, selected, 2);
                    tableModel.setValueAt(newLevel, selected, 3);
                    tableModel.setValueAt(newGender, selected, 4);
                    tableModel.setValueAt(newTrainer, selected, 5);
                    tableModel.setValueAt(newDays, selected, 6);
                    saveToFile();
                    JOptionPane.showMessageDialog(null, "Pokémon updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Update failed. Check data validity.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number entered.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a Pokémon to update.");
        }
    }

    /**
     * Method Name: viewAll
     * Purpose: Displays all Pokémon
     * Arguments: None
     * Return Value: None
     */
    private void viewAll() {
        JOptionPane.showMessageDialog(null, "All Pokémon are shown in the table below.");
    }

    /**
     * Method Name: calculateFee
     * Purpose: Calculates total daycare fee
     * Arguments: JTable table
     * Return Value: None
     */
    private void calculateFee(JTable table) {
        int selected = table.getSelectedRow();
        if (selected != -1) {
            int days = (int) tableModel.getValueAt(selected, 6);
            double fee;
            if (days <= 0) {
                fee = 0.0;
            } else if (days == 1) {
                fee = 5.0;
            } else {
                fee = 5.0 + (days - 1) * 3.0;
            }
            JOptionPane.showMessageDialog(null, "Total Fee: $" + fee);
        } else {
            JOptionPane.showMessageDialog(null, "Select a Pokémon ");
        }
    }


    /**
     * Method Name: loadFromFile
     * Purpose: Loads Pokémon data from the saved file
     * Arguments: None
     * Return Value: None
     */
    private void loadFromFile() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Pokémon Data File");

        int result = fileChooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "File loading canceled.");
            return;
        }

        File file = fileChooser.getSelectedFile();

        try (Scanner scanner = new Scanner(file)) {

            tableModel.setRowCount(0);
            manager.getAllPokemon().clear();

            int count = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] data = line.split(",");
                if (data.length == 7) {
                    try {
                        int id = Integer.parseInt(data[0].trim());
                        String name = data[1].trim();
                        String type = data[2].trim();
                        int level = Integer.parseInt(data[3].trim());
                        String gender = data[4].trim();
                        String trainer = data[5].trim();
                        int days = Integer.parseInt(data[6].trim());

                        Pokemon p = new Pokemon(id, name, type, level, gender, trainer, days);
                        if (manager.addPokemon(p)) {
                            tableModel.addRow(new Object[]{id, name, type, level, gender, trainer, days});
                            count++;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid line: " + line);
                    }
                }
            }

            JOptionPane.showMessageDialog(null, count + " Pokémon loaded successfully from:\n" + file.getName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }

    /**
     * Method Name: saveToFile
     * Purpose: Saves all Pokémon to the file
     * Arguments: None
     * Return Value: None
     */
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.write(tableModel.getValueAt(i, 0) + "," +
                        tableModel.getValueAt(i, 1) + "," +
                        tableModel.getValueAt(i, 2) + "," +
                        tableModel.getValueAt(i, 3) + "," +
                        tableModel.getValueAt(i, 4) + "," +
                        tableModel.getValueAt(i, 5) + "," +
                        tableModel.getValueAt(i, 6));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DaycareGUI().GUI());
    }
}
