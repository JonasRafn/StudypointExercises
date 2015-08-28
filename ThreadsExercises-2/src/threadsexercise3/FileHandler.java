package threadsexercise3;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JFileChooser;

class FileHandler implements Runnable {

    private List<String> lines;
    private File DEFAULTDIR = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString());
    private String FILENAME = "lines.txt";
    private File FINALPATH = new File(DEFAULTDIR, FILENAME);

    public FileHandler(List<String> lines) {
        this.lines = lines;
    }
    
    public void run() {
        while (true) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(FINALPATH, false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            try (PrintWriter out = new PrintWriter(writer)) {
                for (String str : lines) {
                    out.println(str);
                }
                System.out.println("Lines succesfully saved to file! " + "\nIn directory " + DEFAULTDIR);
            } catch (Exception ex) {
                System.out.println("Could not save lines to file!");
                ex.printStackTrace();
            }
            
            try {
                Thread.sleep(15000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
