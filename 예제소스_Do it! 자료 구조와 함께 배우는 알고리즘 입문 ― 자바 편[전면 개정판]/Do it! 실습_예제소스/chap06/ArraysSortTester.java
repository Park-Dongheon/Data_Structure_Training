// Arrays.sort에 의한 정렬(퀵 정렬)

import java.util.Arrays;
import java.util.Scanner;

class ArraysSortTester {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.print("요솟수: ");
        int num = stdIn.nextInt();
        int[] x = new int[num];     // 길이가 num인 배열

        for (int i = 0; i < num; i++) {
            System.out.print("x[" + i + "]: ");
            x[i] = stdIn.nextInt();
        }

        Arrays.sort(x);    // 배열 x를 정렬

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < num; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
}
