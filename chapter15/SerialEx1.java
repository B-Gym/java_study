package chapter15;

import java.io.*;
import java.util.ArrayList;

public class SerialEx1 {
    public static void main(String[] args) {
        try {
            String fileName = "chapter15/UserInfo.ser";
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            ObjectOutputStream out = new ObjectOutputStream(bos);

            UserInfo u1 = new UserInfo("Kimgoat", "1212", 23);
            UserInfo u2 = new UserInfo("New", "1111", 27);

            ArrayList<UserInfo> list = new ArrayList<>();
            list.add(u1);
            list.add(u2);

            // 객체를 직렬화
            out.writeObject(u1);
            out.writeObject(u2);
            out.writeObject(list);
            out.close();

            System.out.println("직렬화가 잘 끝났습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
