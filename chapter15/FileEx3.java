package chapter15;

import java.io.File;
import java.util.ArrayList;

public class FileEx3 {
    static int totalFiles = 0;
    static int totalDirs = 0;

    public static void main(String[] args) {
        File dir = new File("chapter15");

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리입니다.");
            System.exit(0);
        }

        printFileList(dir);

        System.out.println();
        System.out.println("총 " + totalFiles + "개의 파일");
        System.out.println("총 " + totalDirs + "개의 폴더");
    }

    public static void printFileList(File dir) {
        System.out.println(dir.getAbsolutePath() + " 디렉토리");

        File[] files = dir.listFiles();

        ArrayList subDir = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();

            if (files[i].isDirectory()) {
                fileName = String.format("[%s]", fileName);
                subDir.add(i + "");
            }
            System.out.println(fileName);
        }

        int dirNum = subDir.size();
        int fileNum = files.length - dirNum;

        totalFiles += fileNum;
        totalDirs += dirNum;

        System.out.printf("%d개의 파일, %d개의 폴더\n\n", fileNum, dirNum);

        for (int i = 0; i < subDir.size(); i++) {
            int idx = Integer.parseInt((String) subDir.get(i));
            printFileList(files[idx]);
        }
    }
}
