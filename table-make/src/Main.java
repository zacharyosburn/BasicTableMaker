import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        //setting up the window to display the table that the user creates
        JFrame frame = new JFrame("Table Maker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1200);
        frame.setLocationRelativeTo(null);

        //table components
        JLabel row = new JLabel("Number of Rows");
        JTextField rowField = new JTextField(10);
        JLabel col = new JLabel("Number of Columns");
        JTextField colField = new JTextField(10);
        JButton generateButton = new JButton("Create Table");

        DefaultTableModel defaultModel = new DefaultTableModel();
        JTable table = new JTable(defaultModel);
        JScrollPane scrollPane = new JScrollPane(table);

        //panel for inputs
        JPanel inputPanel = new JPanel();
        inputPanel.add(row);
        inputPanel.add(rowField);
        inputPanel.add(col);
        inputPanel.add(colField);
        inputPanel.add(generateButton);

        //generate button
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int rows = Integer.parseInt(rowField.getText());
                    int cols = Integer.parseInt(colField.getText());

                    //clear existing table
                    defaultModel.setRowCount(0);
                    defaultModel.setColumnCount(0);

                    //setting up titling columns for user data
                    JPanel colTitlePanel = new JPanel();
                    JTextField[] colTitleField = new JTextField[cols];

                    for (int i = 0; i < cols; i++) {
                        colTitleField[i] = new JTextField(10);
                        colTitlePanel.add(new JLabel("Column " + (i + 1) + "Title:"));
                        colTitlePanel.add(colTitleField[i]);
                    }

                    int result = JOptionPane.showConfirmDialog(frame, colTitlePanel, "Enter Column Titles", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        String[] columnNames = new String[cols];
                        for (int i = 0; i < cols; i++) {
                            columnNames[i] = colTitleField[i].getText().isEmpty() ? "Column " + (i + 1) : colTitleField[i].getText();
                        }

                        for (String columnName : columnNames) {
                            defaultModel.addColumn(columnName);
                        }
                    }

                    for (int i = 0; i < rows; i++) {
                        Object[] rowData = new Object[cols];
                        for (int j = 0; j < cols; j++) {
                            rowData[j] = "";
                        }
                        defaultModel.addRow(rowData);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}