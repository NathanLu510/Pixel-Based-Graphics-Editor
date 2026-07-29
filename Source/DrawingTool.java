import java.awt.event.MouseEvent;



//An interface that every tool -pencil, eraser, fill, etc. will implement.

public interface DrawingTool {
    void mousePressed(DrawingCanvas canvas, MouseEvent e);
    void mouseDragged(DrawingCanvas canvas, MouseEvent e);
    void mouseReleased(DrawingCanvas canvas, MouseEvent e);
}
