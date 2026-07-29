import javax.swing.*;
import java.awt.*;

public class HelpDialog {
    public static void showDialog(Component parent) {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JLabel pencilLabel = new JLabel("<html>Use Pencil to draw (black by default)</html>", new ImageIcon("icons/pencil2.png"), SwingConstants.LEFT);
        JLabel eraserLabel = new JLabel("<html>Eraser: draws white to erase pixels</html>", new ImageIcon("icons/rubber.png"), SwingConstants.LEFT);
        JLabel fillLabel = new JLabel("<html>Fill: flood-fill an area with the chosen color</html>", new ImageIcon("icons/fill2.png"), SwingConstants.LEFT);
        JLabel selectionLabel = new JLabel(new ImageIcon("icons/selection.png"), SwingConstants.LEFT);
        
        panel.add(pencilLabel);
        panel.add(eraserLabel);
        panel.add(fillLabel);
        panel.add(selectionLabel);
        
        JOptionPane.showMessageDialog(parent, panel, "Tool Guide", JOptionPane.INFORMATION_MESSAGE);
    }
}
