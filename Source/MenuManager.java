import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class MenuManager {
    public static JMenuBar createMenuBar(DrawingCanvas canvas) {
        JMenuBar menubar = new JMenuBar();
        
        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    ImageSaveLoad.saveImage(canvas, file);
                }
            }
        });
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    ImageSaveLoad.loadImage(canvas, file);
                }
            }
        });
        fileMenu.add(saveItem);
        fileMenu.add(openItem);
        menubar.add(fileMenu);
        
        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem canvasSizeItem = new JMenuItem("Canvas size");
        canvasSizeItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                CanvasDialog.showDialog(canvas);
            }
        });
        JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undoItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.getHistoryManager().undo(canvas);
            }
        });
        JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redoItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.getHistoryManager().redo(canvas);
            }
        });
        editMenu.add(canvasSizeItem);
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        menubar.add(editMenu);
        
        // View Menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem zoomInItem = new JMenuItem("Zoom in");
        zoomInItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK));
        zoomInItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                double currentZoom = canvas.getZoomFactor();
                canvas.setZoomFactor(currentZoom * 1.25);
            }
        });
        JMenuItem zoomOutItem = new JMenuItem("Zoom out");
        zoomOutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
        zoomOutItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                double currentZoom = canvas.getZoomFactor();
                canvas.setZoomFactor(currentZoom / 1.25);
            }
        });
        viewMenu.add(zoomInItem);
        viewMenu.add(zoomOutItem);
        menubar.add(viewMenu);
        
        // Options Menu (can add more later)
        JMenu optionsMenu = new JMenu("Options");
        menubar.add(optionsMenu);
        
        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem guideItem = new JMenuItem("Tool guide");
        guideItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                HelpDialog.showDialog(null);
            }
        });
        helpMenu.add(guideItem);
        menubar.add(helpMenu);
        
        return menubar;
    }
}
