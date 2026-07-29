import javax.swing.*;
import java.awt.*;

public class CanvasDialog {
    public static void showDialog(DrawingCanvas canvas) {
        JPanel panel = new JPanel();
        JTextField widthField = new JTextField(5);
        JTextField heightField = new JTextField(5);
        panel.add(new JLabel("Width:"));
        panel.add(widthField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Height:"));
        panel.add(heightField);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Enter canvas width and height", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int width = Integer.parseInt(widthField.getText());
                int height = Integer.parseInt(heightField.getText());
                canvas.editSize(width, height);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter numeric values.");
            }
        }
    }
}
