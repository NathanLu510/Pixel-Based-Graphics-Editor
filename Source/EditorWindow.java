import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class EditorWindow {
    private DrawingCanvas canvas;
    private EditorFrame frame;
    
    public EditorWindow() {
        canvas = new DrawingCanvas();
        JMenuBar menuBar = MenuManager.createMenuBar(canvas);
        JToolBar toolBar = ToolbarManager.createToolBar(canvas);
        JColorChooser colorChooser = new JColorChooser(Color.BLACK);
        // Update the canvas color when a new color is picked.
        colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Color selectedColor = colorChooser.getColor();
                canvas.setCurrentColor(selectedColor);
            }
        });
        frame = new EditorFrame(menuBar, canvas, toolBar, colorChooser);
    }
}
