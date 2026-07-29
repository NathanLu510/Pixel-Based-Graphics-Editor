import javax.swing.*;
import java.awt.*;

//A JFrame subclass that lays out the main components (menu bar, canvas, toolbar, color chooser).
 
public class EditorFrame extends JFrame {
    
    public EditorFrame(JMenuBar menubar, JPanel panel, JToolBar toolbar, JColorChooser colorChooser) {
        super("Pixel Editor");
        setLayout(new BorderLayout());
        
        setJMenuBar(menubar);
        add(panel, BorderLayout.CENTER);
        
        JPanel leftPanel = new JPanel(new BorderLayout());
        toolbar.setFloatable(false);
        
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.add(Box.createVerticalGlue());
        wrapper.add(toolbar);
        wrapper.add(Box.createVerticalGlue());
        leftPanel.add(wrapper, BorderLayout.CENTER);
        
        // Example undo button (its action can also be set via the menu)
        JButton undoButton = new JButton("");
        undoButton.setIcon(new ImageIcon("icons/undo.png"));
        undoButton.setBackground(Color.white);
        leftPanel.add(undoButton, BorderLayout.SOUTH);
        
        add(leftPanel, BorderLayout.WEST);
        
        // Split pane for color chooser (or other components)
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JPanel topSplitPanel = new JPanel(new BorderLayout());
        topSplitPanel.setBackground(Color.WHITE);
        JPanel bottomSplitPanel = new JPanel();
        bottomSplitPanel.setBackground(Color.GRAY);
        splitPane.setTopComponent(topSplitPanel);
        splitPane.setBottomComponent(bottomSplitPanel);
        splitPane.setDividerLocation(400);
        splitPane.setContinuousLayout(true);
        splitPane.setPreferredSize(new Dimension(300, 800));
        
        colorChooser.setPreferredSize(new Dimension(300, 300));
        topSplitPanel.add(colorChooser, BorderLayout.CENTER);
        
        add(splitPane, BorderLayout.EAST);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
