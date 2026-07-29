public class Driver {
    public static void main(String[] args) {
        // Main method
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EditorWindow();
            }
        });
    }
}
