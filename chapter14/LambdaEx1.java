package chapter14;

/**
 * LambdaEx1
 */
public class LambdaEx1 {
    static void execute(MyFunction f) { // 매개변수의 타입이 MyFunction인 메서드
        f.run();
    }

    static MyFunction geMyFunction() { // 반환 타입이 MyFunction인 메서드
        // MyFunction f = () -> System.out.println("f3.run()");
        // return f;
        return () -> System.out.println("f3.run()"); // 람다식의 연산 결과가 곧 반환값이므로 이렇게 한 줄로 표현 가능
    }

    public static void main(String[] args) {
        // 람다식으로 MyFunction의 run()을 구현
        MyFunction f1 = () -> System.out.println("f1.run()");

        // 익명 클래스로 run()을 구현
        MyFunction f2 = new MyFunction() {
            public void run() { // public을 반드시 붙여야 한다.
                System.out.println("f2.run()");
            }
        };

        MyFunction f3 = geMyFunction();

        f1.run();
        f2.run();
        f3.run();

        execute(f1);
        execute(() -> System.out.println("run()"));
    }

}

@FunctionalInterface
interface MyFunction {
    void run(); // == public abstract void run();
}