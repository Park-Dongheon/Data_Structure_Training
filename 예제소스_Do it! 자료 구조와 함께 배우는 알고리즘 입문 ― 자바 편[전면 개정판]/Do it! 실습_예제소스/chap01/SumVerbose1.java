// 1, 2, …, n의 합과 그 값을 구하는 과정을 출력(1)
import java.util.Scanner;

class SumVerbose1 {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int n;

        System.out.println("1부터 n까지의 합을 구합니다.");

        do {
            System.out.print("n값: ");
            n = stdIn.nextInt();
        } while (n <= 0);

        int sum = 0;        // 합

        for (int i = 1; i <= n; i++) {
            if (i < n)        // 중간 과정
                System.out.print(i + " + ");
            else              // 마지막 과정
                System.out.print(i + " = ");
            sum += i;         // sum에 i를 더함
        }

        System.out.println(sum);
    }
}