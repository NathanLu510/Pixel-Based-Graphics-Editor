import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public class FillTool implements DrawingTool {
    @Override
    public void mousePressed(DrawingCanvas canvas, MouseEvent e) {
        Point p = canvas.getGridCoordinates(e.getX(), e.getY());
        floodFill(canvas, p.x, p.y, canvas.getCurrentColor());
    }
    
    @Override
    public void mouseDragged(DrawingCanvas canvas, MouseEvent e) {
        // No action on drag for fill tool.
    }
    
    @Override
    public void mouseReleased(DrawingCanvas canvas, MouseEvent e) {
        // No action on release for fill tool.
    }
    
    private void floodFill(DrawingCanvas canvas, int startX, int startY, Color replacementColor) {
        BufferedImage image = canvas.getCanvasImage();
        int width = image.getWidth();
        int height = image.getHeight();
        if (startX < 0 || startX >= width || startY < 0 || startY >= height) return;
        
        int targetRGB = image.getRGB(startX, startY);
        int replacementRGB = replacementColor.getRGB();
        if (targetRGB == replacementRGB) return;
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY));
        
        while (!queue.isEmpty()) {
            Point n = queue.poll();
            int nx = n.x;
            int ny = n.y;
            if (nx < 0 || nx >= width || ny < 0 || ny >= height)
                continue;
            if (image.getRGB(nx, ny) != targetRGB)
                continue;
            image.setRGB(nx, ny, replacementRGB);
            queue.add(new Point(nx + 1, ny));
            queue.add(new Point(nx - 1, ny));
            queue.add(new Point(nx, ny + 1));
            queue.add(new Point(nx, ny - 1));
        }
        canvas.repaint();
    }
}
