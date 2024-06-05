package Chap5_Recursive;
/*
 * 실습 5-8: 하노이의 탑
 * 일본 수학 퀴즈 문제: 5개의 선반을 모두 이동하는 데 몇번 만에 이동하나?
 * n = 3일 때는 7, n= 5일 때는 31 - 퀴즈로 너무 어렵다 
 * 하노이의 탑 - 나무위키
 * 구글링: "하노이의 탑(개념 이해하기) 알고리즘, Khan Academy
 */

import java.util.Scanner;
public class 실습5_6Hanoi {
	static int count = 0;
	//--- no개의 원반을 x번 기둥에서 y번 기둥으로 옮김 ---//
	static void move(int no, int x, int y) {//no == 4인 경우
		if (no > 1) {
			move(no - 1, x, 6 - x - y);//no = 3개를 1 -> 2 로 이동
		}
		
		System.out.printf("원반[%d]를 %d번 기둥에서 %d번 기둥으로 옮김\n", no, x, y);//이동하는 작업으로 간주한다
		count++;
		if (no > 1) {
			move(no - 1, 6 - x - y, y);//no = 3개를 2 -> 3으로 이동
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("하노이의 탑");
		System.out.print("원반의 개수 : ");
		int n = stdIn.nextInt();

		move(n, 1, 3);    // 제 1 기둥에 쌓인 n개를 제 3 기둥으로 옮김
		System.out.println("원반 이동 횟수 = " + count);
	}
}