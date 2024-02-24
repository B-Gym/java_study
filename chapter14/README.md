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

스트림은 추상화한 데이터를 다루는데 자주 사용되는 메서드를 정의해 놓았기 떄문에 데이터 소스의 타입이 무엇이던 같은 방식으로 다룰 수 있어 코드의 재사용성을 높인다.

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

```java

```

<b></br> 📌 `Stream<T[]>`를 `Stream<T>`로 변환 - `flatMap()` </b>

```java

```

</br>

### 2-4. `Optional<T>`와 `OptionalInt`

</br>

### 2-5. 스트림의 최종연산

</br>

### 2-6. collect()

</br>

### 2-7. Collector 구현하기

</br>

### 2-8. 스트림의 변환