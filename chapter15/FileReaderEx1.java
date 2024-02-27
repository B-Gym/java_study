package chapter15;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderEx1 {
    public static void main(String[] args) {
        try {
            String fileName = "chapter15/test.txt";
            FileInputStream fis = new FileInputStream(fileName);
            FileReader fr = new FileReader(fileName);

            int data = 0;

            System.out.println("FileInputStream을 이용하여 파일 내용을 읽어 화면에 출력");
            while ((data = fis.read()) != -1) {
                System.out.print((char) data);
            }

            fis.close();

            System.out.println();

            System.out.println("FileReader를 이용하여 파일 내용을 읽어 화면에 출력");
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }

            fr.close();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// 실행 결과
// FileInputStream을 이용하여 파일 내용을 읽어 화면에 출력
// 12345
// íê¸ì
// ì¶ë ¥í©ëë¤.
// ABCDEFG
// FileReader를 이용하여 파일 내용을 읽어 화면에 출력
// 12345
// 한글을 출력합니다.
// ABCDEFG
