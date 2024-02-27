package chapter15;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class StringReaderWriterEx {
    public static void main(String[] args) {
        String inputData = "Kimgoat";
        StringReader input = new StringReader(inputData);
        StringWriter output = new StringWriter();

        int data = 0;

        try {
            while ((data = input.read()) != -1) {
                output.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Input Data : " + inputData);
        System.out.println("Output Data : " + output.toString());
        System.out.println("Output Data : " + output.getBuffer());
        System.out.println("Output Data : " + output.getBuffer().toString());
    }
}

// 실행 결과
// Input Data : Kimgoat
// Output Data : Kimgoat
// Output Data : Kimgoat
// Output Data : Kimgoat
