import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSelecter {
    public File selectFile() {
        
        JFileChooser fileChooser = new JFileChooser();

        FileFilter filter = new FileNameExtensionFilter("JSON files", "json");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            System.exit(0);
            return null;
        }
    }
        
}
