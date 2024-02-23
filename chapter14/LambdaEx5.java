package chapter14;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaEx5 {
    public static void main(String[] args) {
        Supplier<Integer> s = () -> (int) (Math.random() * 100) + 1; // 0~100 중 랜덤한 값 반환
        Consumer<Integer> c = i -> System.out.print(i + ", "); // 매개변수 출력
        Predicate<Integer> p = i -> i % 2 == 0; // 짝수인 경우 true 반환
        Function<Integer, Integer> f = i -> i / 10 * 10; // 매개변수 i의 1의 자리를 버린 값 반환

        List<Integer> list = new ArrayList<>();
        makeRandomList(s, list);
        System.out.println(list);
        printEvenNum(p, c, list);
        List<Integer> newList = doSomething(f, list);
        System.out.println(newList);

    }

    static <T> List<T> doSomething(Function<T, T> f, List<T> list) {
        List<T> newList = new ArrayList<T>(list.size());

        // newList에 main 메서드에서 정의한 함수형 인터페이스 f를 list 내 구성요소에 적용한 값을 저장
        // f: 메개변수의 1의 자리 버린 값을 반환하는 함수
        for (T i : list) {
            newList.add(f.apply(i));
        }

        return newList;
    }

    static <T> void printEvenNum(Predicate<T> p, Consumer<T> c, List<T> list) {
        // p: 매개변수가 짝수이면 true를 반환하는 함수
        // c: 매개변수를 출력하는 함수
        System.out.print("[");
        for (T i : list) {
            if (p.test(i))
                c.accept(i);
        }
        System.out.println("]");
    }

    static <T> void makeRandomList(Supplier<T> s, List<T> list) {
        // s: 0~100중 랜덤한 숫자를 반환하는 함수
        for (int i = 0; i < 10; i++) {
            list.add(s.get());
        }
    }
}
