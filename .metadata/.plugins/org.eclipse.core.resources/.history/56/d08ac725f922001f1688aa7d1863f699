package Chap5_Recursive;

/*
 * 실습 5-3: 피보나치 수열을 간결한 코딩으로 해결
 * 삼항 조건 연산자를 사용한 대표적 사례로서 기억해야 함
 * f(n) = f(n-1) + f(n-2)
 * f(n) = f(n-1) + f(n-2) + f(n-2)를 구현하는 실습: 현재 코드를 수정하여 완료 
 */


import java.util.Scanner;

public class 실습5_1_2피보나치수열 {

 //--- 음이 아닌 정수 n의 팩토리얼 값을 반환 ---//
 static int fibonacci(int n) {
	 //recursive 함수를 간결한 코딩으로 해결 - 학습 요점이다 
     return (n > 1) ? fibonacci(n - 1) + fibonacci(n - 2) : 1;
 }

 public static void main(String[] args) {
     Scanner stdIn = new Scanner(System.in);

     System.out.print("정수를 입력하세요 : ");
     int x = stdIn.nextInt();

     System.out.println(x + "의 피보나치 수열은 " + fibonacci(x) + "입니다.");
 }
}
