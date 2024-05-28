// 연습1-16
// 숫자를 나열하여 피라미드를 출력

import java.util.Scanner;

class NumPira {

	//--- 숫자를 나열하여 피라미드를 출력 ---//
	static void npira(int n) {
		for (int i = 1; i <= n; i++) {					// i 행( i = 1, 2, ... , n )
			for (int j = 1; j <= n - i; j++)			// n - i 개의 ' ' 을 출력
				System.out.print(' ');
			for (int j = 1; j <= (i-1)*2+1; j++)	// (i - 1) * 2 + 1개의 '*' 을 출력
				System.out.print(i % 10);
			System.out.println();									// 줄바꿈
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int n;

		System.out.println("피라미드를 출력합니다.");

		do {
			System.out.print("단수는  : ");
			n = stdIn.nextInt();
		} while (n <= 0);

		npira(n);					// 피라미드를 출력
	}
}