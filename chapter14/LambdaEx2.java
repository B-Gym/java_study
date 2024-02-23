package chapter14;

public class LambdaEx2 {
    public static void main(String[] args) {
        Myfunction2 f = () -> {
        };
        Object obj = (Myfunction2) (() -> {
        });
        String str = ((Object) (Myfunction2) (() -> {
        })).toString();

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);
    }
}

@FunctionalInterface
interface Myfunction2 {
    void myMethod();
}
