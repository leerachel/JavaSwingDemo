/**
 * 1. Change IMAGE_URL_PREFIX, DEFAULT_IMAGE_FILE at Line to your project file path
 *
 * 2. Customise font using variables declared at the beginning of the App class
 *    FONT_SIZE, FONT_NAME, DIALOG_FONT_SIZE, DIALOG_FONT_NAME
 *
 * 3. Customise Text Area Background and Font Colour
 *    Ctrl+F Search for the code below
 *    studentTextArea.setBackground(Color.BLACK); // Text Area Background
 *    studentTextArea.setForeground(Color.WHITE); // Text Font Colour
 *    hostTextArea.setBackground(Color.BLUE); // Text Area Background
 *    hostTextArea.setForeground(Color.YELLOW); // Text Font Colour
 *
 * 4. To add more students (aka Heng 5 etc), go to App class -> Main function
 *    Participant student5 = createStudent("Heng 5", "NooNoo", 1, "NooNoo.jpg", "");
 *    ADD 1 line of code at -> participantList.add(student5);
 *    MUST BE BEFORE this line -> participantList.add(host);
 *    because host is set as default LAST participant in participantList
 *
 *    Can add as many students as you can -> Remember to add the their images in images folder
 */

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class Participant {

    // Variables
    private String nameOfButton;
    private String fullName;
    private String tutorialGroup;
    private ImageIcon defaultImageFile;
    private ImageIcon selectedImageFile;
    private String text;

    // Setters
    public void setNameOfButton(String nameOfButton) {
        this.nameOfButton = nameOfButton;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setTutorialGroup(int tutorialGroup) {
        this.tutorialGroup = "T0" + tutorialGroup;
    }

    public void setDefaultImageFile(String imageFile) {
        this.defaultImageFile = new ImageIcon(new ImageIcon(imageFile).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
    }

    public void setSelectedImageFile(String imageFile) {
        this.selectedImageFile = new ImageIcon(new ImageIcon(imageFile).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
    }

    public void setText(String text) {
        this.text = text;
    }

    // Getters
    public String getNameOfButton() {
        return nameOfButton;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTutorialGroup() {
        return tutorialGroup;
    }

    public ImageIcon getDefaultImageFile() {
        return defaultImageFile;
    }

    public ImageIcon getSelectedImageFile() {
        return selectedImageFile;
    }

    public ImageIcon getDialogImageFile() {
        return new ImageIcon(selectedImageFile.getImage().getScaledInstance(80, 100, java.awt.Image.SCALE_SMOOTH));
    }

    public String getText() {
        return text;
    }
}

public class App extends JFrame{
    private static int ROW = 7;
    private static int COL = 60;
    private static int FONT_SIZE = 12;
    private static String FONT_NAME = "Consolas";
    private static int DIALOG_ROW = 7;
    private static int DIALOG_COL = 30;
    private static int DIALOG_FONT_SIZE = 12;
    private static String DIALOG_FONT_NAME = "Arial";
    private static String IMAGE_URL_PREFIX = "C:\\Users\\user\\IdeaProjects\\Q&ARoom\\src\\images\\";
    private static String DEFAULT_IMAGE_FILE = "C:\\Users\\user\\IdeaProjects\\Q&ARoom\\src\\images\\blank.png";

    private static List<Participant> participantList = new ArrayList<>();
    private static List<JButton> buttonList = new ArrayList<>();

    // Create Host
    public static Participant createHost(String nameOfButton, String imageFile, String text) {
        Participant host = new Participant();
        host.setNameOfButton(nameOfButton);
        host.setDefaultImageFile(IMAGE_URL_PREFIX + imageFile);
        host.setSelectedImageFile(IMAGE_URL_PREFIX + "hostReply.png");
        host.setText(text);
        return host;
    }

    // Create Student
    public static Participant createStudent(String nameOfButton, String fullName, int tutorialGroup, String imageFile, String text) {
        Participant student = new Participant();
        student.setNameOfButton(nameOfButton);
        student.setFullName(fullName);
        student.setTutorialGroup(tutorialGroup);
        student.setDefaultImageFile(DEFAULT_IMAGE_FILE);
        student.setSelectedImageFile(IMAGE_URL_PREFIX + imageFile);
        student.setText(text);
        return student;
    }

    public static void main(String args[]) {

        // Initialise Host & Students
        Participant host = createHost("Host", "host.jpg", "");
        Participant student1 = createStudent("Heng 1", "TinkyWinky", 1, "TinkyWinky.jpg", "");
        Participant student2 = createStudent("Heng 2", "Dipsy", 1, "Dipsy.jpg", "");
        Participant student3 = createStudent("Heng 3", "Po", 1, "Po.jpg", "");
        Participant student4 = createStudent("Heng 4", "LaaLaa", 1, "LaaLaa.jpg", "");
        Participant student5 = createStudent("Heng 5", "NooNoo", 1, "NooNoo.jpg", "");

        // Initialise participantList for Buttons - > Add host as the last participant
        participantList.add(student1);
        participantList.add(student2);
        participantList.add(student3);
        participantList.add(student4);
        participantList.add(student5); // Uncomment to remove student 5
        participantList.add(host); // Host must be LAST in the list

        // Creating the Frame
        JFrame frame = new JFrame("Q & A Room");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setBackground(Color.DARK_GRAY);
        Font font = new Font(FONT_NAME, Font.PLAIN, FONT_SIZE);
        Font dialogFont = new Font(DIALOG_FONT_NAME, Font.PLAIN, DIALOG_FONT_SIZE);

        // Creating Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(640, 40));
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Student Text Area at the Top -> User input calls for setText() in participant
        JTextArea studentTextArea = new JTextArea(ROW, COL);
        studentTextArea.setLineWrap(true);
        studentTextArea.setFont(font);
        studentTextArea.setBackground(Color.BLACK); // Text Area Background
        studentTextArea.setForeground(Color.WHITE); // Text Font Colour

        // Host Label and Text Area at Center -> User input calls for setText() in participant (host specifically)
        JLabel label = new JLabel("Host Area");
        JTextArea hostTextArea = new JTextArea(ROW, COL);
        hostTextArea.setLineWrap(true);
        hostTextArea.setFont(font);
        hostTextArea.setBackground(Color.BLUE); // Text Area Background
        hostTextArea.setForeground(Color.YELLOW); // Text Font Colour

        // 2 Text Area Listeners -> Updates whenever user changes input
        hostTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                String hostText = hostTextArea.getText().trim(); // Reads user input
                if (hostText.equals("")) { // Empty text area after clearing button pressed
                    return; // don't overwrite previous saved text
                }
                participantList.get(participantList.size()-1).setText(hostText);
                System.out.println(hostText);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                String hostText = hostTextArea.getText().trim(); // Reads user input
                if (hostText.equals("")) { // Empty text area after clearing button pressed
                    return; // don't overwrite previous saved text
                }
                participantList.get(participantList.size()-1).setText(hostText);
                System.out.println(hostText);
            }
            @Override
            public void changedUpdate(DocumentEvent arg0) {
                // Will not trigger this
            }
        });
        studentTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                String studentText = studentTextArea.getText().trim(); // Reads user input
                for (int i = 0; i < participantList.size()-1; i++) {
                    if (studentText.equals("")) { // Empty text area after clearing button pressed
                        break; // don't overwrite previous saved text
                    }
                    participantList.get(i).setText(studentText);
                    System.out.println(studentText);
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                String studentText = studentTextArea.getText().trim(); // Reads user input
                for (int i = 0; i < participantList.size()-1; i++) {
                    if (studentText.equals("")) { // Empty text area after clearing button pressed
                        break; // don't overwrite previous saved text
                    }
                    participantList.get(i).setText(studentText);
                    System.out.println(studentText);
                }
            }
            @Override
            public void changedUpdate(DocumentEvent arg0) {
                // Will not trigger this
            }
        });

        // Order in FlowLayout
        centerPanel.add(studentTextArea);
        centerPanel.add(label);
        centerPanel.add(hostTextArea);

        // Participant's Buttons + Mouse Listener + Button Listener
        for (int i = 0; i < participantList.size()-1; i++) {
            JButton button = new JButton(participantList.get(i).getNameOfButton(), participantList.get(i).getDefaultImageFile());
            buttonList.add(button);
            Participant participant = participantList.get(i);
            centerPanel.add(button);

            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) { // Mouse hover over button
                    button.setBackground(Color.ORANGE); // Student button changes to orange
                    button.setIcon(participant.getSelectedImageFile()); // Student's icon changes
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new JButton().getBackground()); // Student button changes back to default colour
                    button.setIcon(participant.getDefaultImageFile()); // Student's icon changes
                }
            });

            // Dialog Pop-Up using JOptionPane
            button.addActionListener(e -> {
                System.out.println("Student Button Clicked!");
                JTextArea participantDetails = new JTextArea(DIALOG_ROW, DIALOG_COL); // Participant Details
                participantDetails.setText(dialogFormatter(participant));
                participantDetails.setFont(dialogFont);
                participantDetails.setEditable(false);
                participantDetails.setLineWrap(true);
                participantDetails.setWrapStyleWord(true);
                ImageIcon participantImage = participant.getDialogImageFile(); // Participant Image
                int selected = JOptionPane.showConfirmDialog(
                        null,
                        participantDetails,
                        "Welcome to Chat Room",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        participantImage);
                if (selected == 2 || selected == 0) { // Clicking OK or Cancel does not matter
                    button.setIcon(participant.getDefaultImageFile()); // Student's icon changes
                }
                /*JOptionPane.showMessageDialog(
                        null,
                        participantDetails,
                        "Welcome to Chat Room", JOptionPane.INFORMATION_MESSAGE,
                        participantImage);*/
            });
        }

        // Host Button + Mouse Listener + Button Listener
        JButton hostButton = new JButton(participantList.get(participantList.size()-1).getNameOfButton(), participantList.get(participantList.size()-1).getDefaultImageFile());
        hostButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { // Mouse hover over button
                hostButton.setBackground(Color.GREEN); // Host button changes to green
                hostButton.setIcon(participantList.get(participantList.size()-1).getSelectedImageFile()); // Host's icon changes
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hostButton.setBackground(new JButton().getBackground()); // Host button changes back to default colour
                hostButton.setIcon(participantList.get(participantList.size()-1).getDefaultImageFile()); // Host's icon changes
            }
        });
        hostButton.addActionListener(e -> {
            System.out.println("Host Button Clicked!");
            hostButton.setBackground(Color.GREEN); // Host button changes to green
            JTextArea hostDetails = new JTextArea(DIALOG_ROW, DIALOG_COL); // Host Details
            hostDetails.setText(participantList.get(participantList.size()-1).getText());
            hostDetails.setFont(dialogFont);
            hostDetails.setEditable(false);
            hostDetails.setLineWrap(true);
            hostDetails.setWrapStyleWord(true);
            ImageIcon hostImage = participantList.get(participantList.size()-1).getDialogImageFile(); // Host Image
            int selected = JOptionPane.showConfirmDialog(
                    null,
                    hostDetails,
                    "I am the Host",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    hostImage);
            if (selected == 2 || selected == 0) {
                hostButton.setIcon(participantList.get(participantList.size()-1).getDefaultImageFile()); // Host's icon changes
            }
        });
        centerPanel.add(hostButton);

        // Clear Button + Listener
        ImageIcon cleanIcon = new ImageIcon(new ImageIcon(IMAGE_URL_PREFIX + "clean.jpg").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        JButton clearButton = new JButton("Clear", cleanIcon);
        clearButton.addActionListener(e -> {
            System.out.println("Clear Button Clicked!");
            studentTextArea.setText("");
            hostTextArea.setText("");
        });
        centerPanel.add(clearButton);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.setVisible(true);
    }

    // Dialog String Formatter
    public static String dialogFormatter(Participant participant) {
        return "Hi! I am participant " + participant.getNameOfButton() + "\n"
                + "My name is " + participant.getFullName() + "\n"
                + "I am from tutorial group: " + participant.getTutorialGroup() + "\n"
                + participant.getText();
    }
}

