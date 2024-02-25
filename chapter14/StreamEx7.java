package chapter14;

import java.util.stream.*;
import java.util.*;

public class StreamEx7 {
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

        System.out.println("1. 단순 분할(성별)");
        Map<Boolean, List<GymMember>> memBySex = Stream.of(memArr)
                .collect(Collectors.partitioningBy(GymMember::isMale));

        List<GymMember> maleMember = memBySex.get(true);
        List<GymMember> femaleMember = memBySex.get(false);

        for (GymMember m : maleMember)
            System.out.println(m);

        for (GymMember m : femaleMember)
            System.out.println(m);

        System.out.println("\n2. 단순분할 + 통계(성별 회원 수)");
        Map<Boolean, Long> memNumBySex = Stream.of(memArr)
                .collect(Collectors.partitioningBy(GymMember::isMale, Collectors.counting()));

        System.out.println("남 회원 수: " + memNumBySex.get(true));
        System.out.println("여 회원 수: " + memNumBySex.get(false));

        System.out.println("\n3. 단순분할 + 통계(성별별 비만도 1등)");
        Map<Boolean, Optional<GymMember>> topBMIBySex = Stream.of(memArr)
                .collect(Collectors.partitioningBy(GymMember::isMale,
                        Collectors.maxBy(Comparator.comparingDouble(GymMember::getBMI))));

        System.out.println("남회원 비만도 1등: " + topBMIBySex.get(true));
        System.out.println("여회원 비만도 1등: " + topBMIBySex.get(false));

        System.out.println("\n4. 다중분할(성별, 과체중 이상)");
        Map<Boolean, Map<Boolean, List<GymMember>>> overWeightMemBySex = Stream.of(memArr).collect(
                Collectors.partitioningBy(GymMember::isMale, Collectors.partitioningBy(m -> m.getBMI() >= 23)));

        List<GymMember> overWeightMaleMem = overWeightMemBySex.get(true).get(true);
        List<GymMember> overWeightFemaleMem = overWeightMemBySex.get(false).get(true);

        for (GymMember m : overWeightMaleMem)
            System.out.println(m);

        for (GymMember m : overWeightFemaleMem)
            System.out.println(m);

        System.out.println("\n5. 다중분할(성별, 저체중)");
        Map<Boolean, Map<Boolean, List<GymMember>>> underWeightMemBySex = Stream.of(memArr).collect(
                Collectors.partitioningBy(GymMember::isMale, Collectors.partitioningBy(m -> m.getBMI() < 18.5)));

        List<GymMember> underWeightMaleMem = underWeightMemBySex.get(true).get(true);
        List<GymMember> underWeightFemaleMem = underWeightMemBySex.get(false).get(true);

        for (GymMember m : underWeightMaleMem)
            System.out.println(m);

        for (GymMember m : underWeightFemaleMem)
            System.out.println(m);
    }
}
