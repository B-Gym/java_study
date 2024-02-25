package chapter14;

import java.util.stream.*;
import java.util.*;

public class StreamEx8 {
    public static void main(String[] args) {
        GymMember[] memArr = {
                new GymMember("최찬희", 20240325, 22, 167, 60, false),
                new GymMember("이주연", 20240521, 24, 180, 60, false),
                new GymMember("김선우", 20240819, 23, 169, 50, true),
                new GymMember("지창민", 20241220, 22, 178, 60, false),
                new GymMember("이상연", 20240505, 30, 190, 80, true),
                new GymMember("제이콥", 20240421, 23, 190, 100, true),
                new GymMember("에릭", 20240826, 40, 160, 80, false),
                new GymMember("케빈", 20240723, 33, 160, 40, false),
                new GymMember("주학년", 20240903, 22, 190, 40, true),
                new GymMember("김영훈", 20240424, 21, 178, 89, true),
                new GymMember("이현재", 20240321, 19, 167, 40, true),
                new GymMember("강동원", 20240812, 17, 150, 30, false)
        };

        System.out.println("1. 단순그룹화 (나이별로 그룹화)");
        Map<Integer, List<GymMember>> memByAge = Stream.of(memArr)
                .collect(Collectors.groupingBy(GymMember::getAge));

        for (List<GymMember> age : memByAge.values()) {
            for (GymMember m : age)
                System.out.println(m);
        }

        System.out.println("\n2. 단순그룹화 (비만도에 따른 그룹화)");
        Map<GymMember.BMI, List<GymMember>> memByBMI = Stream.of(memArr)
                .collect(Collectors.groupingBy(m -> {
                    if (m.getBMI() < 18.5)
                        return GymMember.BMI.UNDERWEIGHT;
                    else if (m.getBMI() < 23)
                        return GymMember.BMI.NORMAL;
                    else if (m.getBMI() < 25)
                        return GymMember.BMI.OVERWEIGHT;
                    else
                        return GymMember.BMI.OBESITY;
                }));
        TreeSet<GymMember.BMI> keySet = new TreeSet<>(memByBMI.keySet());

        for (GymMember.BMI key : keySet) {
            System.out.println("[" + key + "]");
            for (GymMember m : memByBMI.get(key))
                System.out.println(m);
            System.out.println();
        }

        System.out.println("\n3. 단순그룹화 + 통계 (비만도에 따른 회원수)");
        Map<GymMember.BMI, Long> memCntByBMI = Stream.of(memArr)
                .collect(Collectors.groupingBy(m -> {
                    if (m.getBMI() < 18.5)
                        return GymMember.BMI.UNDERWEIGHT;
                    else if (m.getBMI() < 23)
                        return GymMember.BMI.NORMAL;
                    else if (m.getBMI() < 25)
                        return GymMember.BMI.OVERWEIGHT;
                    else
                        return GymMember.BMI.OBESITY;
                }, Collectors.counting()));

        for (GymMember.BMI key : memCntByBMI.keySet())
            System.out.print("[" + key + "] - " + memCntByBMI.get(key) + "명");

    }
}
