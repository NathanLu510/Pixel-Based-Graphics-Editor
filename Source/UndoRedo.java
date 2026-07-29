import java.awt.image.BufferedImage;
import java.util.Stack;

public class UndoRedo {
    private Stack<BufferedImage> undoStack;
    private Stack<BufferedImage> redoStack;
    
    public UndoRedo() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
    
    public void pushState(BufferedImage state) {
        undoStack.push(state);
    }
    
    public void clearRedo() {
        redoStack.clear();
    }
    
    public void undo(DrawingCanvas canvas) {
        if (!undoStack.isEmpty()) {
            BufferedImage currentState = canvas.copyImage(canvas.getCanvasImage());
            redoStack.push(currentState);
            BufferedImage prevState = undoStack.pop();
            canvas.setCanvasImage(prevState);
            System.out.println("UNDO PERFORMED");
        }
    }
    
    public void redo(DrawingCanvas canvas) {
        if (!redoStack.isEmpty()) {
            BufferedImage currentState = canvas.copyImage(canvas.getCanvasImage());
            undoStack.push(currentState);
            BufferedImage nextState = redoStack.pop();
            canvas.setCanvasImage(nextState);
            System.out.println("REDO PERFORMED");
        }
    }
}
