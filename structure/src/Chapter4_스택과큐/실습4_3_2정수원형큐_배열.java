package Chapter4_스택과큐;
/*
 * 실습 4번 - 정수 배열 원형 큐
 * 교재 148 ~ 157 페이지
 * 교재 소스 코드를 보지 않고 구현 완성 연습 필요 
 */
import java.util.Random;
/*
 * 큐 1번 실습 코드 - 정수들의 큐
 */
import java.util.Scanner;

//int형 고정 길이 큐
class IntQueue3 {
	private int[] que; // 큐용 배열, 본체를 참조하는 배열 변수 선언
	private int capacity; // 큐의 최대 용량을 저장
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
//	private int num; // 현재 데이터 수

//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyIntQueue3Exception extends RuntimeException {
		// 큐가 비어 있을 때 던질 예외
		public EmptyIntQueue3Exception(String message) {
			super(message);		// 예외 메시지 설정
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowIntQueue3Exception extends RuntimeException {
		// 
		public OverflowIntQueue3Exception(String message) {
			super(message);
		}
	}

//--- 생성자(constructor) ---//
	public IntQueue3(int maxlen) {
		front = rear = -1;	// 맨 처음 요소, 맨 끝 요소 커서, 큐가 비어있음을 나타내기 위해 front와 rear를 -1로 초기화
		capacity = maxlen;	// 배열 que에 저장할 수 있는 최대 요솟수 
		try {
			que = new int[capacity];	// 큐 본체용 배열을 생성
		}catch (OutOfMemoryError e) {	// 생성할 수 없을 때
			capacity = 0;		// 큐 용량을 0으로 설정
		}
	}

//--- 큐에 데이터를 인큐 ---//
	public int enque(int x) throws OverflowIntQueue3Exception {
		if(isFull())		// 큐가 가득 찼는지 확인
			throw new OverflowIntQueue3Exception("Enque: overflow");		// 큐가 가득 찼다면 예외 발생
		if(rear == -1)		// 큐가 비어있을 경우
			front = rear = 0;		// front와 rear를 0으로 초기화
		else
			rear = (rear + 1) % capacity;		// rear를 순환하도록 증가
		que[rear] = x;		// 큐의 rear 위치에 데이터 저장
		return x;		// 인큐된 데이터 반환
	}

//--- 큐에서 데이터를 디큐 ---//
	public int deque() throws EmptyIntQueue3Exception {
		if(isEmpty())		// 큐가 비어있는지 확인
			throw new EmptyIntQueue3Exception("Dequeue: Queue is empty");		// 큐가 비어있다면 예외 발생
		int x = que[front];		// front 위치의 데이터를 x에 저장
		if(front == rear)		// 큐에 데이터가 하나만 있었을 경우
			front = rear = -1;		// 큐를 비움
		else
			front = (front + 1) % capacity;		// front를 순환하도록 증가
		return x;		// 디큐된 데이터 반환
	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public int peek() throws EmptyIntQueue3Exception {
		if(isEmpty())		// 큐가 비어있는지 확인
			throw new EmptyIntQueue3Exception("Peek: Queue is empty");		// 큐가 비어있다면 예외 발생
		return que[front];		// front 위치의 데이터 반환
	}

//--- 큐를 비움 ---//
	public void clear() {
		front = rear = -1;		// 큐를 비우기 위해 front와 rear를 -1로 설정
	}

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(int x) {	
		if(isEmpty())		// 큐가 비어있는지 확인
			return -1;		// 큐가 비어있다면 -1 반환
		for(int i = 0; i < size(); i++) {		// 큐의 모든 데이터에 대해서 반복
			int idx = (i + front) % capacity;		// 순환 큐의 인덱스 계산
			if(que[idx] == x)		// 큐에서 index 위치의 데이터가 x와 같으면
				return idx;		//  index반환
		}
		return -1;		// 데이터가 없다면 -1 반환
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;		// 큐의 최대 용량 반환
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		if(isEmpty())		// 큐가 비어있는지 확인, 비어있다면 0 반환
			return 0;
		if(rear >= front)		// rear가 front보다 크거나 같을 때
			return rear - front + 1;		// 큐의 데이터 개수 계산
		else
			return capacity - front + rear + 1;		// rear가 front보다 작을 때, 순환 큐의 데이터 개수 계산
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return front == -1;		// 큐가 비어있는지 확인
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return !isEmpty() && (rear + 1) % capacity == front;		// 큐가 가득 찼는지 확인
	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		if (isEmpty())		// 큐가 비어 있으면 출력 
			throw new EmptyIntQueue3Exception("Queue is Empty");		// 큐가 비어있다면 예외 발생
		else {
			for(int i = 0; i < size(); i++) {		// 큐의 모든 데이터에 대해서 반복
				int idx = (front + i) % capacity;		// 순환 큐의 인덱스 계산
				System.out.print(que[idx] + " ");		// 큐의 데이터를 출력
			}
			System.out.println();
		}
	}
}

public class 실습4_3_2정수원형큐_배열 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);		// 사용자 입력을 받기 위한 Scanner 객체 생성
		IntQueue3 oq = new IntQueue3(12); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();		// 랜덤 숫자 생성을 위한 Random 객체 생성
		int rndx = 0, p = 0;		// 임의의 정수와 디큐된 데이터를 저장할 변수 초기화
		while (true) {		// 무한 루프
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());		// 현재 데이터 개수와 큐 용량 출력
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프  (5)검색　(0)종료: ");		// 메뉴 출력
			int menu = stdIn.nextInt();		// 사용자가 선택한 메뉴 입력 받기
			switch (menu) {
			
			case 1: // 인큐
				rndx = random.nextInt(1, 20);		// 1부터 19사이의 랜덤 숫자 생성
				System.out.print("입력데이터: (" + rndx +")" + "\n");		// 입력된 데이터 출력
				try {
					oq.enque(rndx);		// 큐에 데이터를 인큐
				} catch(IntQueue3.OverflowIntQueue3Exception e) {		// 큐가 가득 찼을 때 예외 처리
					System.out.println("stack이 가득찼있습니다.");		// 예외 메시지 출력
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();		// 큐에서 데이터 디큐, 디큐한 데이터를 임의의 변수에 저장
					System.out.println("디큐한 데이터는 " + p + "입니다.");		// 디큐한 데이터 출력
				} catch (IntQueue3.EmptyIntQueue3Exception e) {		// 큐가 비어있을 때 예외 처리
					System.out.println("큐가 비어 있습니다.");		// 예외 메시지 출력
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();		// 큐에서 데이터 피크, 피크한 데이터를 임의의 변수에 저장
					System.out.println("피크한 데이터는 " + p + "입니다.");		// 피크한 데이터 출력
				} catch (IntQueue3.EmptyIntQueue3Exception e) {		// 큐가 비어있을 때 예외 처리
					System.out.println("큐가 비어 있습니다.");		// 예외 메시지 출력
				}
				break;

			case 4: // 덤프
				try {
					oq.dump();		// 큐의 모든 데이터를 출력
				} catch (IntQueue3.EmptyIntQueue3Exception e) {		// 큐가 비어있을 때 예외 처리
					System.out.println("큐가 비어 있습니다.");		// 예외 메시지 출력
				}
				break;
				
			case 5: // 검색
				System.out.print("검색할 데이터: ");
				int key = stdIn.nextInt();		// 검색할 데이터 입력 받기
				int idx = oq.indexOf(key);		// 큐에서 데이터 검색
				if(idx != -1)		// 데이터가 큐에 있다면 데이터와 인덱스 출력
					System.out.println("데이터: " + key + ", " + "Index: " + idx);
				else		// 데이터가 큐에 없다면 메시지 출력
					System.out.println("데이터: " + key + "를 찾을 수 없습니다.");
				break;
				
			case 0: // 종료
				stdIn.close();		// Scanner 객체 닫기
				return;		// 프로그램 종료
				
			default:
				System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
				break;
			}
		}
	}

}