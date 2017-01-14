package bawbcat.gtupdater;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Updater {

    public static void main(String[] args) {
        try {
            URL gtDownload = new URL("https://www.dropbox.com/s/64l33jw53c3l0eh/GroupTracker.jar?dl=1");
            URL pircDownload = new URL("https://www.dropbox.com/s/ktduu7wt1gbnp3l/pircbot.jar?dl=1");
            saveFile(gtDownload, "bot.jar");
            System.out.println("[GroupTrackerUpdate] Downloaded bot");
            File libFile = new File("lib");
            if (!libFile.exists()) {
                libFile.mkdirs();
            }
            saveFile(pircDownload, "lib/pircbot.jar");
            System.out.println("[GroupTrackerUpdate] Downloaded library");
            Runtime.getRuntime().exec("java -jar bot.jar");
            System.out.println("[GroupTrackerUpdate] Launched bot");
        } catch (IOException ex) {
            Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveFile(URL url, String destinationFile) throws IOException {
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
}
