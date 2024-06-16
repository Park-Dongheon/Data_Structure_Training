package Chapter6_정렬알고리즘;

import java.util.Scanner;

public class MergeTest2 {
	
	static int[] buff;		// 작업용 배열
	
	private static void __mergeSort(int[] x, int left, int right) {
		if(left < right) {
			int i;
			int center = (left + right) / 2;
			int p = 0;
			int j = 0;
			int k = left;
			
			for (int ix = left; ix < right; ix++) {
				System.out.print(x[ix] + " ");
			}
			System.out.println();
			__mergeSort(x, left, center);			// 전반부를 병합 정렬
			__mergeSort(x, center + 1, right);		// 후반부를 병합 정렬
			
			// 배열의 앞부분(x[left] ~ x[center])을 buff[0] ~ buff[center - left]에 복사,
			// for 문이 끝날 때 p 값은 복사한 요솟수인 center - left + 1이 됨
			for (i = left; i < center + 1; i++) {
				buff[p++] = x[i];
			}
			
			
			
			while (i <= right && j < p)
				x[k++] = (buff[j] <= x[i]) ? buff[j++] : x[i++];
			
			while (j < p)
				x[k++] = buff[j++];
		}
	}
	
	private static void merge(int[] x, int nx) {
		buff = new int[nx];		// 작업용 배열을 생성, 크기 12
		
		__mergeSort(x, 0, nx - 1);		// 배열 전체를 병합 정렬
		
		buff = null;		// 작업용 배열을 해제
	}

	private static void showData(int[] x) {
		for(int i = 0; i < x.length; i++)
			System.out.print(x[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("병합 정렬");
		
		int x[] = {5, 6, 4, 8, 3, 7, 9, 0, 1, 5, 2, 3};
		int nx = 12;
		
		merge(x, nx);		// 배열 x를 병합 정렬
		
		System.out.println("오름차순으로 정렬했습니다.");
		showData(x);
	}


}
