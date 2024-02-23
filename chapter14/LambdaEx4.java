package chapter14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * LambdaEx4
 */
public class LambdaEx4 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add(i);

        // list 내 모든 요소 출력
        // forEach(Consumer<? super Integer> action) : void
        list.forEach(i -> System.out.print(i + ", "));
        System.out.println();

        // list 내 2 또는 3의 배수 제거
        // removeIf(Predicate<? super Integer> filter) : boolean
        list.removeIf(x -> x % 2 == 0 || x % 3 == 0);
        System.out.println(list);

        // list 내 모든 요소에 10을 곱셈
        // replaceAll(UnaryOperator<Integer> operator) : void
        list.replaceAll(i -> i * 10);
        System.out.println(list);

        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        // map의 모든 요소를 { k, v } 형식으로 출력
        // forEach(BiConsumer<? super String,? super String> action) : void
        map.forEach((k, v) -> System.out.println("{ " + k + ", " + v + " }"));
        System.out.println();

    }
}