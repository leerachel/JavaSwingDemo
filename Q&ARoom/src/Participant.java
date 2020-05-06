import javax.swing.*;

public class Participant {

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
