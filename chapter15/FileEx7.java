package chapter15;

import java.io.File;
import java.io.FilenameFilter;

public class FileEx7 {
    public static void main(String[] args) {
        // String currDir = System.getProperty("user.dir");

        File dir = new File("chapter15");
        final String pattern = "FileEx";

        String[] files = dir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.indexOf(pattern) != -1;
            }
        });

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }
}
