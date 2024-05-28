// 1, 2, …, n 의 합을 구합니다.(구하는 과정의 식을 출력 :２[개선])

import java.util.Scanner;

class SumVerbose2a {

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int n;

        System.out.println("1부터 n까지의 합을 구합니다.");

        do {
            System.out.print("n 값 : ");
            n = stdIn.nextInt();
        } while (n <= 0);

        int sum = 0;        // 합

        for (int i = 1; i < n; i++) {
            System.out.print(i + " + ");
            sum += i;        // sum에 i를 더함
        }

        System.out.println(n + " = " + (sum += n));
    }
}
