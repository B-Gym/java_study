package chapter15;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataInputStreamEx1 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("chapter15/test.dat");
            DataInputStream dis = new DataInputStream(fis);

            // chapter15/test.dat에 쓰인 순서대로 읽어야 한다.
            System.out.println(dis.readInt());
            System.out.println(dis.readFloat());
            System.out.println(dis.readBoolean());
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
