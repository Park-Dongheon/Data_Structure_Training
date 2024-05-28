// 연습1-9
// 정수 b에서 정수 a을 뺀 값을 구합니다(b > a가 되도록 입력받음)

import java.util.Scanner;

class Difference {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("a의 값 : ");
		int a = stdIn.nextInt();

		int b;
		while (true) {
			System.out.print("b의 값 : ");
			b = stdIn.nextInt();
			if (b > a) break;
			System.out.println("a보다 큰 값을 입력하세요!");
		}

		System.out.println("b - a는 " + (b - a) + "입니다.");
	}
}

