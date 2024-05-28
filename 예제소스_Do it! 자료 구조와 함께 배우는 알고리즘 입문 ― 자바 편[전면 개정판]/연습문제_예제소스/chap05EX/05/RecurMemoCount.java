// 연습5-5(2)
// 재귀 메서드 recur를 메모화로  구현(호출한 회수를 카운트)
//
import java.util.Scanner;

class RecurMemoCount {

	static String[] memo;
	static int count;

	//--- 메모화를 도입한 메서드 recur ---//
	static void recur(int n) {
		count++;
		if (memo[n + 1] != null)
			System.out.print(memo[n + 1]);											// 메모를 출력
		else {
			if (n > 0) {
				recur(n - 1);
				System.out.println(n);
				recur(n - 2);
				memo[n + 1] = memo[n] + n + "\n" + memo[n - 1];		// 메모화
			} else {
				memo[n + 1] = "";	 // 메모화 : recur(0)와 recur(-1)은 빈 문자열
			}
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("정수를 입력하세요 : ");
		int x = stdIn.nextInt();

		memo = new String[x + 2];
		recur(x);

		System.out.println("메서드를 호출한 회수 : " + count);		
	}
}
