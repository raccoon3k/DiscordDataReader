import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSelecter {

    private File choosenFile = null;
    public File selectFile() {
        
        JFileChooser fileChooser = new JFileChooser();

        FileFilter filter = new FileNameExtensionFilter("JSON files", "json");
        fileChooser.setFileFilter(filter);

        if (choosenFile == null) {
            
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                choosenFile = new File(fileChooser.getSelectedFile().toString());
                return choosenFile;
            } else {
                System.exit(0);
                return null;
            }
        } else { 
            return choosenFile; 
        }
    }

    public File getChoosenFile() {
        return choosenFile;
    }
        
}
