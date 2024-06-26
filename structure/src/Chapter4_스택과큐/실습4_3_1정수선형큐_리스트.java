package Chapter4_스택과큐;
/*
 * 실습3번 - 정수 선형 큐를 리스트로 구현
 * 교재 148 페이지는 원형큐를 배열로 구현한 코드 실습 4번에 활용
 */

import java.util.Scanner;

import Chapter4_스택과큐.IntQueue3.EmptyIntQueue3Exception;

/*
* Queue of ArrayList
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Queue4 {
	private List<Integer> que;// 선형큐 - 정수를 저장하는 리스트, 원형큐로 구현하지 않는다
	private int capacity; // 큐의 최대 크기
	private int front; // 맨 처음 요소 커서(Index)
	private int rear; // 맨 끝 요소 커서(Index)

//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String message) {
			super(message);
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException(String message) {
			super(message);
		}
	}

//--- 생성자(constructor) ---//
	public Queue4(int maxlen) {		// 큐의 최대 크기를 인자로 받아 큐를 초기화
		front = rear = 0;		// front와 rear를 0으로 초기화
		capacity = maxlen;		// que 생성시 크기 초기화
		try {
			que = new ArrayList<Integer> ();	// que를 Integer 타입의 ArrayList로 초기화
		} catch (OutOfMemoryError e) {		// OutOfMemoryError 예외 처리
			capacity = 0;		// que의 크기를 0으로 초기화
		}
	}

//--- 큐에 데이터를 인큐 ---//
	public int enque(int x) throws OverflowQueueException {
		if(isFull())		// rear가 que의 크기보다 크거나 같으면
			throw new OverflowQueueException("Enque: overflow");		// OverflowQueueException를 발생
		que.add(x);		// que에 데이터를 추가
		rear++;		// rear 증가
		return x;		// x값 반환
	}

//--- 큐에서 데이터를 디큐 ---//
	public int deque() throws EmptyQueueException {
		if(isEmpty())		// que의 크기가 0보다 작거나 같으면
			throw new EmptyQueueException("Qeque: Queue is empty");		// EmptyQueueException를 발생
		int x = que.remove(0);		// que에서 첫 번째 데이터를 제거하고 x에 저장
		rear--;
		return x;		// x반환

	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public int peek() throws EmptyQueueException {
		if(isEmpty())		// 큐가 비어 있으면
			throw new EmptyQueueException("Peek: Queue is empty");		// EmptyQueueException를 발생
		return que.get(front);		//front 위치의 데이터를 반환
	}

//--- 큐를 비움 ---//
	public void clear() {
		front = rear = 0;		// front와 rear를 0으로 초기화
		que.clear();		// que를 비움
	}

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(int x) {		// 데이터를 검색하여 인덱스를 반환하는 메서드 indexOf 선언
		if(isEmpty())
			return -1;
		for(int i = 0; i < que.size(); i++) {		// que의 모든 데이터에 대해서 반복
			if(que.get(i) == x )		// que에서 i 위치의 데이터가 x와 같으면
				return i;		// i 반환
		}
		return -1;		// x를 찾지 못하면 -1 반환
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {		// 큐의 최대 크기를 반환하는 메서드
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {			// 큐에 쌓여 있는 데이터 개수를 반환하는 메서드
		return rear - front;
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {		// 큐가 비어있는지 확인하는 메서드
		return rear <= front;		// rear가 front보다 작거나 같으면 true 반환
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {		// 큐가 가득 찼는지 확인하는 메서드
		return size() >= capacity;		// que의 크기가 최대 크기와 같거나 크면 true 반환
	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		if(isEmpty())		// 큐가 비어 있으면 출력 
			throw new EmptyQueueException("Queue is Empty");
		else {
			for(int i = front; i < rear; i++) {		// front부터 rear까지 데이터 출력
				System.out.print(que.get(i) + " ");
			}
			System.out.println();
		}
	}
}

public class 실습4_3_1정수선형큐_리스트 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		Queue4 oq = new Queue4(12); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, p = 0;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프  (5)검색　(0)종료: ");
			int menu = stdIn.nextInt();
			switch (menu) {
			
			case 1: // 인큐
				rndx = random.nextInt(1, 20);
				System.out.print("입력데이터: (" + rndx +")" + "\n");
				try {
					oq.enque(rndx);
				} catch(Queue4.OverflowQueueException e) {
					System.out.println("\nstack이 가득찼있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("\n디큐한 데이터는 " + p + "입니다.");
				} catch (Queue4.EmptyQueueException e) {
					System.out.println("\n큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("\n피크한 데이터는 " + p + "입니다.");
				} catch (Queue4.EmptyQueueException e) {
					System.out.println("\n큐가 비어 있습니다.");
				}
				break;

			case 4: // 덤프
				try {
					oq.dump();					
				} catch (Queue4.EmptyQueueException e) {
					System.out.println("\n큐가 비어 있습니다.");
				}
				break;
			
			case 5: // 검색
				System.out.print("검색할 데이터: ");
				int key = stdIn.nextInt();
				int idx = oq.indexOf(key);
				if(idx != -1)
					System.out.println("데이터: " + key + ", " + "Index: " + idx);
				else
					System.out.println("데이터: " + key + "를 찾을 수 없습니다.");
				break;
				
			case 0:	// 종료
				stdIn.close();
				return;
				
			default:
				System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
				break;
			}
		}
	}
}