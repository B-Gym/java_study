package chapter15;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileEx2 {
    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("chapter15/test.dat", "rw");
            System.out.println("position of file pointer: " + raf.getFilePointer());
            raf.writeInt(100);
            System.out.println("position of file pointer: " + raf.getFilePointer());
            raf.writeLong(100L);
            System.out.println("position of file pointer: " + raf.getFilePointer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
