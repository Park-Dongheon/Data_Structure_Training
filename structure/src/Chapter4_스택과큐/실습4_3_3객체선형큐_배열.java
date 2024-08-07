package Chapter4_스택과큐;

/*
 * 실습 5번 객체 원형 큐를 배열로 구현
 * 교재 148 실습 4_3은 정수 원형 큐를 배열로 구현한 코드임 > 객체 버젼으로 구현
 */
// List를 사용한 선형 큐 구현 - 큐는 배열 사용한다 
import java.util.Random;
import java.util.Scanner;

/*
* Queue of ArrayList of Point
*/

//Point3 클래스 선언
class Point3 {
	private int ix;		// x좌표 변수
	private int iy;		// y좌표 변수
	
	// Point3 클래스 생성자
	public Point3(int rndx, int rndy) {
		this.ix = rndx;		// x좌표 초기화
		this.iy = rndy;		// y좌표 초기화
	}

	// equals 메서드 재정의
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;		// 객체 자신과 비교하여 같으면 true 반환
		if (obj == null || getClass() != obj.getClass()) return false;		// 객체가 null이거나 클래스가 다르면 false 반환
		Point3 p = (Point3) obj;		// Point3 객체로 캐스팅
		return ix == p.ix && iy == p.iy;		// x와 y 좌표가 모두 같으면 true 반환
	}

	// toString 메서드 재정의
	@Override
	public String toString() {
		return "(" + ix + ", " + iy + ") ";		// x와 y 좌표를 문자열로 반환
	}
	
}

//int형 고정 길이 큐
class objectQueue2 {		// objectQueue2 클래스 선언
	private Point3[] que;
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
//	private int num; // 현재 데이터 개수

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
	public objectQueue2(int maxlen) {
		front = rear = 0;		// front와 rear를 0으로 초기화
		capacity = maxlen;		// 크기를 매개변수로 받은 값으로 초기화
		try {
			que = new Point3[capacity];		// Point3 배열 생성
		} catch (OutOfMemoryError e) {		// 메모리 부족 시 크기를 0으로 초기화
			capacity = 0;
		}
	}

//--- 큐에 데이터를 인큐 ---//
	public Point3 enque(Point3 x) throws OverflowQueueException {
		if(isFull()) {		// 큐가 가득 찼을 때 예외 발생
			throw new OverflowQueueException("Queue is Full");
		}
		que[rear++] = x;		// rear가 가리키는 위치에 데이터 저장 후 rear 증가
		return x;		// 저장된 데이터 반환
	}

//--- 큐에서 데이터를 디큐 ---//
	public Point3 deque() throws EmptyQueueException {
		if(isEmpty()) {		// 큐가 비어 있을 때 예외 발생
			throw new EmptyQueueException("Queue is Empty");
		}
		Point3 x = que[front];		// front가 가리키는 데이터를 x에 저장
		rear--;		// rear 감소
		return x;		// Point3 객체 타입 x가 반환, 디큐된 데이터
	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public Point3 peek() throws EmptyQueueException {
		if(isEmpty()) {		// 큐가 비어 있을 때 예외 발생
			throw new EmptyQueueException("Queue is Empty");
		}
		Point3 x = que[0];		// 맨 앞의 데이터를 x에 저장
		return x;		// 피크한 데이터 반환
	}

//--- 큐를 비움 ---//
	public void clear() {
		front = rear = 0;		// front와 rear를 0으로 초기화하여 큐를 비움
	}

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(Point3 x) {
		if(isEmpty()) {		// 큐가 비어 있을 때 -1 반환
			return -1;
		}
		for(int i = 0; i < que.length; i++) {		// 큐의 모든 요소에 대해 반복
			if(que[i].equals(x)) {		// 데이터가 일치할 때 해당 인덱스 반환
				return i;
			}
		}
		
		return -1;		// 일치하는 데이터가 없을 때 -1 반환
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;		// 큐의 크기 반환
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		return rear - front;		// rear와 front의 차이를 반환하여 데이터 개수 계산
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return rear <= front;		// rear가 front보다 작거나 같으면 비어 있는 상태이므로 true 반환
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return size() >= capacity;		// 데이터 개수가 큐의 크기와 같거나 크면 가득 찬 상태이므로 true 반환
	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		if(isEmpty()) {		// 큐가 비어 있을 때 예외 발생
			throw new EmptyQueueException("Queue is Empty"); 
		}
		else {
			for(int i = front; i < rear; i++) {		// front부터 rear 직전까지 반복, 데이터 출력
				System.out.print(que[i] + " ");
			}
			System.out.println();
		}
	}
}

public class 실습4_3_3객체선형큐_배열 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		objectQueue2 oq = new objectQueue2(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();		// Random 객체 생성
		int rndx = 0, rndy = 0;		// 랜덤 좌표 변수 초기화
		Point3 p = null;		// Point3 객체 변수 초기화
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프  (5)검색　(0)종료: ");
			int menu = stdIn.nextInt();		// 사용자로부터 메뉴 선택 입력
			switch (menu) {
			
			case 1: // 인큐
				rndx = random.nextInt(1, 20);		// 1부터 20 사이의 랜덤한 정수 생성하여 x 좌표에 할당
				rndy = random.nextInt(1, 20);
				System.out.print("입력데이터: (" + rndx + ", " + rndy + ")\n");		// 생성된 랜덤 좌표 출력
				p = new Point3(rndx, rndy);		// 랜덤 좌표로 Point3 객체 생성
				try {
					oq.enque(p);		// 큐에 데이터 인큐
				} catch (objectQueue2.OverflowQueueException e) {
					System.out.println("stack이 가득찼있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();		// 큐에서 데이터 디큐
					System.out.println("디큐한 데이터는 " + p + "입니다.");		// 디큐에 의해 반환된 객체의 참조 변수를 p로 변경하고, sysout() 출력할 때 Point3 클래스의 toString() 메소드가 호출
				} catch (objectQueue2.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();		// 큐에서 데이터 피크
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (objectQueue2.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 4: // 덤프
				try {
					oq.dump();		// 큐의 모든 데이터 출력			
				} catch (Queue4.EmptyQueueException e) {
					System.out.println("\n큐가 비어 있습니다.");
				}
				break;
				
			case 5: // 검색
				System.out.print("검색할 데이터: ");
				int x_key = stdIn.nextInt();		// 검색할 데이터의 x 좌표 입력
				int y_key = stdIn.nextInt();		// 검색할 데이터의 y 좌표 입력
				Point3 key = new Point3(x_key, y_key);		// 검색할 데이터로 Point3 객체 생성
				int idx = oq.indexOf(key);		// 데이터 검색하여 인덱스 저장
				if(idx != -1)
					System.out.println("데이터: " + key + ", " + "Index: " + idx);		// 검색한 데이터와 인덱스 출력
				else
					System.out.println("데이터: " + key + "를 찾을 수 없습니다.");		// 데이터를 찾을 수 없을 때 메시지 출력
				break;
				
			case 0:	// 종료
				stdIn.close();		// Scanner 객체 닫기
				return;		// 프로그램 종료
				
			default:
				System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
				break;
			}
		}
	}
}