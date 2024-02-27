package chapter15;

import java.io.FileInputStream;
import java.io.IOException;

public class FileViewer {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("chapter15/FileViewer.java");

        int data = 0;

        while ((data = fis.read()) != -1) {
            char x = (char) data;
            System.out.print(x);
        }
    }
}

// 실행 결과
// package chapter15;

// import java.io.FileInputStream;
// import java.io.IOException;

// public class FileViewer {
// public static void main(String[] args) throws IOException {
// FileInputStream fis = new FileInputStream("chapter15/FileViewer.java");

// int data = 0;

// while ((data = fis.read()) != -1) {
// char x = (char) data;
// System.out.print(x);
// }
// }
// }