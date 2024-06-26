package Chapter4_스택과큐;
/*
 * 실습 6번: 원형 큐로서 큐에 Point 객체를 배열로 저장
 * 실습4_3_2 정수 원형 큐 배열을 객체 버젼으로 구현하는 것임
 */

import java.util.Random;
import java.util.Scanner;

//Point5 클래스 선언
class Point5 {
	private int ix; // x좌표 변수
	private int iy; // y좌표 변수

	// 생성자
	public Point5(int rndx, int rndy) {
		this.ix = rndx;		// x 좌표 초기화
		this.iy = rndy;		// y 좌표 초기화
	}
	
	// 객체의 문자열 표현을 반환하는 메서드
    @Override
    public String toString() {
        return "(" + ix + ", " + iy + ")";	// 좌표를 문자열로 반환
    }
}

class CircularQueue {
	static int QUEUE_SIZE = 0;	// 큐의 최대 크기
	Point5[] que;// 배열로 객체원형 큐 구현
	int front, rear;
	static boolean isEmptyTag;	// 큐가 비어 있는지 여부를 나타내는 태그
	
	// --- 실행시 예외: 큐가 비어있음 ---//
    public class EmptyQueueException extends RuntimeException {
        public EmptyQueueException(String message) {
            super(message); // 상위 클래스의 생성자 호출
        }
    }
    
	// --- 실행시 예외: 큐가 가득 찼음 ---//
    public class OverflowQueueException extends RuntimeException {
        public OverflowQueueException(String message) {
            super(message); // 상위 클래스의 생성자 호출
        }
    }
    
	public CircularQueue(int count) {
		QUEUE_SIZE = count;
        front = 0; // 초기값 0으로 설정
        rear = 0; // 초기값 0으로 설정
        que = new Point5[count + 1]; // 용량이 count인 큐 생성
        isEmptyTag = true; // 큐가 비어 있는 상태로 초기화
	}

	// 큐에 데이터를 인큐하는 메서드
	void push(Point5 it) throws OverflowQueueException {
		if (isFull()) {
			throw new OverflowQueueException("push: circular queue overflow");
		}
		que[rear] = it;	// rear가 가리키는 위치에 데이터 저장
		rear = (rear + 1) % que.length;	// rear 증가
		isEmptyTag = false;	// 데이터가 추가되었으므로 큐는 더 이상 비어 있지 않음
	}

	// 큐에 데이터를 인큐하는 메서드
	Point5 pop() throws EmptyQueueException {
		if (isEmpty()) {		// 큐가 가득 찼을 때 예외 발생
			throw new EmptyQueueException("pop: circular queue overflow");
		}
        Point5 it = que[front]; // front가 가리키는 위치의 데이터 저장
        front = (front + 1) % que.length; // front 증가 (원형 큐이므로 모듈로 연산)
        if (front == rear) { // 디큐 후 front와 rear가 같으면 큐가 비어 있음
            isEmptyTag = true;
        }
        return it; // 저장된 데이터 반환
	}
	
	// 큐를 초기화하는 메서드
	void clear() throws EmptyQueueException {
        front = 0; // front를 초기값으로 설정
        rear = 0; // rear를 초기값으로 설정
        isEmptyTag = true; // 큐가 비어 있는 상태로 설정
	}

	// --- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return QUEUE_SIZE;	// 큐의 최대 크기 반환
	}

	// --- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {// front, rear를 사용하여 갯수를 size로 계산
		int queueSize = -1;	// 큐의 크기를 저장할 변수
        if (isEmptyTag) {	// 큐가 비어있으면
            queueSize = 0;	// 크기는 0
        } else {
            queueSize = (rear - front + que.length) % que.length;	// 큐의 크기 계산
        }
		return queueSize;	// 큐의 크기 반환
	}

	// --- 원형 큐가 비어있는가? --- 수정 필요//
	public boolean isEmpty() {
		return isEmptyTag;	// 큐가 비어 있는지 여부 반환
	}

	// --- 원형 큐가 가득 찼는가? --- 수정 필요//
	public boolean isFull() {
		return !isEmptyTag && front == (rear + 1) % que.length;	// 큐가 가득 찼는지 여부 반환
	}

	// 큐의 모든 데이터를 출력하는 메서드
	public void dump() throws EmptyQueueException {
        if (isEmpty()) // 큐가 비어있는지 확인
            throw new EmptyQueueException("Dump: Queue Empty"); // 큐가 비어있다면 예외 발생
        else {
        	int i = front;	// front부터 시작
        	while (i != rear) {	// rear까지 순회
        		System.out.print(que[i] + " ");	// 데이터 출력
        		i = (i + 1) % que.length;	// 인덱스 증가
            }
            System.out.println(); // 줄 바꿈
        }
	}

	 // 큐의 Peek 메서드 front 데이터를 확인
	public Point5 peek() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Peek: Queue Empty"); // 큐가 비어있음
		return que[front];	// front 데이터 반환
	}
}

public class 과제6_실습4_5원형큐객체배열2 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in); // Scanner 객체 생성
		CircularQueue oq = new CircularQueue(4); // 최대 4개를 인큐할 수 있는 큐
		Random random = new Random(); // Random 객체 생성
		int rndx = 0, rndy = 0; // 랜덤 좌표 변수 초기화
		Point5 p = null; // Point5 객체 변수 초기화
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5) clear  (0)종료: ");
			int menu = stdIn.nextInt();
			if (menu == 0)	// 0을 입력하면 종료
				break;
			switch (menu) {	// 메뉴에 따라 실행

			case 1: // 인큐
				rndx = random.nextInt(1, 20); // 1부터 20 사이의 랜덤한 정수 생성하여 x 좌표에 할당
				rndy = random.nextInt(1, 20); // 1부터 20 사이의 랜덤한 정수 생성하여 y 좌표에 할당
				System.out.print("입력데이터: (" + rndx + ", " + rndy + ") "); // 생성된 랜덤 좌표 출력
				p = new Point5(rndx, rndy); // 랜덤 좌표로 Point5 객체 생성
				try {
					oq.push(p); // 큐에 데이터 인큐
					System.out.println("push: size() = " + oq.size());
				} catch (CircularQueue.OverflowQueueException e) {
					System.out.println("queue이 full입니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 2: // 디큐
				try {
					p = oq.pop();
					System.out.println("pop: size() = " + oq.size());
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
				
			case 4: // 덤프
                try {
                    oq.dump(); // 큐의 모든 데이터 출력
                } catch (CircularQueue.EmptyQueueException e) {
                    System.out.println("\n큐가 비어 있습니다."); // 언더플로우 예외 발생 시 메시지 출력
                }
                break;
                
			case 5: // clear
                oq.clear();
                System.out.println("큐를 초기화했습니다.");
				break;
				
            case 0: // 종료
                stdIn.close(); // Scanner 객체 닫기
                return; // 프로그램 종료
                
            default:
                System.out.println("잘못된 입력입니다. 다시 시도해주세요."); // 잘못된 입력 시 메시지 출력
                break;
			}
		}
	}
}
