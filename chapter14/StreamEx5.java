package chapter14;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx5 {
    public static void main(String[] args) {
        String[] strArr = {
                "김밥", "주먹밥", "돈까스", "김치 라면", "쫄면", "비빔밥"
        };

        Stream.of(strArr).forEach(System.out::println);

        boolean noEmptyStr = Stream.of(strArr).noneMatch(s -> s.length() == 0);
        Optional<String> sWord = Stream.of(strArr)
                .filter(s -> s.charAt(0) == '김').findFirst();
        Optional<String> aWord = Stream.of(strArr)
                .filter(s -> s.charAt(0) == '김').parallel().findAny();

        System.out.println("noEmptyStr = " + noEmptyStr);
        System.out.println("sWord = " + sWord);
        System.out.println("aWord = " + aWord);

        // Stream<String[]>을 IntStream으로 변환
        IntStream is1 = Stream.of(strArr).mapToInt(String::length);
        IntStream is2 = Stream.of(strArr).mapToInt(String::length);
        IntStream is3 = Stream.of(strArr).mapToInt(String::length);
        IntStream is4 = Stream.of(strArr).mapToInt(String::length);

        int count = is1.reduce(0, (a, b) -> a + 1);
        int sum = is2.reduce(0, (a, b) -> a + b);

        OptionalInt max = is3.reduce(Integer::max);
        OptionalInt min = is4.reduce(Integer::min);

        System.out.println("count = " + count);
        System.out.println("sum = " + sum);
        System.out.println("max = " + max);
        System.out.println("min = " + min);
    }
}
