// 연습6-7
// 단순삽입정렬(삽입 과정을 자세히 출력)

import java.util.Scanner;

class InsertionSortEx {

	//--- 단순삽입정렬 ---//
	static void insertionSort(int[] a, int n) {
		for (int i = 1; i < n; i++) {
			for (int m = 0; m < n; m++)
				System.out.printf("%3d ", a[m]);
			System.out.println();

			int j;
			int tmp = a[i];
			for (j = i; j > 0 && a[j - 1] > tmp; j--)
				a[j] = a[j - 1];
			a[j] = tmp;

			System.out.print(" ".repeat(4 * j));
			System.out.print(i != j ? "^-" : "  ");
			System.out.print("-".repeat(4 * (i - j)));
			System.out.println("+");
			System.out.printf("a[%d]의 %d을 a[%d]의 위치에 삽입하였습니다.\n\n", i, tmp, j);
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("단순삽입정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		insertionSort(x, nx);			// 배열 x를 단순삽입정렬

		System.out.println("오름차순으로 정렬하였습니다.");
		for (int i = 0; i < nx; i++)
			System.out.println("x[" + i + "]＝" + x[i]);
	}
}

