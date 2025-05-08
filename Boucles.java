package bouclefor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class ForLoopVisualizer extends JFrame {
    private JTextField startField, endField, stepValueField;
    private JComboBox<String> stepBox, operatorBox;
    private JTextArea outputArea;
    private JButton runButton;

    public ForLoopVisualizer() {
        setTitle("Visualisation de la boucle for");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton helpButton = new JButton("?");
        helpButton.setFont(new Font("Arial", Font.BOLD, 14));
        helpButton.setMargin(new Insets(0, 5, 0, 5));
        helpButton.setToolTipText("Aide");
        helpButton.addActionListener(e -> showHelpDialog());
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(helpButton);
        add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        JPanel forPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forPanel.add(createBoldLabel("for ("));
        forPanel.add(createBoldLabel("int "));
        forPanel.add(new JLabel("i = "));
        startField = new JTextField(5);
        startField.setText("0"); 
        forPanel.add(startField);
        forPanel.add(createBoldLabel("; "));
        forPanel.add(new JLabel("i "));
        operatorBox = new JComboBox<>(new String[]{"<", "<=", ">", ">="});
        operatorBox.setSelectedItem("<="); 
        forPanel.add(operatorBox);
        endField = new JTextField(5);
        endField.setText("10"); 
        forPanel.add(endField);
        forPanel.add(createBoldLabel("; "));
        
        stepBox = new JComboBox<>(new String[]{"i++", "i--", "i+=", "i-="});
        stepBox.setSelectedItem("i++"); 
        forPanel.add(stepBox);
        
        stepValueField = new JTextField(3);
        stepValueField.setText("2"); 
        stepValueField.setVisible(false);
        forPanel.add(stepValueField);
        
        forPanel.add(createBoldLabel(")"));
        mainPanel.add(forPanel);

       
        JPanel bodyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bodyPanel.add(createBoldLabel("{"));
        bodyPanel.add(new JLabel("    System.out.println(i);"));
        bodyPanel.add(createBoldLabel("}"));
        mainPanel.add(bodyPanel);

        runButton = new JButton("Exécuter");
        mainPanel.add(runButton);

        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane);

        stepBox.addActionListener(e -> updateStepValueFieldVisibility());
        runButton.addActionListener(e -> executeLoop());
        
        SwingUtilities.invokeLater(() -> executeLoop());
    }

    private JLabel createBoldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }

    private void showHelpDialog() {
        String helpText = "<html><b>Explications:</b><br>"
                + "- <b>for (int i = début; condition; incrément) { ... }</b><br>"
                + "- Choisissez l'opérateur de comparaison <br>"
                + "- Le corps de la boucle affichera chaque valeur de i<br>";
        
        JOptionPane.showMessageDialog(this, helpText, "Aide", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateStepValueFieldVisibility() {
        String stepChoice = (String) stepBox.getSelectedItem();
        stepValueField.setVisible(stepChoice.equals("i+=") || stepChoice.equals("i-="));
        revalidate();
        repaint();
    }

    private void executeLoop() {
        outputArea.setText("");
        resetFieldColors();
        
        try {
            int start = Integer.parseInt(startField.getText());
            int end = Integer.parseInt(endField.getText());
            String operator = (String) operatorBox.getSelectedItem();
            String stepChoice = (String) stepBox.getSelectedItem();
            
            int step = 1;
            if (stepValueField.isVisible()) {
                try {
                    step = Integer.parseInt(stepValueField.getText());
                    if (step <= 0) {
                        showError("Le pas doit être un entier positif.", stepValueField);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    showError("Veuillez entrer un pas valide.", stepValueField);
                    return;
                }
            }

            StringBuilder output = new StringBuilder("Exécution de la boucle:\n\n");
            
            if (stepChoice.equals("i++") || stepChoice.equals("i+=")) {
                int actualStep = stepChoice.equals("i++") ? 1 : step;
                if (operator.equals("<")) {
                    for (int i = start; i < end; i += actualStep) {
                        output.append(String.format("%-10s", "i = " + i))
                              .append("   // ")
                              .append("System.out.println(").append(i).append(");\n");
                    }
                } else if (operator.equals("<=")) {
                    for (int i = start; i <= end; i += actualStep) {
                        output.append(String.format("%-10s", "i = " + i))
                              .append("   // ")
                              .append("System.out.println(").append(i).append(");\n");
                    }
                }
            } else if (stepChoice.equals("i--") || stepChoice.equals("i-=")) {
                int actualStep = stepChoice.equals("i--") ? 1 : step;
                if (operator.equals(">")) {
                    for (int i = start; i > end; i -= actualStep) {
                        output.append(String.format("%-10s", "i = " + i))
                              .append("   // ")
                              .append("System.out.println(").append(i).append(");\n");
                    }
                } else if (operator.equals(">=")) {
                    for (int i = start; i >= end; i -= actualStep) {
                        output.append(String.format("%-10s", "i = " + i))
                              .append("   // ")
                              .append("System.out.println(").append(i).append(");\n");
                    }
                }
            }
            outputArea.setText(output.toString());
        } catch (NumberFormatException ex) {
            showError("Veuillez entrer des nombres valides.", startField, endField);
        }
    }

    private void resetFieldColors() {
        startField.setBackground(Color.WHITE);
        endField.setBackground(Color.WHITE);
        stepValueField.setBackground(Color.WHITE);
    }

    private void showError(String message, JTextField... fields) {
        outputArea.setText(message);
        for (JTextField field : fields) {
            field.setBackground(Color.PINK);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ForLoopVisualizer visualizer = new ForLoopVisualizer();
            visualizer.setVisible(true);
        });
    }
}
