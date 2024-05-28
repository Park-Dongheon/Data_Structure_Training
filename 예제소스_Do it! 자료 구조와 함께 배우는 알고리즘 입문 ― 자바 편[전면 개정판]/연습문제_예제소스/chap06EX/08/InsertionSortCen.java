// 연습6-8
// 단순삽입정렬(보초법 : 배열의 맨앞 요소는 비어 있음)

import java.util.Scanner;

class InsertionSortCen {

	//--- 단순삽입정렬(보초법 : 배열의 맨앞 요소는 비어 있음)---//
	static void insertionSort(int[] a, int n) {
		for (int i = 2; i < n; i++) {
			int tmp = a[0] = a[i];
			int j = i;
			for ( ; a[j - 1] > tmp; j--)
				a[j] = a[j - 1];
			if (j > 0) a[j] = tmp;
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("단순삽입정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx + 1];						// 여분으로 1개 더 생성

		for (int i = 1; i <= nx; i++) {				// x[1]~x[nx]에 입력 받음
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		insertionSort(x, nx + 1);							// 배열 x를 단순삽입정렬

		System.out.println("오름차순으로 정렬하였습니다.");
		for (int i = 1; i <= nx; i++)
			System.out.println("x[" + i + "]＝" + x[i]);
	}
}

