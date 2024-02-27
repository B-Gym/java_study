package chapter15;

import java.io.*;

public class PipedReaderWriter {
    public static void main(String[] args) {
        InputThread inThread = new InputThread("InputThread");
        OutputThread outThread = new OutputThread("OutputThread");

        // PipedReader과 PipedWriter를 연결
        // inThread.connect(outThread.getOutput());
        outThread.connect(inThread.getInput());

        inThread.start();
        outThread.start();
    }
}

// 실행 결과
// OutputThread sent: Hello
// InputThread received: Hello

class InputThread extends Thread {
    PipedReader input = new PipedReader();
    StringWriter sw = new StringWriter();

    InputThread(String name) {
        super(name);
    }

    public void run() {
        try {
            int data = 0;

            while ((data = input.read()) != -1) {
                sw.write(data);
            }

            System.out.println(getName() + "  received: " + sw.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PipedReader getInput() {
        return input;
    }

    public void connect(PipedWriter output) {
        try {
            input.connect(output);
        } catch (IOException e) {
        }
    }
}

class OutputThread extends Thread {
    PipedWriter output = new PipedWriter();

    OutputThread(String name) {
        super(name);
    }

    public void run() {
        try {
            String msg = "Hello";
            System.out.println(getName() + "  sent: " + msg);
            output.write(msg);
            output.close();
        } catch (IOException e) {
        }
    }

    public PipedWriter getOutput() {
        return output;
    }

    public void connect(PipedReader input) {
        try {
            output.connect(input);
        } catch (IOException e) {
        }
    }
}
