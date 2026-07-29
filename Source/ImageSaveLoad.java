import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageSaveLoad {
    public static void saveImage(DrawingCanvas canvas, File file) {
        try {
            ImageIO.write(canvas.getCanvasImage(), "png", file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadImage(DrawingCanvas canvas, File file) {
        try {
            BufferedImage img = ImageIO.read(file);
            if (img.getWidth() == canvas.getCanvasImage().getWidth() && img.getHeight() == canvas.getCanvasImage().getHeight()) {
                canvas.setCanvasImage(img);
            }
            canvas.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
