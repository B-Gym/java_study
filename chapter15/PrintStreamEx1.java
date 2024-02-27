package chapter15;

import java.util.Date;

public class PrintStreamEx1 {
    public static void main(String[] args) {
        int i = 65;
        float f = 1234.56789f;

        Date d = new Date();

        System.out.printf("문자 %c의 코드는 %d%n", i, i);
        System.out.printf("%d는 8진수로 %o, 16진수로 %x%n", i, i, i);
        System.out.println();
        System.out.printf("123456789012345678901234567890%n");
        System.out.printf("%s%-5s%5s%n", "123", "123", "123");
        System.out.println();
        System.out.printf("%-8.1f%8.1f %e%n", f, f, f);
        System.out.println();
        System.out.printf("오늘은 %tY년 %tm월 %tD일 입니다.%n", d, d, d);
        System.out.printf("지금은 %tH시 %tM분 %tS초 입니다.\n", d, d, d);
        System.out.printf("지금은 %1$tH시 %1$tM분 %1$tS초 입니다.\n", d);
    }
}

// 실행 결과
// 문자 A의 코드는 65
// 65는 8진수로 101, 16진수로 41

// 123456789012345678901234567890
// 123123 123

// 1234.6 1234.6 1.234568e+03

// 오늘은 2024년 02월 02/27/24일 입니다.
// 지금은 18시 52분 35초 입니다.
// 지금은 18시 52분 35초 입니다.