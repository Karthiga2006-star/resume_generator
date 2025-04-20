import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainFrame extends JFrame {
    private JTextField nameField, emailField, phoneField;
    private JTextArea summaryArea, skillsArea, experienceArea, educationArea;
    private JComboBox<String> templateDropdown;

    public MainFrame() {
        setTitle("Resume Builder");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set background color for the frame
        getContentPane().setBackground(new Color(230, 240, 250));

        // Create a form panel with better spacing and colors
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(250, 255, 240));  // Light cream background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title label
        JLabel titleLabel = new JLabel("Resume Builder", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(new Color(70, 130, 180));  // Steel blue color
        add(titleLabel, BorderLayout.NORTH);

        // Input fields
        createField(formPanel, gbc, "Name:", nameField = new JTextField());
        createField(formPanel, gbc, "Email:", emailField = new JTextField());
        createField(formPanel, gbc, "Phone:", phoneField = new JTextField());
        createArea(formPanel, gbc, "Summary:", summaryArea = new JTextArea(5, 20));
        createArea(formPanel, gbc, "Skills:", skillsArea = new JTextArea(5, 20));
        createArea(formPanel, gbc, "Experience:", experienceArea = new JTextArea(5, 20));
        createArea(formPanel, gbc, "Education:", educationArea = new JTextArea(5, 20));

        // Template selection dropdown
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Select Template:"), gbc);
        String[] templates = {"Classic", "Modern", "Creative"};
        templateDropdown = new JComboBox<>(templates);
        gbc.gridx = 1;
        formPanel.add(templateDropdown, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Export button at the bottom
        JButton exportButton = new JButton("Export Resume");
        exportButton.setFont(new Font("Arial", Font.BOLD, 18));
        exportButton.setBackground(new Color(70, 130, 180));  // Steel blue background
        exportButton.setForeground(Color.WHITE);
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToPDF();
            }
        });
        add(exportButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void createField(JPanel panel, GridBagConstraints gbc, String label, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void createArea(JPanel panel, GridBagConstraints gbc, String label, JTextArea area) {
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(area), gbc);
    }

    private void exportToPDF() {
        try {
            String selectedTemplate = (String) templateDropdown.getSelectedItem();
            PDFGenerator.generatePDF(
                "Resume.pdf",
                nameField.getText(),
                emailField.getText(),
                phoneField.getText(),
                summaryArea.getText(),
                skillsArea.getText(),
                experienceArea.getText(),
                educationArea.getText(),
                selectedTemplate
            );
            JOptionPane.showMessageDialog(this, "Resume exported successfully to Resume.pdf!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error exporting resume: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
