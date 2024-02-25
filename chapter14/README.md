# 람다와 스트림 (Lambda & stream)

1. [람다식(Lambda expression)](#1장-람다식lambda-expression)
2. [[1-1] 람다식이란?](#1-1-람다식이란)
3. [[1-2] 람다식 작성하기](#1-2-람다식-작성-방법)
4. [[1-3] 함수형 인터페이스](#1-3-함수형-인터페이스-functional-interface)
5. [[1-4] java.util.function패키지](#1-4-javautilfunction-패키지)
6. [[1-5] Function의 합성과 Predicate의 결합](#1-5-function의-합성과-predicate의-결합)
7. [[1-6] 메서드 참조](#1-6-메서드-참조)
8. [스트림(stream)](#2장-스트림stream)
9. [[2-1] 스트림이란?](#2-1-스트림이란)
10. [[2-2] 스트림 만들기](#2-2-스트림-만들기)
11. [[2-3] 스트림의 중간연산](#2-3-스트림의-중간연산)
12. [[2-4] Optional<T>와 OptionalInt](#2-4-optionalt와-optionalint)
13. [[2-5] 스트림의 최종연산](#2-5-스트림의-최종연산)
14. [[2-6] collect()](#2-6-collect)
15. [[2-7] Collector 구현하기](#2-7-collector-구현하기)
16. [[2-8] 스트림의 변환](#2-8-스트림의-변환)

</br>
</br>

## 1장 람다식(Lambda expression)

1. [람다식이란?](#1-1-람다식이란)
2. [람다식 작성하기](#1-2-람다식-작성-방법)
3. [함수형 인터페이스](#1-3-함수형-인터페이스-functional-interface)
4. [java.util.function패키지](#1-4-javautilfunction-패키지)
5. [Function의 합성과 Predicate의 결합](#1-5-function의-합성과-predicate의-결합)
6. [메서드 참조](#1-6-메서드-참조)
   </br>

### 1-1. 람다식이란?

`람다식`은 메서드를 하나의 식으로 표현한 것으로 람다식으로 메서드를 표현하는 과정에 메서드의 이름과 반환값이 사라지기 때문에 람다식은 `익명함수`라고도 불린다.

```java
import java.util.*;

public class LambdaEx {

    public static void main(String[] args) {
        System.out.println("\n1. 람다식을 활용하여 랜덤한 숫자로 구성된 배열 만들기");
        randomNumArr();

        System.out.println("\n2. 람다식을 활용하여 배열 정렬하기");
        sortArr();
    }

    /**
     * 람다식을 활용하여 랜덤한 숫자로 구성된 배열 만들기
     */
    static void randomNumArr() {
        int[] arr1 = new int[5];
        Arrays.setAll(arr1, (i) -> (int) (Math.random() * 5) + 1);
        printArr(arr1);
    }

    /**
     * 람다식을 활용하여 배열 정렬하기
     */
    static void sortArr() {
        List<String> str_list = Arrays.asList("a", "b", "d", "c", "e");
        System.out.println(str_list + " (original) ");
        Collections.sort(str_list);
        System.out.println(str_list + " (asc) ");
        Collections.sort(str_list, (s1, s2) -> s2.compareTo(s1));
        System.out.println(str_list + " (desc) ");

        System.out.println();

        List<Integer> num_list = Arrays.asList(1, 5, 4, 6, 8, 4, 21, 6);
        System.out.println(num_list + " (original) ");
        Collections.sort(num_list);
        System.out.println(num_list + " (asc) ");
        Collections.sort(num_list, (n1, n2) -> n1 == n2 ? 0 : (n1 > n2) ? -1 : 1);
        System.out.println(num_list + " (desc) ");

    }

    static void printArr(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
```

</br>

### 1-2. 람다식 작성 방법

1. 메서드의 이름과 반환 타입을 제거한다.
2. 메서드의 매개변수 선언부`()`와 몸통`{}` 사이에 화살표 `->` 를 추가한다.

- 식(expression) 끝에 `;`을 붙이지 않는다.
- 람다식에 선언된 매개변수 타입이 추론 가능한 경우 생략 가능하다. (⚠️ 매개 변수가 여러 개인 경우 어느 하나의 타입만 생략하는 건 안된다.)
- 매개변수가 하나뿐인 경우에 괄호`()` 생략 가능하다. (⚠️ 매개변수의 타입을 선언하는 경우에는 괄호`()` 생략 불가)
- 화살표 `->` 다음에 `return`문이 오는 경우 대괄호`{}`로 감싸야 한다.

<table style="border: 2px; text-align: center;">
  <tr>
    <td> 메서드 </td>
    <td> 람다식 </td>
  </tr>
<tr>
  <td>

```java
int max(int a, int b) {
    return a > b ? a : b;
}
```

  </td>
  <td>

```java
(int a, int b) -> { return a > b ? a : b; }
(int a, int b) -> a > b ? a : b
(a, b) -> a > b ? a : b
```

  </td>
</tr>
<tr>
  <td>

```java
int printVar(String name,  int i) {
    System.out.println(name + ", " + i);
}
```

  </td>
  <td>

```java
(String name, int i) -> { System.out.println(name + ", " + i); }
(name, i) -> { System.out.println(name + ", " + i); }
(name, i) -> System.out.println(name + ", " + i)
```

  </td>
</tr>
<tr>
  <td>

```java
int square(int x) {
    return x * x;
}
```

  </td>
  <td>

```java
(int x) ->  x * x
(x) -> x * x
x -> x * x
```

  </td>
</tr>

<tr>
  <td>

```java
int roll() {
    return (int) (Math.random() * 6);
}
```

  </td>
  <td>

```java
() -> { return (int)(Math.random() * 6); }
() -> (int)(Math.random() * 6)
```

  </td>
</tr>
<tr>
  <td>

```java
int sumArr(int[] arr) {
    int sum = 0;
    for(int i : arr)
        sum += i;
    return sum;
}
```

  </td>
  <td>

```java
(int[] arr) -> {
    int sum = 0;
    for(int i : arr)
        sum += i;
    return sum;
}
```

  </td>
</tr>
<tr>
  <td>

```java
int[] emptyArr() {
    return new int[]{};
}
```

  </td>
  <td>

```java
() -> { return new int[]{}; }
() -> new int[]{}
```

  </td>
</tr>
</table>

</br>

### 1-3. 함수형 인터페이스 (Functional Interface)

`LambdaEx1.java` 함수형 인터페이스 타입의 매개변수와 반환타입에 대한 예제

`LambdaEx2.java` 람다식의 타입이 어떤 형식인지와 람다식의 형변환에 대한 예제

`LambdaEx3.java` 람다식 내부에서 선언된 변수와 외부에서 선언된 변수를 참조하는 경우 각 변수가 어떻게 취급되는지에 대한 예제

</br>

### 1-4. java.util.function 패키지

[🔗 Package java.util.function - Interface Summary](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

<b> </br>📌 기본적인 함수형 인터페이스 </b>

|  함수형 인터페이스   |         메서드         |         설명         |
| :------------------: | :--------------------: | :------------------: |
| `java.lang.Runnable` |      `void run()`      | 매개변수 x, 반환값 x |
|    `Supplier<T>`     |       `T get()`        | 매개변수 x, 반환값 o |
|    `Consumer<T>`     | `void accept(T type)`  | 매개변수 o, 반환값 x |
|   `Function<T,R>`    |   `R apply(T type)`    | 매개변수 o, 반환값 o |
|    `Predicate<T>`    | `boolean test(T type)` | 매개변수 o, 반환값 o |

- 수학에서 결과값으로 `true` 또는 `false`를 반환하는 함수를 '프레디케이트(Predicate)'라고 함

</br>

<b>📝 조건식 표현에 사용되는 Predicate 예제</b>

```java
import java.util.function.Predicate;

public class LambdaEx {
    public static void main(String[] args) {
        Predicate<String> isEmptyStr = s -> s.length() == 0;

        String[] strArr = { "", "kimgoat", " ", "java", "" };

        int idx = 0;
        for (String str : strArr) {
            if (isEmptyStr.test(str))
                System.out.printf("[index: %d] This is an empty String\n", idx);
            idx++;
        }

        // 결과
        // [index: 0] This is an empty String
        // [index: 4] This is an empty String
    }
}
```

<br> </br>📝 `LambdaEx5.java` 기본적인 함수형 인터페이스 예제</b>

```java
import java.util.*;
import java.util.function.*;

public class LambdaEx {
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

```

<b> </br></br>📌 매개변수가 두 개인 함수형 인터페이스 </b>

- 메개변수의 개수가 두 개인 함수형 인터페이스는 이름 앞에 접두사 `Bi`가 붙는다.

|  함수형 인터페이스  |              메서드              |         설명         |
| :-----------------: | :------------------------------: | :------------------: |
|  `BiConsumer<T,U>`  | `void accept(T type1, U type2)`  | 매개변수 o, 반환값 x |
| `BiFunction<T,U,R>` |   `R apply(T type1, U type2)`    | 매개변수 o, 반환값 o |
| `BiPredicate<T,U>`  | `boolean test(T type1, U type2)` | 매개변수 o, 반환값 o |

<b> </br></br>📌 UnaryOperator & BinaryOperator </b>

- `Function`의 또 다른 변형
- `UnaryOperator`의 조상은 `Function`
- `BinaryOperator`의 조상은 `BiFunction`

|  함수형 인터페이스  |           메서드            |              설명               |
| :-----------------: | :-------------------------: | :-----------------------------: |
| `UnaryOperator<T>`  |      `T apply(T type)`      | 매개변수와 반환값의 타입이 같음 |
| `BinaryOperator<T>` | `T apply(T type1, T type2)` | 매개변수와 반환값의 타입이 같음 |

<b> </br></br>📌 컬렉션 프레임웍과 함수형 인터페이스 </b>

|  인터페이스  |                      메서드                      |               설명               |
| :----------: | :----------------------------------------------: | :------------------------------: |
| `Collection` |     `boolean removeIf(Predicate<E> filter)`      |      조건에 맞는 요소 삭제       |
|    `List`    |   `void replaceAll(UnaryOperator<E> operator)`   |    모든 요소를 변환하여 대체     |
|  `Iterable`  |        `void forEach(Consumer<T> action)`        |   모든 요소에 작업 action 수행   |
|    `Map`     |     `V compute(K key, BiFunction<K,V,V> f)`      |   지정된 키의 값에 작업 f 수행   |
|    `Map`     |   `V computeIfAbsent(K key, Function<K,V> f)`    | 키가 없으면 작업 f 수행 후 추가  |
|    `Map`     | `V computeIfPresent(K key, BiFunction<K,V,V> f)` | 지정된 키가 있을 때, 작업 f 수행 |
|    `Map`     |  `V merge(K key, V value, BiFunction<V,V,V> f)`  |   모든 요소에 병합 작업 f 수행   |
|    `Map`     |      `void forEach(BiConsumer<K,V> action)`      |   모든 요소에 작업 action 수행   |
|    `Map`     |      `void replaceAll(BiFunction<K,V,V> f)`      |  모든 요소에 치완 작업 f를 수행  |

<b></br></br>📝 `LambdaEx4.java`컬렉션 프레임웍과 함수형 인터페이스 예제</b>

```java
package chapter14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LambdaEx4 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add(i);

        // list 내 모든 요소 출력
        // forEach(Consumer<? super Integer> action) : void
        list.forEach(i -> System.out.print(i + ", "));
        System.out.println();
        // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,


        // list 내 2 또는 3의 배수 제거
        // removeIf(Predicate<? super Integer> filter) : boolean
        list.removeIf(x -> x % 2 == 0 || x % 3 == 0);
        System.out.println(list);
        // [1, 5, 7]

        // list 내 모든 요소에 10을 곱셈
        // replaceAll(UnaryOperator<Integer> operator) : void
        list.replaceAll(i -> i * 10);
        System.out.println(list);
        // [10, 50, 70]

        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        // map의 모든 요소를 { k, v } 형식으로 출력
        // forEach(BiConsumer<? super String,? super String> action) : void
        map.forEach((k, v) -> System.out.println("{ " + k + ", " + v + " }"));
        System.out.println();
        // { 1, 1 }
        // { 2, 2 }
        // { 3, 3 }
        // { 4, 4 }
    }
}
```

<b> </br></br>📌 기본형을 사용하는 함수형 인터페이스 </b>

|   함수형 인터페이스    |           메서드           |                    설명                    |
| :--------------------: | :------------------------: | :----------------------------------------: |
| `Double ToIntFunction` | `int applyAsInt(double d)` |    AToBFunction [input: A / output: B]     |
|   `ToIntFunction<T>`   | `int applyAsInt(T value)`  |  ToBFunction [input: Generic / output: B]  |
|    `IntFunction<R>`    |    `R apply(T t, U u)`     |   AFunction [input: A / output: Generic]   |
|  `ObjIntConsumer<T>`   |  `void accept(T t, U u)`   | ObjAFunction [input: T, A / output : none] |

<b></br></br>📝 `LambdaEx6.java`기본형을 사용하는 함수형 인터페이스 예제</b>

```java
import java.util.function.*;

public class LambdaEx {
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

```

</br>

### 1-5. Function의 합성과 Predicate의 결합

```java
// Function
// ⚠️ 두개의 타입이 같아도 반드시 두 개의 타입을 지정해 줘야 함
default <V> Function<T, V> andThen(Function<? super R, ? extends V> after)
default <V> Function<V, R> compose(Function<? super V, ? extends T> before)
static <T> Function<T,T> identity()

// Predicate
default Predicate<T> and(Predicate<? super T> other)
default Predicate<T> or(Predicate<? super T> other)
default Predicate<T> negate()
static <T> Predicate<T> isEqual(Object targetRef)
```

</br>

<b> 📝`LambdaEx6.java` Function의 합성 예제 </b>

```java
// f.endThen(g) : 함수 f를 먼저 적용 후 함수 g 적용
Function<String, Integer> f = (s) -> Integer.parseInt(s, 16);
Function<Integer, String> g = (i) -> Integer.toBinaryString(i);
Function<String, String> h = f.andThen(g);
System.out.println(h.apply("FF")); // FF -(f)-> 255 -(g)-> 11111111
System.out.println(h.apply("A")); // A -(f)-> 10 -(g)-> 1010

// f.compose(g) : 함수 g를 먼저 적용 후 함수 f 적용
Function<Integer, String> g = (i) -> Integer.toBinaryString(i);
Function<String, Integer> f = (s) -> Integer.parseInt(s, 16);
Function<Integer, Integer> h = f.compose(g);
System.out.println(h.apply(2)); // 2 -(g)-> 10 -(f)-> 16

// identity() : 항등 함수 필요시 사용
Function<String, String> f1 = x -> x;
Function<String, String> f2 = Function.identity(); // f1과 동일한 함수

```

</br>

<b>📝`LambdaEx6.java` Predicate 합성 예제 </b>

- 논리연산자를 결합하여 사용하는 것처럼 여러 Predicate를 `and()`, `or()`, `negate()` 로 연결하여 하나의 새로운 Predicate로 결합 가능

```java
Predicate<Integer> p = i -> i < 100;
Predicate<Integer> q = i -> i < 200;
Predicate<Integer> r = i -> i%2 == 0;
Predicate<Integer> notP = p.negate(); // i >= 100

// i >= 100 && (i < 200 || i%2 ==0)
Predicate<Integer> all = notP.and(q.or(r));
System.out.println(all.test(200)); // true
```

</br>

### 1-6. 메서드 참조

```java
Function<String, Integer> f1 = (s) -> Integer.parseInt(s);
Function<String, Integer> f2 = Integer::parseInt;
```

- 람다식이 위와 같이 하나의 메서드만 호출하는 경우에는 메서드 참조를 통해 간략화할 수 있다.

|             종류              |            람다식            |     메서드참조      |
| :---------------------------: | :--------------------------: | :-----------------: |
|      static 메서드 참조       | `(x) -> ClassName.method(x)` | `ClassName::method` |
|      인스턴스메서드 참조      | `(obj, x) -> obj.method(x)`  | `ClassName::method` |
| 특정 객체 인스턴스메서드 참조 |    `(x) -> obj.method(x)`    |    `obj::method`    |

<b> </br>📝 람다식 -> 메서드 참조 간략화 예제 </b>

|                      람다식                       |         메서드참조          |
| :-----------------------------------------------: | :-------------------------: |
|            `(String s) -> s.length()`             |      `String::length`       |
|                `() -> new int[]{}`                |        `int[]::new`         |
|            `arr -> Arrays.stream(arr)`            |      `Arrays::stream`       |
| `(String str1, String str2) -> str1.equals(str2)` |      `String::equals`       |
|         `(a, b) -> Integer.compare(a, b)`         |     `Integer::compare`      |
|  `(String kind, int num) -> new Card(kind, num)`  |         `Card::new`         |
|          `(x) -> System.out.println(x)`           |    `System.out::println`    |
|               `()-> Math.random()`                |       `Math::random`        |
|           `(str) -> str.toUpperCase()`            |    `String::toUpperCase`    |
|        `() -> new NullPointerException()`         | `NullPointerException::new` |
|           `(Optional opt) -> opt.get()`           |       `Optional::get`       |
|   `(StringBuffer sb, String s) -> sb.append(s)`   |   `StringBuffer::append`    |
|       `(String s) -> System.out.println(s)`       |    `System.out::println`    |

</br>
</br>
</br>
</br>

## 2장 스트림(stream)

1. [스트림이란?](#2-1-스트림이란)
2. [스트림 만들기](#2-2-스트림-만들기)
3. [스트림의 중간연산](#2-3-스트림의-중간연산)
4. [Optional<T>와 OptionalInt](#2-4-optionalt와-optionalint)
5. [스트림의 최종연산](#2-5-스트림의-최종연산)
6. [collect()](#2-6-collect)
7. [Collector 구현하기](#2-7-collector-구현하기)
8. [스트림의 변환](#2-8-스트림의-변환)

</br>

### 2-1. 스트림이란?

[🔗 Package java.util.stream - Summary](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)

[🔗 Interface Stream<T> - Method Summary](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)

스트림은 추상화한 데이터를 다루는데 자주 사용되는 메서드를 정의해 놓았기 때문에 데이터 소스의 타입이 무엇이던 같은 방식으로 다룰 수 있어 코드의 재사용성을 높인다.

```java
import java.util.*;
import java.util.stream.*;

public class StreamEx1 {
    public static void main(String[] args) {
        before();
        after();
    }

    /**
     * stream 사용 전: 데이터 소스마다 다른 방식으로 다뤄야 함
     */
    static void before() {
        System.out.println("\nstream 사용 전");

        String[] strArr1 = { "c", "bb", "a" };
        List<String> strList1 = Arrays.asList("c", "bb", "a");

        Arrays.sort(strArr1);
        Collections.sort(strList1);

        printArr(strArr1);
        printList(strList1);
    }

    static void printArr(String[] arr) {
        for (String s : arr) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    static <T> void printList(List<T> list) {
        for (T s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    /**
     * stream 사용 후: 데이터 소스가 무엇이든 같은 방식으로 다룰 수 있음
     */
    static void after() {
        System.out.println("\nstream 사용 후");

        String[] strArr2 = { "c", "bb", "a" };
        List<String> strList2 = Arrays.asList("c", "bb", "a");

        Stream<String> strStream1 = Arrays.stream(strArr2);
        Stream<String> strStream2 = strList2.stream();

        strStream1.sorted().forEach(s -> System.out.print(s + " "));
        System.out.println();
        strStream2.sorted().forEach(s -> System.out.print(s + " "));
        System.out.println();
    }
}
```

- 스트림은 데이터 소스를 변경하지 않는다.
- 스트림은 일회용이다.
- 스트림을 작업을 내부 반복으로 처리한다.

<br></br>📌 Stream 연산 목록 표</b>

| 중간연산                                                                                                                                                                                                                                                                                                                                                                                                                                                                                | 셜명                     |
| :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :----------------------- |
| `Stream<T> distinct()`                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | 중복 제거                |
| `Stream<T> filter(Predicate<T> predicate)`                                                                                                                                                                                                                                                                                                                                                                                                                                              | 조건에 안 맞는 요소 제외 |
| `Stream<T> limit(long maxSize)`                                                                                                                                                                                                                                                                                                                                                                                                                                                         | 스트림 일부 자르기       |
| `Stream<T> skip(long n)`                                                                                                                                                                                                                                                                                                                                                                                                                                                                | 스트림 일부 건너뛰기     |
| `Stream<T> peek(Consumer<T> action)`                                                                                                                                                                                                                                                                                                                                                                                                                                                    | 스트림 요소 작업 수행    |
| `Stream<T> sorted()` </br> `Stream<T> sorted(Comparator<T> comparator)`                                                                                                                                                                                                                                                                                                                                                                                                                 | 스트림 요소 정렬         |
| `Stream<R> map(Function<T,R> mapper)` </br> `DoubleStream mapToDouble(ToDoubleFunction<T> mapper) ` </br> `IntStream mapToInt(ToIntFunction<T> mapper) ` </br> `LongStream mapToLong(ToLongFunction<T> mapper)` </br> </br> `Stream<R> flatMap(Function<T, Stream<R>> mapper)` </br>`DoubleStream 	flatMapToDouble(Function<T, DoubleStream> mapper)` </br>`IntStream 	flatMapToInt(Function<T, IntStream> mapper)` </br>`LongStream 	flatMapToLong(Function<T, LongStream> mapper)` </br> | 스트림 요소 변환         |

| 최종연산                                                                                                                                                                                                            | 셜명                                           |
| :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | :--------------------------------------------- |
| `void forEach(Consumer<? super T) action` </br> `void forEachOrdered(Consumer<? super T> action)`                                                                                                                   | 각 요소에 지정된 작업 수행                     |
| `long count() `                                                                                                                                                                                                     | 스트림 요소 개수 반환                          |
| `Optional<T> max(Comparator<? super T> comparator)` </br> `Optional<T> min(Comparator<? super T> comparator)`                                                                                                       | 스트림의 최댓값, 최솟값 반환                   |
| `boolean allMatch(Predicate<T> p)` </br> `boolean anyMatch(Predicate<T> p)` </br> `boolean noneMatch(Predicate<T> p)`                                                                                               | 주어진 조건을 모든 요소가 만족하는지 여부 확인 |
| `Optional<T> findAny()` </br> `Optional<T> findFirst()`                                                                                                                                                             | 스트림 요소 하나 반환                          |
| `Object[] toArray()` </br> `A[] toArray(IntFunction<A[]> generator)`                                                                                                                                                | 스트림 모든 요소를 배열로 반환                 |
| `Optional<T> reduce(BinaryOperator<T> accumulator)` </br> `T 	reduce(T identity, BinaryOperator<T> accumulator)` </br> `<U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)` | 스트림의 요소를 하나씩 줄여가면서 계산         |
| `<R, A> R collect(Collector<? super T,A,R> collector)` </br> `<R> R	collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)`                                                    | 스트림 요소를 수집                             |

- 스트림 연산은 <b>최종 연산이 수행되어야만</b> 중간 연산이 수행된다.
- 스트림으로 데이터를 다룰 때 병렬로 처리하고 싶은 경우 스트림에 `parallel()`을 호출한다. 스트림의 속성을 병렬로 변경한 것을 취소하고 싶은 경우에는 `sequential()`을 호출하면 된다.

</br>

### 2-2. 스트림 만들기

`Stream<T> Collection.stream();`

<b></br> 📌 배열을 소스로 하는 스트림 생성</b>

```java
Stream<T> Stream.of(T... values) // 가변 인자
Stream<T> Stream.of(T[])
Stream<T> Arrays.stream(T[])
Stream<T> Arrays.stream(T[] array, int startInclusive, int endExclusive)

// int(IntStream), long(LongStream), double(DoubleStream)과 같은 기본형 배열을 소스로 하는 스트림 생성
IntStream IntStream.of(int... values)
IntStream IntStream.of(int[])
IntStream Arrays.stream(int[])
IntStream Arrays.stream(int[] array, int startInclusive, int endExclusive)
```

<b></br> 📌 지정된 범위의 연속된 정수 가지는 스트림 생성</b>

```java
IntStream IntStream.range(int begin, int end) // 경계의 끝이 포함 x
IntStream IntStream.rangeClosed(int begin, int end) // 경계의 끝이 포함 o
```

<b></br> 📌 지정된 타입에 따른 난수로 이루어진 스트림 생성</b>

```java
// 크기가 정해지지 않은 무한 스트림 생성
IntStream ints()
LongStream longs()
DoubleStream doubles()

// 지정된 범위의 난수를 발생시키는 무한 스트림 생성
IntStream ints(int begin, int end)
LongStream longs(long begin, long end)
DoubleStream doubles(double begin, double end)


// limit()를 사용하여 무한 스트림을 유한 스트림으로 변환하여 생성
IntStream ints(long streamSize)
LongStream longs(long streamSize)
DoubleStream doubles(long streamSize)

// 지정된 범위의 난수를 발생시키는 유한 스트림 생성
IntStream ints(long streamSize, int begin, int end)
LongStream longs(long streamSize, long begin, long end)
DoubleStream doubles(long streamSize, double begin, double end)

IntStream intStream = new Random().ints(5) // 스트림의 크기가 5인 난수 스크림 생성
IntStream intStream = new Random().ints(5, 1, 10) // 1-9 범위의 난수를 발생시키는 크기가 5인 난수 스크림 생성
```

<b></br> 📌 매개 변수로 받은 람다식에 의해 계산된 값을 요소로 하는 무한 스트림 생성 - iterate(), generate()</b>

- ⚠️ iterate()와 generate()에 의해 생성된 스트림은 기본형 스트림 타입의 참조변수로 다룰 수 없음 (필요한 경우에 변환하고자 하는 `<T>`의 `mapTo<T>()` 메서드로 변환해야 함) (ex) `IntStream intStream = Stream.iterate(0, n -> n+2).mapToInt(Integer::parseInt)`
- `<T>Stream` 타입의 스트림을 `Stream<T>`으로 변환하려는 경우 `boxed()`를 사용하면 됨 (ex) `Stream<Integer> stream = intStream.boxed();`

```java
static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
static <T> Stream<T> generate(Supplier<T> s)
```

<b></br> 📌 지정된 디렉토리(dir)에 있는 파일 목록을 소스로 하는 스트림 생성</b>

[🔗 Package java.nio.file.Files](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html)

```java
static Stream<Path>	Files.list(Path dir)

static Stream<String>	Files.lines(Path path)
static Stream<String>	Files.lines(Path path, Charset cs)
static Stream<String>	lines()
```

<b></br> 📌 요소가 없는 비어있는 스트림 생성 - `Stream.empty()`</b>

```java
Stream emptyStream = Stream.empty(); // empty(): 빈 스크림 생성 후 반환
long count = emptyStream.count(); // 0
```

<b></br> 📌 두 스트림을 연결한 스트림 생성 - `Stream.concat()`</b>

- ⚠️ 연결하려는 두 스트림의 타입은 같아야 한다.

```java
Stream<String> strs1 = Stream.of("123", "456", "789");
Stream<String> strs2 = Stream.of("ABC", "abc", "DEF");
Stream<String> strs3 = Stream.concat(strs1, strs2);
```

</br>

### 2-3. 스트림의 중간연산

[🔗 `Interface Stream<T>` - Method Summary](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)

<b></br> 📌 스트림 자르기 - `skip()`, `limit()` </b>

- `skip()` : 매개변수 요소만큼 건너뜀
- `limit()` : 스트림의 요소를 매개변수 만큼 제한

```java
Stream<T>	skip(long n)
Stream<T>	limit(long maxSize)

// 기본형 스트림
IntStream	    skip(long n)
IntStream	    limit(long maxSize)
LongStream	    skip(long n)
LongStream	    limit(long maxSize)
DoubleStream	skip(long n)
DoubleStream	limit(long maxSize)


// 사용 방법
IntStream intStream = IntStream.rangeClosed(1,10);
intStream.skip(3).limit(4).forEach(System.out::print); // 4567
```

<b></br> 📌 스트림 요소 걸러내기 - `filter()`, `distinct()`</b>

- `filter()` : 주어진 조건(Predicate)에 맞지 않는 요소 거름, 필요한 경우 다른 조건으로 여러 번 사용 가능
- `distinct()` : 스트림에서 중복된 요소 제거

```java
Stream<T>	filter(Predicate<? super T> predicate)
Stream<T>	distinct()

// 사용 방법 : distinct()
IntStream intStream = IntStream.of(1, 2, 2, 3, 3, 4, 5, 6);
intStream.distinct().forEach(System.out::print); // 123456

// 사용 방법 : filter(Predicate<? super T> predicate)
IntStream intStream = IntStream.rangeClosed(1,10);
intStream.filter(i -> i % 2 == 0).forEach(System.out::print); // 246810
```

<b></br> 📌`StreamEx1.java` 정렬 - `sorted()` </b>

[🔗 Interface Comparator<T> - Method Summary](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)

- `sorted()` : 지정된 Comparator로 스트림을 정렬하는데, Comparator 대신 int값 반환하는 람다식 사용도 가능. Comparator 지정하지 않는 경우 스트림 요소의 기본 정렬기준에 따라 정렬
- ⚠️ 스트림 요소가 Comparable을 구현한 클래스가 아니라면 예외 발생

```java
Stream<T>	sorted()
Stream<T>	sorted(Comparator<? super T> comparator)
```

<b></br> 📌 `StreamEx2.java`변환 - `map()` </b>

- `map()` : 스트림 요소에 저장된 값 중 원하는 필드만 뽑아내거나 특정 형태로 변환해야 할 때 사용

```java
 Stream<R>	map(Function<? super T,? extends R> mapper)
```

<b></br> 📌 조회 - `peek()` </b>

- `peek()` : 연산과 연산 사이에 올바르게 처리되었는지 확인하는 메서드, `filter()`나 `map()`의 결과를 확인할 때 유용하게 사용

```java
Stream<T>	peek(Consumer<? super T> action)
```

<b></br> 📌 `mapToInt()`, `mapToLong()`, `mapToDouble()` </b>

- 스트림의 요소를 숫자로 변경하는 경우 기본형 스트림으로 변환하는 것이 유용 (기본형 스트림만 제공하는 통계형 메서드를 사용하기 위해)

```java
IntStream	mapToInt(ToIntFunction<? super T> mapper)
LongStream	mapToLong(ToLongFunction<? super T> mapper)
DoubleStream	mapToDouble(ToDoubleFunction<? super T> mapper)

// 예제
IntStream studentScoreStream = studentStream.mapToInt(Student::getTotalScore);
int allToalScore = studentScoreStream.sum();

```

- Interface IntStream의 Method들 중 숫자를 다루는데 편리한 메서드 4개
- ⚠️ 아래 메서드 4개 모두 최종 연산이기 때문에 호출 후 스트림이 닫힌다. 즉, 하나의 스트림에 아래 메서드를 연속해서 사용할 수 없음

[🔗 Interface IntStream - Method Summary](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html)

```java
int	sum()
OptionalDouble	average()
OptionalInt	max()
OptionalInt	min()
```

- `summaryStatistics()` 메서드를 통해 하나의 스트림에 최종 연산을 하기 위해 새롭게 생성하는 번거로움을 없앨 수 있다.

[🔗 Class IntSummaryStatistics - Method Summary ](https://docs.oracle.com/javase/8/docs/api/java/util/IntSummaryStatistics.html)

```java
IntSummaryStatistics	summaryStatistics()

IntSummaryStatistics stat = scoreStream.summaryStatistics();
long totalCount = stat.getCount();
long totalScore = stat.getSum();
double avgScore = stat.getAverage();
int minScore = stat.min();
int maxScore = stat.max();
```

- 기본형 스트림을 `Stream<T>`로 변환하는 경우에 `mapToObj()`를 사용

[🔗 Interface IntStream - Method Summary ](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html)

[🔗 Interface DoubleStream - Method Summary ](https://docs.oracle.com/javase/8/docs/api/java/util/stream/DoubleStream.html)

[🔗 Interface LongStream - Method Summary ](https://docs.oracle.com/javase/8/docs/api/java/util/stream/LongStream.html)

```java
// IntStream
Stream<Integer>	boxed()
IntStream	map(IntUnaryOperator mapper)
DoubleStream	mapToDouble(IntToDoubleFunction mapper)
LongStream	mapToLong(IntToLongFunction mapper)
<U> Stream<U>	mapToObj(IntFunction<? extends U> mapper)

// DoubleStream
Stream<Double>	boxed()
DoubleStream	map(DoubleUnaryOperator mapper)
IntStream	mapToInt(DoubleToIntFunction mapper)
LongStream	mapToLong(DoubleToLongFunction mapper)
<U> Stream<U>	mapToObj(DoubleFunction<? extends U> mapper)

// LongStream
Stream<Long>	boxed()
LongStream	map(LongUnaryOperator mapper)
IntStream	mapToInt(LongToIntFunction mapper)
DoubleStream	mapToDouble(LongToDoubleFunction mapper)
<U> Stream<U>	mapToObj(LongFunction<? extends U> mapper)
```

<b></br> 📌`StreamEx4.java` `Stream<T[]>`를 `Stream<T>`로 변환 - `flatMap()` </b>

- `flatMap()`: 스트림 요소가 배열이거나 `map()`의 연산결과가 배열인 경우 즉, `Stream<T[]>`을 `Stream<T>`로 다루기 위해 사용

```java
<R> Stream<R>	flatMap(Function<? super T,? extends Stream<? extends R>> mapper)
```

</br>

### 2-4. `Optional<T>`와 `OptionalInt`

`OptionalEx1.java`

[🔗 Class `Optional<T>` - Method Summary ](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)

- 스트림의 연산 결과가 `Optional`인 경우가 있는데 이때 `Optional`은 객체이므로 `Optional<T>` 내부 메서드를 통해 결과 값이 null인지 체크할 수 있다.

```java
String str = "Hello";
Optional<String> optVal = Optional.of(str);

Optional<String> optVal = Optional.of("Hello");

Optional<String> optVal = Optional.of(new String("Hello"));
```

- 만일 참조 변수의 값이 null일 가능성이 있는 경우 `of()`가 아닌 `ofNullable()`을 사용하여 `Optional` 객체를 생성해야 한다.

```java
Optional<String> optVal = Optional.of(null); // 불가능 - NullPointerException 발생
Optional<String> optVal = Optional.ofNullable(null); // 가능
```

- `Optional<T>` 타입의 참조변수를 기본값으로 초기화하는 경우에 null이 아닌 `empty()`를 사용하는 것이 바람직

```java
Optional<String> optVal = null; // 가능은 하다.
Optional<String> optVal = Optional.<String>empty();
Optional<String> optVal = Optional.empty();
```

- `Optional` 객체의 값을 가져올 때 `Optional` 객체의 값이 null인 경우에 NullPointerException가 발생할 수 있기 때문에 가져오고자 하는 객체의 값이 null인 경우에 대체해서 반환할 값을 지정할 수 있다.

```java
Optional<String> optVal1 = Optional.of("Hello");
String str1 = optVal1.get();

Optional<String> optVal2 = Optional.ofNullable(null);
String str2 = optVal2.get(); // NoSuchElementException발생
String str3 = optVal2.orElse("This is null");
String str4 = optVal2.orElseGet(String::new); // () -> new String()과 동일
String str5 = optVal2.orElseThrow(NullPointerException::new); // null인 경우 예외 발생시킴
```

[🔗 Class `OptionalInt` - Method Summary ](https://docs.oracle.com/javase/8/docs/api/java/util/OptionalInt.html)

[🔗 Class `OptionalLong` - Method Summary ](https://docs.oracle.com/javase/8/docs/api/java/util/OptionalLong.html)

[🔗 Class `OptionalDouble` - Method Summary ](https://docs.oracle.com/javase/8/docs/api/java/util/OptionalDouble.html)

| Optional 클래스  | 값을 반환하는 메서드   |
| :--------------- | :--------------------- |
| `Optional<T>`    | `T get()`              |
| `OptionalInt`    | `int getAsInt()`       |
| `OptionalLong`   | `long getAsLong()`     |
| `OptionalDouble` | `double getAsDouble()` |

</br>

### 2-5. 스트림의 최종연산

`StreamEx5.java`

[🔗 `Interface Stream<T>` - Method Summary](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)

<b></br> 📌 스트림 요소 출력하기 - `forEach()` </b>

- `forEach()` : 반환 타입이 void여서 스트림의 요소를 출력하는 용도로 많이 사용

```java
void	forEach(Consumer<? super T> action)
```

<b></br> 📌 조건 검사 - `allMatch()`, `anyMatch()`, `noneMatch()`, `findFirst()`, `findAny()` </b>

- `allMatch()` : 스트림 요소에 대해 지정된 조건에 모든 요소가 일치하는지 확인
- `anyMatch()` : 스트림 요소에 대해 지정된 조건에 일부 요소가 일치하는지 확인
- `noneMatch()` : 스트림 요소에 대해 지정된 조건에 어떠한 요소도 일치하지 않는지 확인
- `findFirst()` : 스트림 요소 중 조건에 일치하는 첫 번째 요소를 반환
- `findAny()` : 병렬 스트림인 경우에 `findFirst()` 대신 사용해야 함

```java
boolean	allMatch(Predicate<? super T> predicate)
boolean	anyMatch(Predicate<? super T> predicate)
boolean	noneMatch(Predicate<? super T> predicate)
Optional<T>	findFirst()
Optional<T>	findAny()
```

<b></br> 📌 통계 - `count()`, `sum()`, `average()`, `max()`, `min()` </b>

- `count()` : 스트림 요소 개수 반환
- `sum()` : 스트림의 총합 반환
- `average()` : 스트림의 평균값 반환
- `max()` : 스트림의 최댓값 반환
- `min()` : 스트림의 최솟값 반환

```java
// Interface Stream<T> Method
long count()
Optional<T>	max(Comparator<? super T> comparator)
Optional<T>	min(Comparator<? super T> comparator)

// Interface IntStream Method
long count()
int	sum()
OptionalDouble	average()
OptionalInt	max()
OptionalInt	min()

// Interface LongStream Method
long count()
long sum()
OptionalDouble	average()
OptionalLong max()
OptionalLong min()

// Interface DoubleStream Method
long count()
double	sum()
OptionalDouble	average()
OptionalDouble	max()
OptionalDouble	min()
```

<b></br> 📌 리듀싱 - `reduce()` </b>

- `reduce()` : 스트림의 요소를 줄여나가면서 연산을 수행하여 최종 결과 반환

```java
Optional<T>	reduce(BinaryOperator<T> accumulator)
T reduce(T identity, BinaryOperator<T> accumulator)
U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
```

</br>

### 2-6. collect()

`StreamEx6.java`

[🔗 `Interface Stream<T>` - Method Summary](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)

[🔗 Interface Collector - Method Summary ](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collector.html)

[🔗 Class Collectors - Method Summary](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)

- `collect()` : 스트림의 요소를 어떻게 수집할 것인지에 대한 방법을 정의한 메서드

```java
collect() // 스트림의 최종 연산, 매개변수로 컬렉터를 필요로 함
Interface Collector // 인터페이스, 컬렉터는 이 인터페이스를 구현해야 함
class Collectors // 클래스, static 메서드로 미리 작성된 컬렉터 제공
```

<b></br> 📌 스크림을 컬렉션과 배열로 반환 - `toList()`, `toSet()`, `toMap()`, `toCollection()`, `toArray()`</b>

```java
List<String> names = stuStream.map(Student::getName)
                            .collect(Collectors.toList());
ArrayList<String> list = names.stream()
                            .collect(Collectors.toCollection(ArrayList::new));
Map<String, Student> map = stuStream
                    `       `.collect(Collectors.toMap(s -> s.getName(), s -> s)); // s -> s == Function.identity()

Student[] stuArr = stuStream.toArray(Student::new);
Object[] stuArr = stuStream.toArray();
```

<b></br> 📌 통계 - `counting()`, `summingInt()`, `averagingInt()`, `maxBy()`, `minBy()` </b>

```java
long count = Stream.of(stuArr)
                .collect(Collectors.counting());
long totalScore = Stream.of(stuArr)
                .collect(Collectors.summingInt(Student::getTotalScore));


totalScore = Stream.of(stuArr)
                .collect(Collectors.reducing(0, Student::getTotalScore, Integer::sum));

Optional<Student> topStudent = Stream.of(stuArr)
                .max(Comparator.comparingInt(Student::getTotalScore));

Optional<Student> topStudent = Stream.of(stuArr)
                .collect(Collectors.maxBy(Comparator.comparingInt(Student::getTotalScore)));

IntSummaryStatistics stat = Stream.of(stuArr)
                .collect(Collectors.summarizingInt(Student::getTotalScore));
```

<b></br> 📌 리듀싱 - `reducing()` </b>

```java
static <T> Collector<T,?,Optional<T>>	reducing(BinaryOperator<T> op)
static <T> Collector<T,?,T>	reducing(T identity, BinaryOperator<T> op)
static <T,U> Collector<T,?,U>	reducing(U identity, Function<? super T,? extends U> mapper, BinaryOperator<U> op)
```

<b></br> 📌 문자열 결합 - `joining()` </b>

```java
static Collector<CharSequence,?,String>	joining()
static Collector<CharSequence,?,String>	joining(CharSequence delimiter)
static Collector<CharSequence,?,String>	joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
```

<b></br> 📌 그룹화와 분할 - `groupingBy()`, `partitioningBy()` </b>

- `groupingBy()` : 스트림 요소를 Function으로 분류
- `partitioningBy()` : 스트림 요소를 Predicate로 분류. 스트림을 두 개의 그룹으로 나눠야 하는 경우 `partitioningBy()`로 분할하는 것이 더 빠름
- 그룹화 분할의 결과는 Map에 담겨 반환됨

`StreamEx7.java` : `partitioningBy()` 예제

`StreamEx8.java` : `groupingBy()` 예제

```java
static <T,K> Collector<T,?,Map<K,List<T>>>	groupingBy(Function<? super T,? extends K> classifier)
static <T,K,A,D> Collector<T,?,Map<K,D>>	groupingBy(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream)
static <T,K,D,A,M extends Map<K,D>> Collector<T,?,M> groupingBy(Function<? super T,? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream)

static <T> Collector<T,?,Map<Boolean,List<T>>>	partitioningBy(Predicate<? super T> predicate)
static <T,D,A> Collector<T,?,Map<Boolean,D>>	partitioningBy(Predicate<? super T> predicate, Collector<? super T,A,D> downstream)
```

</br>

### 2-7. Collector 구현하기

`CollectorEx1.java`

[🔗 Interface Collector<T,A,R>](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collector.html)

```java
BiConsumer<A,T>	accumulator() // A function that folds a value into a mutable result container.
Set<Collector.Characteristics>	characteristics() //Returns a Set of Collector.Characteristics indicating the characteristics of this Collector.
BinaryOperator<A>	combiner() // A function that accepts two partial results and merges them.
Function<A,R>	finisher() // Perform the final transformation from the intermediate accumulation type A to the final result type R.
static <T,A,R> Collector<T,A,R>	of(Supplier<A> supplier, BiConsumer<A,T> accumulator, BinaryOperator<A> combiner, Function<A,R> finisher, Collector.Characteristics... characteristics) // Returns a new Collector described by the given supplier, accumulator, combiner, and finisher functions.
static <T,R> Collector<T,R,R>	of(Supplier<R> supplier, BiConsumer<R,T> accumulator, BinaryOperator<R> combiner, Collector.Characteristics... characteristics) // Returns a new Collector described by the given supplier, accumulator, and combiner functions.
Supplier<A>	supplier() // A function that creates and returns a new mutable result container.
```

- `supplier()` : 작업 결과를 저장할 공간 제공
- `accumulator()` : 스트림의 요소를 수집할 방법 제공
- `combiner()` : 두 저장공간을 병합할 방법을 제공(병렬 스트림)
- `finisher()` : 결과를 최종적으로 변환할 방법을 제공
- `characteristics()` : 컬렉터가 수행하는 작업의 속성에 대한 정보를 제공

</br>

### 2-8. 스트림의 변환

<table class="tg">
<thead>
  <tr>
    <th class="tg-0lax">from</th>
    <th class="tg-0lax">to</th>
    <th class="tg-0lax">변환 시 사용하는 메서드</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-0lax" colspan="3">1. 스트림 → 기본형 스트림</td>
  </tr>
  <tr>
    <td class="tg-0lax">Stream&lt;T&gt;</td>
    <td class="tg-0lax">IntStream<br>LongStream<br><span style="font-weight:400;font-style:normal">Double</span>Stream</td>
    <td class="tg-0lax">mapToInt(ToIntFunction&lt;T&gt; mapper)<br>mapToLong(ToLongFunction&lt;T&gt; mapper)<br>mapToDoublt(ToDoubleFunction&lt;T&gt; mapper)</td>
  </tr>
  <tr>
    <td class="tg-0lax" colspan="3">2.&nbsp;&nbsp;기본형 스트림 →&nbsp;&nbsp;스트림</td>
  </tr>
  <tr>
    <td class="tg-0lax" rowspan="2">IntStream<br>LongStream<br><span style="font-weight:400;font-style:normal">Double</span>Stream</td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">Stream&lt;Integer&gt;</span><br><span style="font-weight:400;font-style:normal">Stream&lt;Long&gt;</span><br><span style="font-weight:400;font-style:normal">Stream&lt;Double&gt;</span></td>
    <td class="tg-0lax">boxed()</td>
  </tr>
  <tr>
    <td class="tg-0lax">Stream&lt;U&gt;</td>
    <td class="tg-0lax">mapToObj(DoubleFunction&lt;U&gt; mapper)</td>
  </tr>
  <tr>
    <td class="tg-0lax" colspan="3">3. 기본형 스트림 → 기본형 스트림</td>
  </tr>
  <tr>
    <td class="tg-0lax">IntStream<br>LongStream<br><span style="font-weight:400;font-style:normal">Double</span>Stream</td>
    <td class="tg-0lax">LongStream<br><br><span style="font-weight:400;font-style:normal">Double</span>Stream</td>
    <td class="tg-0lax">asLongStream()<br>asDoubleStream()</td>
  </tr>
  <tr>
    <td class="tg-0lax" colspan="3">4. 스트림 → 부분 스트림</td>
  </tr>
  <tr>
    <td class="tg-0lax">Stream&lt;T&gt;<br>IntStream</td>
    <td class="tg-0lax">Stream&lt;T&gt;<br>IntStream</td>
    <td class="tg-0lax">skip(long n)<br>limit(long maxSize)</td>
  </tr>
  <tr>
    <td class="tg-0lax" colspan="3">5. 두 개의 스트림 → 스트림</td>
  </tr>
  <tr>
    <td class="tg-0lax">Stream&lt;T&gt;, Stream&lt;T&gt;</td>
    <td class="tg-0lax">Stream&lt;T&gt;</td>
    <td class="tg-0lax">concat(Stream&lt;T&gt; a, Stream&lt;T&gt; b)</td>
  </tr>
  <tr>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">IntStream, IntStream</span></td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">IntStream</span></td>
    <td class="tg-0lax">concat(IntStream a, IntStream b)</td>
  </tr>
  <tr>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">LongStream, LongStream</span></td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">LongStream</span></td>
    <td class="tg-0lax">concat(LongStream a, LongStream b)</td>
  </tr>
  <tr>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">DoubleStream, DoubleStream</span></td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">DoubleStream</span></td>
    <td class="tg-0lax">concat(DoubleStream a, DoubleStream b)</td>
  </tr>
  <tr>
    <td class="tg-0lax" colspan="3">6. 스트림의 스트림  → 스트림</td>
  </tr>
  <tr>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">Stream&lt;Stream&lt;T&gt;&gt;</span></td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">Stream&lt;T&gt;</span></td>
    <td class="tg-0lax">flatMap(Function mapper)</td>
  </tr>
  <tr>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">Stream&lt;IntStream&gt;</span></td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">IntStream</span></td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">flatMapToInt(Function mapper)</span></td>
  </tr>
  <tr>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">Stream&lt;LongStream&gt;</span></td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">LongStream</span></td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">flatMapToLong(Function mapper)</span></td>
  </tr>
  <tr>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">Stream&lt;DoubleStream&gt;</span></td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">DoubleStream</span></td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">flatMapToDouble(Function mapper)</span></td>
  </tr>
  <tr>
    <td class="tg-0lax" colspan="3">7. 스트림 ↔ 병렬스트림</td>
  </tr>
  <tr>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">Stream&lt;T&gt;</span><br>IntStream<br>LongStream<br><span style="font-weight:400;font-style:normal">Double</span>Stream</td>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">Stream&lt;T&gt;</span><br>IntStream<br>LongStream<br><span style="font-weight:400;font-style:normal">Double</span><br>Stream</td>
    <td class="tg-0lax">parallel()<br>sequential()</td>
  </tr>
  <tr>
    <td class="tg-0lax" colspan="3">8. 스트림&nbsp;&nbsp;→ 컬렉션</td>
  </tr>
  <tr>
    <td class="tg-0lax" rowspan="3"><span style="font-weight:400;font-style:normal">Stream&lt;T&gt;</span><br>IntStream<br>LongStream<br>DoubleStream</td>
    <td class="tg-0lax">Collection&lt;T&gt;</td>
    <td class="tg-0lax">collect(Collectors.toCollection(Supplier factory))</td>
  </tr>
  <tr>
    <td class="tg-0lax">List&lt;T&gt;</td>
    <td class="tg-0lax">collect(Collectors.toList())</td>
  </tr>
  <tr>
    <td class="tg-0lax">Set&lt;T&gt;</td>
    <td class="tg-0lax">collect(Collectors.toSet())</td>
  </tr>
  <tr>
    <td class="tg-0lax" colspan="3">9. 컬렉션 → 스트림</td>
  </tr>
  <tr>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">Collection&lt;T&gt;</span><br><span style="font-weight:400;font-style:normal">List&lt;T&gt;</span><br><span style="font-weight:400;font-style:normal">Set&lt;T&gt;</span></td>
    <td class="tg-0lax">Stream&lt;T&gt;</td>
    <td class="tg-0lax">stream()</td>
  </tr>
  <tr>
    <td class="tg-0lax" colspan="3">10. 스트림&nbsp;&nbsp;→ Map</td>
  </tr>
  <tr>
    <td class="tg-0lax"><span style="font-weight:400;font-style:normal">Stream&lt;T&gt;</span><br>IntStream<br>LongStream<br><span style="font-weight:400;font-style:normal">DoubleStream</span></td>
    <td class="tg-0lax">Map&lt;K,V&gt;</td>
    <td class="tg-0lax">collect(Collectors.toMap(Function key, Function value))<br>collect(Collectors.toMap(Function k, Function v, BinaryOperator))<br>collect(Collectors.toMap(Function k, Function v, BinaryOperator merge, Supplier mapSupplier))</td>
  </tr>
  <tr>
    <td class="tg-0lax" colspan="3">11. 스트림&nbsp;&nbsp;→ 배열</td>
  </tr>
  <tr>
    <td class="tg-0lax" rowspan="2"><span style="font-weight:400;font-style:normal">Stream&lt;T&gt;</span></td>
    <td class="tg-0lax">Object[]</td>
    <td class="tg-0lax">toArray()</td>
  </tr>
  <tr>
    <td class="tg-0lax">T[]</td>
    <td class="tg-0lax">toArray(IntFunction&lt;A[]&gt; generator)</td>
  </tr>
  <tr>
    <td class="tg-0lax">IntStream<br>LongStream<br><br><span style="font-weight:400;font-style:normal">DoubleStream</span></td>
    <td class="tg-0lax">int[]<br>long[]<br>double[]</td>
    <td class="tg-0lax">toArray()</td>
  </tr>
</tbody>
</table>
