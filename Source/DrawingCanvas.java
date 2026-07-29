import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DrawingCanvas extends JPanel implements MouseListener, MouseMotionListener {
    private BufferedImage canvasImage;
    private int pixelSize = 4;
    private int gridWidth = 500;
    private int gridHeight = 250;
    private double zoomFactor = 1.0;
    private Color currentColor = Color.BLACK;
    private UndoRedo undoredo;
    private DrawingTool currentTool;
    
    public DrawingCanvas() {
        setPreferredSize(new Dimension((int)(gridWidth * pixelSize * zoomFactor), (int)(gridHeight * pixelSize * zoomFactor)));
        canvasImage = new BufferedImage(gridWidth, gridHeight, BufferedImage.TYPE_INT_ARGB);
        clearCanvas();
        undoredo = new UndoRedo();
        // Default tool is pencil
        currentTool = new PencilTool();
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public void clearCanvas() {
        Graphics2D g2d = canvasImage.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, gridWidth, gridHeight);
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.dispose();
        repaint();
    }
    
    public void editSize(int setWidth, int setHeight) {
        this.gridWidth = setWidth;
        this.gridHeight = setHeight;
        canvasImage = new BufferedImage(gridWidth, gridHeight, BufferedImage.TYPE_INT_ARGB);
        updatePreferredSize();
        repaint();
    }
    
    public void setPixelSize(int newPixelSize) {
        this.pixelSize = newPixelSize;
        updatePreferredSize();
        repaint();
    }
    
    public void setZoomFactor(double factor) {
        this.zoomFactor = factor;
        updatePreferredSize();
        revalidate();
        repaint();
    }
    
    public double getZoomFactor() {
        return zoomFactor;
    }
    
    private void updatePreferredSize() {
        int w = (int)(gridWidth * pixelSize * zoomFactor);
        int h = (int)(gridHeight * pixelSize * zoomFactor);
        setPreferredSize(new Dimension(w, h));
    }
    
    public void setCurrentTool(DrawingTool tool) {
        this.currentTool = tool;
    }
    
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }
    
    public Color getCurrentColor() {
        return currentColor;
    }
    
    public BufferedImage getCanvasImage() {
        return canvasImage;
    }
    
    public void setCanvasImage(BufferedImage img) {
        this.canvasImage = img;
        repaint();
    }
    
    public UndoRedo getHistoryManager() {
        return undoredo;
    }
    
    // Helper: converts screen coordinates to grid coordinates.
    public Point getGridCoordinates(int x, int y) {
        int adjustedX = (int)(x / (zoomFactor * pixelSize));
        int adjustedY = (int)(y / (zoomFactor * pixelSize));
        return new Point(adjustedX, adjustedY);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(zoomFactor, zoomFactor);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, gridWidth * pixelSize, gridHeight * pixelSize);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.drawImage(canvasImage, 0, 0, gridWidth * pixelSize, gridHeight * pixelSize, null);
    }
    
    // Delegate mouse events to the current tool.
    @Override
    public void mousePressed(MouseEvent e) {
        undoredo.pushState(copyImage(canvasImage));
        undoredo.clearRedo();
        if (currentTool != null) {
            currentTool.mousePressed(this, e);
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentTool != null) {
            currentTool.mouseDragged(this, e);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentTool != null) {
            currentTool.mouseReleased(this, e);
        }
    }
    
    // Unused mouse events.
    @Override public void mouseClicked(MouseEvent e) { }
    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { }
    @Override public void mouseMoved(MouseEvent e) { }
    
    // Utility method to copy a BufferedImage.
    public BufferedImage copyImage(BufferedImage img) {
        BufferedImage copy = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics2D g2d = copy.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return copy;
    }
}
