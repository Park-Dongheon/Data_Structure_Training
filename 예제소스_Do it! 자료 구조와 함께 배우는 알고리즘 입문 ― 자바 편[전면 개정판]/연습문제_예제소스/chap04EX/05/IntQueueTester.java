// 연습4-5
// int형 고정 길이 큐의 테스트 프로그램(메서드 indexOf와 search의 호출하는 것을 추가)

import java.util.Scanner;

class IntQueueTester {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntQueue s = new IntQueue(64);	// 최대 64개 인큐할 수 있는 큐

		while (true) {
			System.out.printf("현재 데이터 개수 : %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1) 인큐　(2) 디큐　(3) 피크　" +
											 "(4) 덤프　(5) 검색 (0) 종료 : ");

			int menu = stdIn.nextInt();
			if (menu == 0) break;

			int x;
			switch (menu) {
			 case 1: 								//  인큐
				System.out.print("데이터 : ");
				x = stdIn.nextInt();
				try {
					s.enque(x);
			 	} catch (IntQueue.OverflowIntQueueException e) {
					System.out.println("큐가 가득 찼습니다.");
				}
				break;

			 case 2: 								//  디큐
				try {
			 		x = s.deque();
					System.out.println(" 디큐한 데이터는 " + x + "입니다.");
			 	} catch (IntQueue.EmptyIntQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			 case 3: 								// 피크
				try {
					x = s.peek();
					System.out.println("피크한 데이터는 " + x + "입니다.");
				} catch (IntQueue.EmptyIntQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			 case 4: 								// 덤프
				s.dump();
				break;

			 case 5: { 								// 검색
				System.out.print("데이터 : ");
				x = stdIn.nextInt();
				int n = s.search(x);
				if (n != 0)
					System.out.printf("%d번째 데이터로 인덱스%d의 위치에 저장되어 있습니다.\n", n, s.indexOf(x));
				else
					System.out.println("그 데이터는 등록되어 있지 않습니다.");
				break;
			 }
			}
		}
	}
}
