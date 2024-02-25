package chapter14;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class StreamEx6 {
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

                // 학생 이름만 추출하여 List<String>에 저장
                List<String> names = Stream.of(stuArr)
                                .map(Student::getName)
                                .collect(Collectors.toList());

                System.out.println(names);

                // 스트림을 배열로 변환 : toArray()
                Student[] stuArr2 = Stream.of(stuArr).toArray(Student[]::new);

                for (Student s : stuArr2)
                        System.out.println(s);

                // 스트림을 맵으로 변환(학생 이름이 Key) : toMap()
                Map<String, Student> stuMap = Stream.of(stuArr)
                                .collect(Collectors.toMap(s -> s.getName(), Function.identity()));

                for (String name : stuMap.keySet())
                        System.out.println(name + " - " + stuMap.get(name));

                long count = Stream.of(stuArr)
                                .collect(Collectors.counting());
                long totalScore = Stream.of(stuArr)
                                .collect(Collectors.summingInt(Student::getTotalScore));

                System.out.println("count = " + count);
                System.out.println("totalScore = " + totalScore);

                totalScore = Stream.of(stuArr)
                                .collect(Collectors.reducing(0, Student::getTotalScore, Integer::sum));
                System.out.println("totalScore = " + totalScore);

                Optional<Student> topStudent = Stream.of(stuArr)
                                .collect(Collectors.maxBy(Comparator.comparingInt(Student::getTotalScore)));
                System.out.println("topStudent = " + topStudent.get());

                IntSummaryStatistics stat = Stream.of(stuArr)
                                .collect(Collectors.summarizingInt(Student::getTotalScore));
                System.out.println(stat);

                String stuNames = Stream.of(stuArr)
                                .map(Student::getName)
                                .collect(Collectors.joining(", ", "{", "}"));
                System.out.println(stuNames);

        }
}