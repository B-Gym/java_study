package chapter15;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class DataInputStreamEx3 {
    public static void main(String[] args) {
        int sum = 0;
        int score = 0;

        // JDK1.7부터 try-with-resources문을 이용하면 close()를 직접 호출하지 않아도 자동 호출되도록 할 수 있음
        try (FileInputStream fis = new FileInputStream("chapter15/socre.dat");
                DataInputStream dis = new DataInputStream(fis)) {
            while (true) {
                score = dis.readInt();
                System.out.println(score);
                sum += score;
            }
        } catch (EOFException e) {
            System.out.println("total score: " + sum);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
