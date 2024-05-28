// 연습6-9
// 이진삽입정렬

import java.util.Scanner;

class BinInsertionSort {

	//--- 이진삽입정렬 ---//
	static void binInsertionSort(int[] a, int n) {
		for (int i = 1; i < n; i++) {
			int key = a[i];
			int pl = 0;				// 검색 범위 맨앞의 인덱스
			int pr = i - 1;			// 　 〃 　맨끝의 인덱스
			int pc;					// 　 〃 　중앙의 인덱스
			int pd;					// 삽입하는 위치의 인덱스

			do {
				pc = (pl + pr) / 2;
				if (a[pc] == key)			// 검색 성공
					break;
				else if (a[pc] < key)
					pl = pc + 1;
				else
					pr = pc - 1;
			} while (pl <= pr);
			pd = (pl <= pr) ? pc + 1 : pr + 1;

			for (int j = i; j > pd; j--)
				a[j] = a[j - 1];
			a[pd] = key;
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("이진삽입정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		binInsertionSort(x, nx);			// 배열 x를 이진삽입정렬

		System.out.println("오름차순으로 정렬하였습니다.");
		for (int i = 0; i < nx; i++)
			System.out.println("x[" + i + "]＝" + x[i]);
	}
}

