package chapter15;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileConversion {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("chapter15/FileConversion.java");
            FileWriter fw = new FileWriter("chapter15/convert.txt");

            int data = 0;
            while ((data = fr.read()) != -1) {
                if (data != '\t' && data != '\n' && data != ' ' && data != '\r')
                    fw.write(data);
            }

            fr.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
