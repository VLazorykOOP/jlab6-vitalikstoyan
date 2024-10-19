import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Custom exception for matrix size errors
class MatrixSizeException extends ArithmeticException {
    public MatrixSizeException(String message) {
        super(message);
    }
}

// Main GUI class for matrix comparison
public class MatrixComparisonGUI extends JFrame {
    private JTextField matrixSizeField;
    private JTable tableA;
    private JTable tableB;
    private JButton compareButton;
    private JTextArea resultArea;

    public MatrixComparisonGUI() {
        // Set up the main frame
        setTitle("Matrix Comparison");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel for matrix size and file loading
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Matrix Size (n <= 15):"));
        matrixSizeField = new JTextField(5);
        inputPanel.add(matrixSizeField);
        JButton loadButton = new JButton("Load From File");
        inputPanel.add(loadButton);

        add(inputPanel, BorderLayout.NORTH);

        // Table for Matrix A
        String[] columnNames = {"Column 1", "Column 2", "Column 3", "Column 4", "Column 5", 
                                "Column 6", "Column 7", "Column 8", "Column 9", "Column 10", 
                                "Column 11", "Column 12", "Column 13", "Column 14", "Column 15"};
        DefaultTableModel modelA = new DefaultTableModel(columnNames, 15);
        tableA = new JTable(modelA);
        JScrollPane scrollPaneA = new JScrollPane(tableA);
        add(scrollPaneA, BorderLayout.WEST);

        // Table for Matrix B
        DefaultTableModel modelB = new DefaultTableModel(columnNames, 15);
        tableB = new JTable(modelB);
        JScrollPane scrollPaneB = new JScrollPane(tableB);
        add(scrollPaneB, BorderLayout.CENTER);

        // Compare Button
        compareButton = new JButton("Compare");
        add(compareButton, BorderLayout.SOUTH);
        
        // Result Area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.EAST);

        // Button Action Listeners
        loadButton.addActionListener(e -> loadFromFile());
        compareButton.addActionListener(e -> compareMatrices());

        setVisible(true);
    }

    // Method to load matrices from a file
    private void loadFromFile() {
        try {
            String filePath = JOptionPane.showInputDialog("Enter the file path:");
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            int n = Integer.parseInt(scanner.nextLine().trim());
            if (n > 15) throw new MatrixSizeException("Matrix size cannot exceed 15.");

            // Read matrix A
            for (int i = 0; i < n; i++) {
                String[] line = scanner.nextLine().trim().split("\\s+");
                for (int j = 0; j < n; j++) {
                    tableA.setValueAt(Integer.parseInt(line[j]), i, j);
                }
            }

            // Read matrix B
            for (int i = 0; i < n; i++) {
                String[] line = scanner.nextLine().trim().split("\\s+");
                for (int j = 0; j < n; j++) {
                    tableB.setValueAt(Integer.parseInt(line[j]), i, j);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Invalid data format!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (MatrixSizeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to compare the two matrices
    private void compareMatrices() {
        int n;
        try {
            n = Integer.parseInt(matrixSizeField.getText().trim());
            if (n > 15) throw new MatrixSizeException("Matrix size cannot exceed 15.");

            int[] X = new int[n];

            for (int i = 0; i < n; i++) {
                boolean isGreater = true;
                for (int j = 0; j < n; j++) {
                    int valueA = (int) tableA.getValueAt(i, j);
                    int valueB = (int) tableB.getValueAt(i, j);
                    if (valueA <= valueB) {
                        isGreater = false;
                        break;
                    }
                }
                X[i] = isGreater ? 1 : 0;
            }

            // Display result
            resultArea.setText("Result Vector X:\n");
            for (int value : X) {
                resultArea.append(value + " ");
            }

        } catch (MatrixSizeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid matrix size!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new MatrixComparisonGUI();
    }
}
