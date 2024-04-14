import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveLister extends JFrame {
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public RecursiveLister() {
        setTitle("Recursive Directory Lister");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Recursive Directory Lister");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Use FlowLayout for button alignment

        JButton startButton = new JButton("Select Directory");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int result = fileChooser.showOpenDialog(RecursiveLister.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedDirectory = fileChooser.getSelectedFile();
                    listFiles(selectedDirectory);
                }
            }
        });
        buttonPanel.add(startButton); // Add "Select Directory" button to buttonPanel

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(quitButton); // Add "Quit" button to buttonPanel

        panel.add(buttonPanel, BorderLayout.SOUTH); // Add buttonPanel to the SOUTH of the main panel

        add(panel);
    }

    private void listFiles(File directory) {
        textArea.setText(""); // Clear previous content
        listFilesRecursive(directory);
    }

    private void listFilesRecursive(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFilesRecursive(file); // Recursively call for subdirectories
                } else {
                    textArea.append(file.getAbsolutePath() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RecursiveLister lister = new RecursiveLister();
                lister.setVisible(true);
            }
        });
    }
}
