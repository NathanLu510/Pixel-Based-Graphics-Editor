import javax.swing.*;
import java.awt.event.*;

public class ToolbarManager {
    public static JToolBar createToolBar(DrawingCanvas canvas) {
        JToolBar toolbar = new JToolBar();
        toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.Y_AXIS));
        
        JButton pencilButton = new JButton();
        pencilButton.setIcon(new ImageIcon("icons/pencil2.png"));
        pencilButton.setBackground(java.awt.Color.white);
        pencilButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setCurrentTool(new PencilTool());
                canvas.setCurrentColor(java.awt.Color.BLACK);
            }
        });
        
        JButton eraserButton = new JButton();
        eraserButton.setIcon(new ImageIcon("icons/rubber.png"));
        eraserButton.setBackground(java.awt.Color.white);
        eraserButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setCurrentTool(new EraserTool());
            }
        });
        
        JButton fillButton = new JButton();
        fillButton.setIcon(new ImageIcon("icons/fill2.png"));
        fillButton.setBackground(java.awt.Color.white);
        fillButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setCurrentTool(new FillTool());
            }
        });
        
        JButton selectionButton = new JButton();
        selectionButton.setIcon(new ImageIcon("icons/selection.png"));
        selectionButton.setBackground(java.awt.Color.white);
        selectionButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Selection tool not implemented yet.");
            }
        });
        
        JButton layersButton = new JButton();
        layersButton.setIcon(new ImageIcon("icons/layers.png"));
        layersButton.setBackground(java.awt.Color.white);
        layersButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Layers functionality not implemented yet.");
            }
        });
        
        toolbar.add(pencilButton);
        toolbar.add(eraserButton);
        toolbar.add(fillButton);
        toolbar.add(selectionButton);
        toolbar.add(layersButton);
        
        return toolbar;
    }
}
