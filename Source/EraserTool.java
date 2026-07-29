import java.awt.*;
import java.awt.event.MouseEvent;

public class EraserTool implements DrawingTool {
    private Point lastPoint = null;
    
    @Override
    public void mousePressed(DrawingCanvas canvas, MouseEvent e) {
        Point p = canvas.getGridCoordinates(e.getX(), e.getY());
        lastPoint = p;
        Graphics2D g2d = canvas.getCanvasImage().createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(p.x, p.y, 1, 1);
        g2d.dispose();
        canvas.repaint();
    }
    
    @Override
    public void mouseDragged(DrawingCanvas canvas, MouseEvent e) {
        Point p = canvas.getGridCoordinates(e.getX(), e.getY());
        if (lastPoint != null) {
            Graphics2D g2d = canvas.getCanvasImage().createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawLine(lastPoint.x, lastPoint.y, p.x, p.y);
            g2d.dispose();
            canvas.repaint();
        }
        lastPoint = p;
    }
    
    @Override
    public void mouseReleased(DrawingCanvas canvas, MouseEvent e) {
        lastPoint = null;
    }
}
