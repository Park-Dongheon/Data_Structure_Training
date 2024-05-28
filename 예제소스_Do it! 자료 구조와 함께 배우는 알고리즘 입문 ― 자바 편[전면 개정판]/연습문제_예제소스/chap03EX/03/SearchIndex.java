// 연습3-3
// 특정 값을 갖는 배열 안의 모든 요소를 다른 배열에 copy 

import java.util.Scanner;

class SearchIndex {

	//--- 배열 a의 앞쪽 n개의 요소에서 key와 일치하는 모든 요소의 인덱스를  ---//
	//--- 배열 idx의 앞쪽부터 순서대로 저장하고 일치하는 요솟수를 반환합니다 ---//
	static int searchIdx(int[] a, int n, int key, int[] idx) {
		int count = 0;								// key와 일치하는 요솟수
		for (int i = 0; i < n; i++)
			if (a[i] == key)
				idx[count++] = i; 
		return count;
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("요솟수 : ");
		int num = stdIn.nextInt();
		int[] x = new int[num];					// 요솟수가 num 인 배열 
		int[] y = new int[num];					// 요솟수가 num 인 배열 

		for (int i = 0; i < num; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}
		System.out.print("검색 값 : "); 					// 키값을 입력받음
		int ky = stdIn.nextInt();

		int count = searchIdx(x, num, ky, y);		// 배열 x안의 값이 ky인 요소를 y에 copy 

		if (count == 0)
			System.out.println("그 값의 요소는 존재하지 않습니다.");
		else
			for (int i = 0; i < count; i++)
				System.out.println("그 값은 " + "x[" + y[i] + "]에 있습니다.");
	}
}

