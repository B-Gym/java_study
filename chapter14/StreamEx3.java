package chapter14;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx3 {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("최찬희", 1, 300),
                new Student("지창민", 1, 200),
                new Student("이주연", 3, 270),
                new Student("김선우", 2, 199),
                new Student("김영훈", 1, 299),
                new Student("제이콥", 2, 199),
                new Student("케빈", 2, 189),
                new Student("에릭", 4, 199),
                new Student("주학년", 4, 200),
                new Student("이현재", 4, 170),
                new Student("이상연", 4, 240)
        };

        Stream<Student> stuStream = Stream.of(stuArr);

        stuStream.sorted(Comparator.comparing(Student::getBan)
                .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);

        stuStream = Stream.of(stuArr);
        IntStream stuScoreStream = stuStream.mapToInt(Student::getTotalScore);

        IntSummaryStatistics stat = stuScoreStream.summaryStatistics();
        System.out.println("count = " + stat.getCount());
        System.out.println("sum = " + stat.getSum());
        System.out.printf("average = %.2f \n", stat.getAverage());
        System.out.println("max = " + stat.getMax());
        System.out.println("min = " + stat.getMin());
    }
}
