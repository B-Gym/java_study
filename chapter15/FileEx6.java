package chapter15;

import java.io.*;

public class FileEx6 {
    static int found = 0;

    public static void main(String[] args) {

        File dir = new File("chapter15");
        String keyword = "Kimgoat";

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리입니다");
            System.exit(0);
        }

        try {
            findInFiles(dir, keyword);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("총 " + found + "개의 라인에서 '" + keyword + "' 을/를 발견했습니다.");
    }

    public static void findInFiles(File dir, String keyword) throws IOException {
        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                findInFiles(files[i], keyword);
            } else {
                String fileName = files[i].getName();
                String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

                if (",java,txt,bak".indexOf(extension) == -1)
                    continue;

                fileName = dir.getAbsolutePath() + File.separator + fileName;

                FileReader fr = new FileReader(files[i]);
                BufferedReader br = new BufferedReader(fr);

                String data = "";
                int lineNum = 0;
                while ((data = br.readLine()) != null) {
                    lineNum++;

                    if (data.indexOf(keyword) != -1) {
                        found++;
                        System.out.printf("[%s (%d)] %s\n", fileName, lineNum, data);
                    }
                }
                br.close();
            }
        }
    }
}
