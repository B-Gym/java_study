package chapter15;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class IOEx1 {
    public static void main(String[] args) {
        byte[] inSrc = { -128, 127 };
        byte[] outSrc = null;

        ByteArrayInputStream input = null;
        ByteArrayOutputStream output = null;

        input = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

        // while (input.read() != -1) {
        // output.write(input.read());
        // }
        int data = 0;
        while ((data = input.read()) != -1) {
            output.write(data);
        }

        outSrc = output.toByteArray();
        System.out.println("Input Source: " + Arrays.toString(inSrc));
        System.out.println("Output Source: " + Arrays.toString(outSrc));
    }
}
