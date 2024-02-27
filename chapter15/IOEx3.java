package chapter15;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

// 담을 배열(temp)의 크기가 담아야 하는 데이터의 양보다 작은 경우 예제
public class IOEx3 {
    public static void main(String[] args) {
        byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        byte[] outSrc = null;
        byte[] temp = new byte[4];

        ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.out.println("Input Source : " + Arrays.toString(inSrc));

        try {
            while (input.available() > 0) {
                input.read(temp);
                output.write(temp);

                outSrc = output.toByteArray();

                System.out.println("temp         :" + Arrays.toString(temp));
                System.out.println("Output Source: " + Arrays.toString(outSrc));
            }
        } catch (IOException e) {
        }

    }
}
