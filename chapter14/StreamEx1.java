package chapter14;

import java.util.*;
import java.util.stream.*;

public class StreamEx1 {
    public static void main(String[] args) {
        Stream<Student> stuStream = Stream.of(
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
                new Student("이상연", 4, 240));

        // 1) 반 별로 정렬 Comparator.comparing(Student::getBan)
        // 2) Student에서 미리 정의한기본 정렬 기준에 맞춰 정렬 thenComparing(Comparator.naturalOrder())
        // 3) 이름 순 정렬 thenComparing(Student::getName)
        stuStream
                .sorted(Comparator.comparing(Student::getBan).thenComparing(Comparator.naturalOrder())
                        .thenComparing(Student::getName))
                .forEach(System.out::println);
    }

}

class Student implements Comparable<Student> {
    String name;
    int ban;
    int totalScore;

    Student(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    public String toString() {
        return String.format("[%s, %d, %d]", name, ban, totalScore);
    }

    String getName() {
        return name;
    }

    int getBan() {
        return ban;
    }

    int getTotalScore() {
        return totalScore;
    }

    @Override
    public int compareTo(Student s) {
        return s.totalScore - this.totalScore;
    }

}
