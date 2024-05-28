// +와 -를 번갈아 출력(1)(개선)

import java.util.Scanner;

class Alternative1a {

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int n;

        System.out.println("+와 -를 번갈아 n개 출력합니다.");

        do {
            System.out.print("n값: ");
            n = stdIn.nextInt();
        } while (n <= 0);

        for (int i = 1; i <= n; i++)
            if (i % 2 == 0)                    // 홀수
                System.out.print("-");
            else                               // 짝수
                System.out.print("+");
    }
}