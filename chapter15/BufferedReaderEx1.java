package chapter15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderEx1 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("chapter15/BufferedReaderEx1.java");
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            for (int i = 1; (line = br.readLine()) != null; i++) {
                if (line.indexOf(";") != -1)
                    System.out.println(i + ":" + line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// 실행 결과
// 1:package chapter15;
// 3:import java.io.BufferedReader;
// 4:import java.io.FileReader;
// 5:import java.io.IOException;
// 10: FileReader fr = new FileReader("chapter15/BufferedReaderEx1.java");
// 11: BufferedReader br = new BufferedReader(fr);
// 13: String line = "";
// 14: for (int i = 1; (line = br.readLine()) != null; i++) {
// 15: if (line.indexOf(";") != -1)
// 16: System.out.println(i + ":" + line);
// 18: br.close();
// 20: e.printStackTrace();