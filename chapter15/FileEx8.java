package chapter15;

import java.io.File;

public class FileEx8 {
    static int deletedFiles = 0;

    public static void main(String[] args) {
        File dir = new File(System.getProperty("user.dir"));

        String extension = "dat";
        String ext = "." + extension;

        delete(dir, ext);
        System.out.println(deletedFiles + "개의 파일이 삭제되었습니다.");
    }

    static void delete(File dir, String ext) {
        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                delete(files[i], ext);
            } else {
                String fileName = files[i].getAbsolutePath();

                if (fileName.endsWith(ext)) {
                    System.out.print(fileName);

                    if (files[i].delete()) {
                        System.out.println(" - 삭제 성공");
                        deletedFiles++;
                    } else {
                        System.out.println(" - 삭제 실패");

                    }
                }
            }
        }
    }
}
