//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.*;

import static java.lang.Thread.sleep;

public class App extends JFrame{
    private static int SIMULATION_STUDENT_ID = 0; // participantList starts from 0
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
    private static boolean isReadyForReply = false;

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
        Participant host = createHost("Host", "host.jpg", "ANSWER");
        Participant student1 = createStudent("Heng 1", "TinkyWinky", 1, "TinkyWinky.jpg", "Student 1 The quick brown fox jumps over the lazy dog.");
        Participant student2 = createStudent("Heng 2", "Dipsy", 1, "Dipsy.jpg", "Student 2 The quick brown fox jumps over the lazy dog.");
        Participant student3 = createStudent("Heng 3", "Po", 1, "Po.jpg", "Student 3 The quick brown fox jumps over the lazy dog.");
        Participant student4 = createStudent("Heng 4", "LaaLaa", 1, "LaaLaa.jpg", "Student 4 The quick brown fox jumps over the lazy dog.");

        // Initialise participantList for Buttons - > Add host as the last participant
        participantList.add(student1);
        participantList.add(student2);
        participantList.add(student3);
        participantList.add(student4);
        participantList.add(host);

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

        // Student Text Area at the Top
        JTextArea studentTextArea = new JTextArea(ROW, COL);
        studentTextArea.setLineWrap(true);
        studentTextArea.setText("");
        studentTextArea.setFont(font);
        studentTextArea.setBackground(Color.BLACK); // Text Area Background
        studentTextArea.setForeground(Color.WHITE); // Text Font Colour

        // Host Label and Text Area at Center
        JLabel label = new JLabel("Host Area");
        JTextArea hostTextArea = new JTextArea(ROW, COL);
        hostTextArea.setLineWrap(true);
        hostTextArea.setText("");
        hostTextArea.setFont(font);
        hostTextArea.setBackground(Color.BLUE); // Text Area Background
        hostTextArea.setForeground(Color.YELLOW); // Text Area Colour

        // Order in FlowLayout
        centerPanel.add(studentTextArea);
        centerPanel.add(label);
        centerPanel.add(hostTextArea);

        // Participant's Buttons -> n Students, excluding Host
        for (int i = 0; i < participantList.size()-1; i++) {
            JButton button = new JButton(participantList.get(i).getNameOfButton(), participantList.get(i).getDefaultImageFile());
            buttonList.add(button);
            Participant participant = participantList.get(i);
            centerPanel.add(button);

            // Dialog Pop-Up using JOptionPane
            button.addActionListener(e -> {
                System.out.println("Button Clicked!");
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
                    isReadyForReply = true;
                    button.setIcon(participant.getDefaultImageFile()); // Student's icon changes
                }
                /*JOptionPane.showMessageDialog(
                        null,
                        participantDetails,
                        "Welcome to Chat Room", JOptionPane.INFORMATION_MESSAGE,
                        participantImage);*/
            });
        }

        // Host Button + Listener
        JButton hostButton = new JButton(participantList.get(participantList.size()-1).getNameOfButton(), participantList.get(participantList.size()-1).getDefaultImageFile());
        hostButton.addActionListener(e -> {
            System.out.println("Host Button Clicked!");
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
                isReadyForReply = false;
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

        // ================================ Simulate Chat Room ================================= //
        System.out.println("Simulation Started");
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        JButton student = buttonList.get(SIMULATION_STUDENT_ID);
        student.setIcon(participantList.get(SIMULATION_STUDENT_ID).getSelectedImageFile()); // Student's icon changes
        studentTextArea.setText(participantList.get(SIMULATION_STUDENT_ID).getText()); // Student asks a question
        // User clicks Student 1 button and Dialog pops up, User clicks OK to close dialog

        while (isReadyForReply == false) {
            try // Waits for Host to reply
            {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        hostButton.setIcon(participantList.get(participantList.size()-1).getSelectedImageFile()); // Host's icon changes
        hostTextArea.setText(participantList.get(participantList.size()-1).getText()); // Host replies to the question
        // User clicks Host button and Dialog pops up, User clicks OK to close dialog
        System.out.println("Simulation Ended");
    }

    // Dialog String Formatter
    public static String dialogFormatter(Participant participant) {
        return "Hi! I am participant " + participant.getNameOfButton() + "\n"
                + "My name is " + participant.getFullName() + "\n"
                + "I am from tutorial group: " + participant.getTutorialGroup() + "\n"
                + participant.getText();
    }
}
