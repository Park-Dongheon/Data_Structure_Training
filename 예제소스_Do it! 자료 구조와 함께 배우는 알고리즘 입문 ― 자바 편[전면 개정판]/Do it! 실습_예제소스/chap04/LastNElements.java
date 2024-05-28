// 원하는 개수만큼 값을 계속 입력받고, 요솟수가 N인 배열에 마지막 N개를 저장

import java.util.Scanner;

class LastNElements {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        final int N = 10;
        int[] a = new int[N];    // 읽어 들인 값을 저장
        int cnt = 0;             // 읽어 들인 개수
        int retry;               // 다시 한 번?

        System.out.println("정수를 입력하세요.");

        do {
            System.out.printf("%d번째 정수: ", cnt + 1);
            a[cnt++ % N] = stdIn.nextInt();
          System.out.print("계속 할까요? (Yes…1／No…0) : ");
            retry = stdIn.nextInt();
        } while (retry == 1);

        int i = cnt - N;
        if (i < 0) i = 0;

        for ( ; i < cnt; i++)
            System.out.printf("%2d번째 ＝ %d\n", i + 1, a[i % N]);
    }
}