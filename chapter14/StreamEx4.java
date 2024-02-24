package chapter14;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamEx4 {
    public static void main(String[] args) {
        Stream<String[]> strArrStrm = Stream.of(
                new String[] { "abc", "def", "jkl" },
                new String[] { "123", "456", "789" });
        Stream<Stream<String>> strStrmStrm = strArrStrm.map(Arrays::stream);
        System.out.println("map()");
        strStrmStrm.forEach(System.out::println);
        // java.util.stream.ReferencePipeline$Head@7229724f
        // java.util.stream.ReferencePipeline$Head@4c873330
        System.out.println("-------------");

        strArrStrm = Stream.of(
                new String[] { "abc", "def", "jkl" },
                new String[] { "123", "456", "789" });
        Stream<String> strStrm = strArrStrm.flatMap(Arrays::stream);
        System.out.println("flatMap()");
        strStrm.forEach(System.out::println);
        // abc
        // def
        // jkl
        // 123
        // 456
        // 789
        System.out.println("-------------");

        String[] lineArr = {
                "You're My Pain Killer",
                "Don't Cry Snowman"
        };

        Stream<String> lineStrm = Stream.of(lineArr);
        lineStrm.flatMap(line -> Stream.of(line.split(" +")))
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        System.out.println("-------------");

        Stream<String> strStream1 = Stream.of("AAA", "ABC", "bBb", "Dd");
        Stream<String> strStream2 = Stream.of("bbb", "aaa", "ccc", "dd");

        Stream<Stream<String>> strStrmStrm2 = Stream.of(strStream1, strStream2);
        Stream<String> strStrm2 = strStrmStrm2
                .map(s -> s.toArray(String[]::new))
                .peek(s -> System.out.println("after map: " + s))
                .flatMap(Arrays::stream)
                .peek(s -> System.out.println("after flatMap: " + s));

        strStrm2.map(String::toLowerCase)
                .distinct()
                .forEach(System.out::println);

    }
}
