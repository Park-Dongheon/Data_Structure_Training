// 연습4-6
// 제네릭 용 큐의 테스트 프로그램

import java.util.Scanner;

class QueueTester {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		Queue<String> s = new Queue<String>(64);			// 최대 64개를 넣을 수 있는 큐

		while (true) {
			System.out.printf("현재 데이터 개수 : %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1) 인큐　(2) 디큐　(3) 피크　" +
											 "(4) 덤프　(5) 검색 (0) 종료 : ");

			int menu = stdIn.nextInt();
			if (menu == 0) break;

			int idx;
			String x;

			switch (menu) {
			 case 1:														//  인큐
				System.out.print("데이터 : ");
				x = stdIn.next();
				try {
					s.enque(x);
				 } catch (Queue.OverflowGqueueException e) {
					System.out.println("큐가 가득 찼습니다.");
				}
				break;

			 case 2:														//  디큐
				try {
					 x = s.deque();
					System.out.println(" 디큐한 데이터는 " + x + "입니다.");
				 } catch (Queue.EmptyGqueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			 case 3:														// 피크
				try {
					 x = s.peek();
					System.out.println("피크한 데이터는 " + x + "입니다.");
				 } catch (Queue.EmptyGqueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			 case 4:														// 덤프
				s.dump();
				break;

			 case 5: { 								// 검색
				System.out.print("데이터 : ");
				String str = stdIn.next();
				int n = s.search(str);
				if (n != 0)
					System.out.printf("%d번째 데이터로 인덱스%d의 위치에 저장되어 있습니다.\n", n, s.indexOf(str));
				else
					System.out.println("그 데이터는 등록되어 있지 않습니다.");
				break;
			 }
			}
		}
	}
}

