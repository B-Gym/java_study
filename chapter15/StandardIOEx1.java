package chapter15;

import java.io.IOException;

public class StandardIOEx1 {
    public static void main(String[] args) {
        try {
            int input = 0;

            while ((input = System.in.read()) != -1) {
                System.out.println("input : " + input + ", (char)input : " + (char) input);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

// 실행 결과
// hello
// input : 104, (char)input : h
// input : 101, (char)input : e
// input : 108, (char)input : l
// input : 108, (char)input : l
// input : 111, (char)input : o
// input : 10, (char)input :

// ^Z <- ctrl + Z를 눌러 입력의 끝(EOF)를 알림