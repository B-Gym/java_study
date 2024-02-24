package chapter14;

import java.io.File;
import java.util.stream.Stream;

public class StreamEx2 {
    public static void main(String[] args) {
        File[] fileArr = {
                new File("Kimgoat.java"),
                new File("Kimgoat.jpg"),
                new File("Hello.java"),
                new File("Hello.pdf"),
                new File("New.txt")
        };

        Stream<File> filStream = Stream.of(fileArr);

        // map을 사용하여 Stream<File> -> Stream<String>으로 변환
        Stream<String> fileNameStream = filStream.map(File::getName);
        fileNameStream.forEach(System.out::println);

        System.out.println("파일의 이름만 (중복 제거)");
        filStream = Stream.of(fileArr);
        filStream.map(File::getName).filter(s -> s.indexOf('.') != 1).map(s -> s.substring(0, s.indexOf('.')))
                .distinct()
                .map(String::toUpperCase).forEach(System.out::println);

        System.out.println("파일 확장자만");
        filStream = Stream.of(fileArr);
        filStream.map(File::getName).filter(s -> s.indexOf('.') != 1).map(s -> s.substring(s.indexOf('.') + 1))
                .map(String::toUpperCase).forEach(System.out::println);
    }
}
