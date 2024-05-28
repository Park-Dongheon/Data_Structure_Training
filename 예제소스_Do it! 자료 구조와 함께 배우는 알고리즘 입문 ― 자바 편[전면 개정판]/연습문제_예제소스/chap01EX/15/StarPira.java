// 연습1-15
// 특수문자 *으로 피라미드를 출력

import java.util.Scanner;

class StarPira {

	//--- 특수문자 * 을 나열하여 피라미드를 출력 ---//
	static void spira(int n) {
		for (int i = 1; i <= n; i++) {					// i 행( i = 1, 2, ... , n )
			for (int j = 1; j <= n - i; j++)			// n - i 개의 ' ' 을 출력
				System.out.print(' ');
			for (int j = 1; j <= (i-1)*2+1; j++)	// (i - 1) * 2 + 1 개의 '*' 을 출력
				System.out.print('*');
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

		spira(n);					// 피라미드를 출력
	}
}

