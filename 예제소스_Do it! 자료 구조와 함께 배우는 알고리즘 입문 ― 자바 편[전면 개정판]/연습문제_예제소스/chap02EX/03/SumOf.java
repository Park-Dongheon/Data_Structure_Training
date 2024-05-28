// 연습2-3
// 배열의 모든 요소의 합계값을 구합니다

import java.util.Scanner;

class SumOf {

	//--- 배열 a의 요솟값을 출력 ---//
	static int sumOf(int[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++)
			sum += a[i];
		return sum;
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("요솟수는  : ");
		int num = stdIn.nextInt();		// 요솟수

		int[] a = new int[num];			// 요솟수가 num 인 배열 

		for (int i = 0; i < num; i++) {
			System.out.print("a[" + i + "] : ");
			a[i] = stdIn.nextInt();
		}

		System.out.println("모든 요소의 합계는 " + sumOf(a) + "입니다.");
	}
}
