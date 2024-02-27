package chapter15;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class IOEx4 {
    public static void main(String[] args) {
        byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        byte[] outSrc = null;
        byte[] temp = new byte[4];

        ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.out.println("Input Source : " + Arrays.toString(inSrc));

        try {
            while (input.available() > 0) {
                int len = input.read(temp);
                output.write(temp, 0, len);

                outSrc = output.toByteArray();

                System.out.println("temp         :" + Arrays.toString(temp));
                System.out.println("Output Source: " + Arrays.toString(outSrc));
            }
        } catch (IOException e) {
        }
    }
}

// 실행 결과
// Input Source : [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
// temp :[0, 1, 2, 3]
// Output Source: [0, 1, 2, 3]
// temp :[4, 5, 6, 7]
// Output Source: [0, 1, 2, 3, 4, 5, 6, 7]
// temp :[8, 9, 6, 7]
// Output Source: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]