package Chapter5_재귀알고리즘;
/*
 * //실습 5-5  재귀 알고리즘
 * 재귀에 대한 이해를 돕는 순수 재귀 메서드 - 직접 입력하여 학습
 * stack frame을 이해하는 것이 필요 :구글링 - "스택 프레임(stack frame)", TCP School 참조 
 */

import java.util.Scanner;

public class 실습5_3Recur {
 //--- 순수 재귀 메서드 ---//
 static void recur(int n) {
     if (n > 0) {
    	 System.out.println("recur(" + n + " - 1) 호출됨");
         recur(n - 1);
         System.out.println("n = " + n);
         System.out.println("recur(" + n + " - 2) 호출됨");
         recur(n - 2);	//꼬리 재귀 -> 제거
     }
 }

 public static void main(String[] args) {
     Scanner stdIn = new Scanner(System.in);
     // 처음에는 n = 2,3에 대하여 실행한다 다음에 5에 대하여 
     System.out.print("정수를 입력하세요 : ");
     int x = stdIn.nextInt();

     recur(x);
 }
}
