package chapter14;

import java.util.Optional;
import java.util.OptionalInt;

public class OptionalEx1 {
    public static void main(String[] args) {
        Optional<String> optStr = Optional.of("Hello");
        Optional<Integer> optInt = optStr.map(String::length);
        System.out.println("optStr = " + optStr.get()); // optStr = Hello
        System.out.println("optInt = " + optInt.get()); // optInt = 5

        int result1 = Optional.of("123")
                .filter(x -> x.length() > 0)
                .map(Integer::parseInt).get();
        int result2 = Optional.of("")
                .filter(x -> x.length() > 0)
                .map(Integer::parseInt).orElse(-1);
        System.out.println("result1 = " + result1); // result1 = 123
        System.out.println("result2 = " + result2); // result2 = -1

        Optional.of("456").map(Integer::parseInt)
                .ifPresent(x -> System.out.printf("result3 = %d \n", x));

        OptionalInt optInt1 = OptionalInt.of(0); // 0 저장
        OptionalInt optInt2 = OptionalInt.empty(); // 빈 객체 생성
        System.out.println(optInt1.isPresent()); // true
        System.out.println(optInt2.isPresent()); // false

        System.out.println(optInt1.getAsInt()); // 0
        // System.out.println(optInt2.getAsInt()); // NoSuchElementException발생
        System.out.println("optInt1 = " + optInt1); // optInt1 = OptionalInt[0]
        System.out.println("optInt2 = " + optInt2); // optInt2 = OptionalInt.empty
        System.out.println("optInt1.equals(optInt2) = " + optInt1.equals(optInt2)); // optInt1.equals(optInt2) = false

        Optional<String> opt = Optional.ofNullable(null); // null 저장
        Optional<String> opt2 = Optional.empty(); // 빈 객체 생성
        System.out.println("opt = " + opt); // opt = Optional.empty
        System.out.println("opt2 = " + opt2); // opt2 = Optional.empty
        System.out.println("opt.equals(opt2)" + opt.equals(opt2)); // true

        int result3 = optStrToInt(Optional.of("123"), 0);
        int result4 = optStrToInt(Optional.of(""), 0);
        System.out.println("result3 = " + result3); // result3 = 123
        System.out.println("result4 = " + result4); // result4 = 0
    }

    static int optStrToInt(Optional<String> optStr, int defaultValue) {
        try {
            return optStr.map(Integer::parseInt).get();
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
