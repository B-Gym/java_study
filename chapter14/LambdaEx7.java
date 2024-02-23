package chapter14;

import java.util.function.*;

public class LambdaEx7 {
    public static void main(String[] args) {
        System.out.println("[Function의 합성 예제]");
        System.out.println("1) f.endThen(g) : 함수 f를 먼저 적용 후 함수 g 적용");
        Function<String, Integer> f1 = (s) -> Integer.parseInt(s, 16);
        Function<Integer, String> g1 = (i) -> Integer.toBinaryString(i);
        Function<String, String> h1 = f1.andThen(g1);
        System.out.println(h1.apply("FF")); // FF -(f)-> 255 -(g)-> 11111111
        System.out.println(h1.apply("A")); // A -(f)-> 10 -(g)-> 1010

        System.out.println("\n2) f.compose(g) : 함수 g를 먼저 적용 후 함수 f 적용");
        Function<Integer, String> g2 = (i) -> Integer.toBinaryString(i);
        Function<String, Integer> f2 = (s) -> Integer.parseInt(s, 16);
        Function<Integer, Integer> h2 = f2.compose(g2);
        System.out.println(h2.apply(2)); // 2 -(g)-> 10 -(f)-> 16

        // identity() : 항등 함수 필요시 사용
        System.out.println("\n3) identity() : 항등 함수 필요시 사용");
        Function<String, String> f3 = x -> x;
        Function<String, String> f4 = Function.identity(); // f1과 동일한 함수
        System.out.println(f3.apply("Kimgoat"));
        System.out.println(f4.apply("Kimgoat"));

        System.out.println("\n[Predicate의 합성 예제]");
        System.out.println("1) and(), or(), negate()");
        Predicate<Integer> p = i -> i < 100;
        Predicate<Integer> q = i -> i < 200;
        Predicate<Integer> r = i -> i % 2 == 0;
        Predicate<Integer> notP = p.negate(); // i >= 100

        // i >= 100 && (i < 200 || i%2 ==0)
        Predicate<Integer> all = notP.and(q.or(r));
        System.out.println(all.test(200)); // true

        System.out.println("\n2) isEqual()");
        String str1 = "kimgoat";
        String str2 = "kimgoat";

        // Predicate<String> p2 = Predicate.isEqual(str1);
        // boolean result = p2.test(str2);
        boolean result = Predicate.isEqual(str1).test(str2);
        System.out.println(result);

    }
}
