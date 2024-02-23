package chapter14;

public class LambdaEx3 {
    public static void main(String[] args) {
        Outer o = new Outer();
        Outer.Inner i = o.new Inner();
        i.method(100);
    }
}

@FunctionalInterface
interface MyFunction3 {
    void myMethod();
}

class Outer {
    int val = 10; // Outer.this.val

    class Inner {
        int val = 20; // this.val

        void method(int i) { // 람다식 내에서 참조하는 지역변수는 상수로 간주됨(final을 붙이지 않아도)
            int val = 30;
            // i = 10; // error 상수값 변경 불가
            // val = 20; // error 상수값 변경 불가
            this.val = 200;
            Outer.this.val = 1000;

            MyFunction3 f = () -> {
                System.out.println("i: " + i);
                System.out.println("val: " + val);
                System.out.println("this.val: " + this.val);
                System.out.println("Outer.this.val: " + Outer.this.val);
            };

            f.myMethod();
        }
    }
}