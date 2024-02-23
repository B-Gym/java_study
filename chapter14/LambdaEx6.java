package chapter14;

import java.util.function.*;

public class LambdaEx6 {
    public static void main(String[] args) {
        IntSupplier s = () -> (int) (Math.random() * 100) + 1; // 0~100 중 랜덤한 값 반환
        IntConsumer c = i -> System.out.print(i + ", "); // 매개변수 출력
        IntPredicate p = i -> i % 2 == 0; // 짝수인 경우 true 반환
        IntUnaryOperator op = i -> i / 10 * 10; // 매개변수 i의 1의 자리를 버린 값 반환

        int[] arr = new int[10];

        makeRandomList(s, arr);
        System.out.println(arr);
        printEvenNum(p, c, arr);
        int[] newList = doSomething(op, arr);
        System.out.println(newList);

    }

    static int[] doSomething(IntUnaryOperator op, int[] arr) {
        int[] newArr = new int[arr.length];

        // newList에 main 메서드에서 정의한 함수형 인터페이스 f를 list 내 구성요소에 적용한 값을 저장
        // f: 메개변수의 1의 자리 버린 값을 반환하는 함수
        for (int i : arr) {
            newArr[i] = op.applyAsInt(arr[i]);
        }

        return newArr;
    }

    static void printEvenNum(IntPredicate p, IntConsumer c, int[] arr) {
        // p: 매개변수가 짝수이면 true를 반환하는 함수
        // c: 매개변수를 출력하는 함수
        System.out.print("[");
        for (int i : arr) {
            if (p.test(i))
                c.accept(i);
        }
        System.out.println("]");
    }

    static void makeRandomList(IntSupplier s, int[] arr) {
        // s: 0~100중 랜덤한 숫자를 반환하는 함수
        for (int i = 0; i < 10; i++) {
            arr[i] = s.getAsInt();
        }
    }
}
