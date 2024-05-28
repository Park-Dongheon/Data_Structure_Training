// 연습4-3
// 머리가 둘인 int형 고정 길이 스택의 사용 예(DoubleHeadIntStackTester클래스의 모든 메서드 를 사용)

import java.util.Scanner;

class DoubleHeadIntStackTester {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		DoubleHeadIntStack s = new DoubleHeadIntStack(64);	// 최대 64개 푸시할 수 있는 머리가 둘인 스택

		while (true) {
			System.out.println("현재 데이터 개수 : "+
												 "A : " + s.size(DoubleHeadIntStack.AorB.StackA) + "/" + 
												 "B : " + s.size(DoubleHeadIntStack.AorB.StackB));
			System.out.print("( 1) A 푸시 ( 2) A 팝 ( 3) A 피크 " + "( 4) A 덤프 ( 5) A 검색 ( 6) A 비움\n" +
							 "( 7) B 푸시 ( 8) B 팝 ( 9) B 피크 " + "(10) B 덤프 (11) B 검색 (12) B 비움\n" +
							 "(13)   출력 ( 0) 종료 : ");

			int menu = stdIn.nextInt();
			if (menu == 0) break;

			int n, x = 0;
			switch (menu) {
			 case 1:													// A에 푸시
				System.out.print("데이터 : ");
				x = stdIn.nextInt();
				try {
					s.push(DoubleHeadIntStack.AorB.StackA, x);
				 } catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
					System.out.println("스택이 가득 찼습니다.");
				}
				break;

			 case 2:													// A에서 팝
				try {
					 x = s.pop(DoubleHeadIntStack.AorB.StackA);
					System.out.println("팝한 데이터는 " + x + "입니다.");
				 } catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
					System.out.println("스택이 비어 있습니다.");
				}
				break;

			 case 3:													// A에서 피크
				try {
					 x = s.peek(DoubleHeadIntStack.AorB.StackA);
					System.out.println("피크한 데이터는 " + x + "입니다.");
				 } catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
					System.out.println("스택이 비어 있습니다.");
				}
				break;

			 case 4:													// A를 덤프
				s.dump(DoubleHeadIntStack.AorB.StackA);
				break;

			 case 5:													// A에서 검색
				System.out.print("검색할 데이터 : ");
				x = stdIn.nextInt();
				n = s.indexOf(DoubleHeadIntStack.AorB.StackA, x);
				if (n >= 0)
					System.out.println("꼭대기에서 " + (s.size(DoubleHeadIntStack.AorB.StackA) - n) +"번째에 있습니다.");
				else
					System.out.println("그 데이터가 없습니다.");
				break;

			 case 6:													// 비웁니다
				s.clear(DoubleHeadIntStack.AorB.StackA);
				break;

			 case 7:													// B에 푸시
				System.out.print("데이터 : ");
				x = stdIn.nextInt();
				try {
					s.push(DoubleHeadIntStack.AorB.StackB, x);
				 } catch (DoubleHeadIntStack.OverflowDoubleHeadIntStackException e) {
					System.out.println("스택이 가득 찼습니다.");
				}
				break;

			 case 8:													// B에서 팝
				try {
					 x = s.pop(DoubleHeadIntStack.AorB.StackB);
					System.out.println("팝한 데이터는 " + x + "입니다.");
				 } catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
					System.out.println("스택이 비어 있습니다.");
				}
				break;

			 case 9:													// B에서 피크
				try {
					 x = s.peek(DoubleHeadIntStack.AorB.StackB);
					System.out.println("피크한 데이터는 " + x + "입니다.");
				 } catch (DoubleHeadIntStack.EmptyDoubleHeadIntStackException e) {
					System.out.println("스택이 비어 있습니다.");
				}
				break;

			 case 10:													// B를 덤프
				s.dump(DoubleHeadIntStack.AorB.StackB);
				break;

			 case 11:													// B에서 검색
				System.out.print("검색할 데이터 : ");
				x = stdIn.nextInt();
				n = s.indexOf(DoubleHeadIntStack.AorB.StackB, x);
				if (n >= 0)
					System.out.println("꼭대기에서 " + (s.size(DoubleHeadIntStack.AorB.StackB) - (s.getCapacity() - n) + 1) +"번째에 있습니다.");
				else
					System.out.println("그 데이터가 없습니다.");
				break;

			 case 12:													// 비웁니다
				s.clear(DoubleHeadIntStack.AorB.StackB);
				break;

			 case 13:													// 데이터 출력
				System.out.println("용량 : " + s.getCapacity());
				System.out.println("A의 데이터수 : " + s.size(DoubleHeadIntStack.AorB.StackA));
				System.out.println("B의 데이터수 : " + s.size(DoubleHeadIntStack.AorB.StackB));
				System.out.println("A는 비어 " + (s.isEmpty(DoubleHeadIntStack.AorB.StackA) ? "있습니다."
																										: "있지 않습니다."));
				System.out.println("B는 비어 " + (s.isEmpty(DoubleHeadIntStack.AorB.StackB) ? "있습니다."
																										: "있지 않습니다."));
				System.out.println("가득 차 " + (s.isFull() ? "있습니다."
																										: "있지 않습니다."));
				break;
			}
		}
	}
}

