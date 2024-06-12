package Chapter6_정렬알고리즘;

//병합 정렬 -교재 버젼으로 사용하지 않음

import java.util.Scanner;

public class 실습6_12MergeSort {
 static int[] buff;    // 작업용 배열

 //--- a[left] ~ a[right]를 재귀적으로 병합정렬 ---//
 static void __mergeSort(int[] a, int left, int right) {
	 //body를 지우고 작성 훈련 연습이 도움이 된다 
     if (left < right) {
         int i;
         int center = (left + right) / 2;
         int p = 0;
         int j = 0;
         int k = left;
         for (int ix = left; ix <= right; ix ++)
        	 System.out.print(" " + a[ix]);
         System.out.println();
         __mergeSort(a, left, center);         // 전반부를 병합정렬
         __mergeSort(a, center + 1, right);    // 후반부를 병합정렬

         for (i = left; i <= center; i++)
             buff[p++] = a[i];

         while (i <= right && j < p)
             a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];

         while (j < p)
             a[k++] = buff[j++];

     }
 }
 static void showData(int[] d) {
	 System.out.println();
     for (int i = 0; i < d.length; i++)
         System.out.print(d[i] + " ");
     System.out.println();
 }
 //--- 병합 정렬 ---//
 static void mergeSort(int[] a, int n) {
     buff = new int[n];                    // 작업용 배열을 생성

     __mergeSort(a, 0, n - 1);            // 배열 전체를 병합 정렬

     buff = null;                         // 작업용 배열을 해제
 }

 public static void main(String[] args) {
     Scanner stdIn = new Scanner(System.in);

     System.out.println("병합 정렬");
     /*
     System.out.print("요솟수: ");
     int nx = stdIn.nextInt();
     int[] x = new int[nx];

     for (int i = 0; i < nx; i++) {
         System.out.print("x[" + i + "]: ");
         x[i] = stdIn.nextInt();
     }
	*/
     int x[] = {5,6,4,8,3,7,9,0,1,5,2,3};
     int nx = 12;
     mergeSort(x, nx);        // 배열 x를 병합정렬

     System.out.println("오름차순으로 정렬했습니다.");
     showData(x);
 }
}