package chapter14;

import java.util.stream.*;
import java.util.*;
import java.util.function.*;

public class CollectorEx1 {
    public static void main(String[] args) {
        String[] strArr = { "김치찌개", "된장찌개", "떡볶이" };
        Stream<String> strStream = Stream.of(strArr);

        String result = strStream.collect(new ConcatCollector());

        System.out.println(Arrays.toString(strArr));
        System.out.println("result = " + result);
    }
}

class ConcatCollector implements Collector<String, StringBuilder, String> {

    @Override
    public BiConsumer<StringBuilder, String> accumulator() {
        return StringBuilder::append;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return StringBuilder::append;
    }

    @Override
    public Function<StringBuilder, String> finisher() {
        return StringBuilder::toString;
    }

    @Override
    public Supplier<StringBuilder> supplier() {
        return StringBuilder::new;
    }

}
