// 연습4-6
// 제네릭 용 큐

public class Queue<E> {

	//--- 실행 시 예외 : 큐가 비어 있음 ---//
	public static class EmptyGqueueException extends RuntimeException {
		public EmptyGqueueException() { }
	}

	//--- 실행 시 예외 : 큐가 가득 참 ---//
	public static class OverflowGqueueException extends RuntimeException {
		public OverflowGqueueException() { }
	}

	private E[] que;			// 큐의 본체
	private int capacity;			// 큐의 용량
	private int num;			// 현재 데이터 개수
	private int front;		// 맨앞 요소 커서
	private int rear;			// 맨끝 요소 커서

	//--- 생성자 ---//
	public Queue(int maxlen) {
		num = front = rear = 0;
		capacity = maxlen;
		try {
			que = (E [])new Object[capacity];		// 큐 본체용 배열을 생성
		} catch (OutOfMemoryError e) {				// 생성할 수 없음
			capacity = 0;
		}
	}

	//--- 큐에 데이터를  인큐 ---//
	public E enque(E x) throws OverflowGqueueException {
		if (num >= capacity)
			throw new OverflowGqueueException();		// 큐가 가득 참
		que[rear++] = x;
		num++;
		if (rear == capacity)
			rear = 0;
		return x;
	}

	//--- 큐에서 데이터를  디큐 ---//
	public E deque() throws EmptyGqueueException {
		if (num <= 0)
			throw new EmptyGqueueException();				// 큐가 비어 있음
		E x = que[front++];
		num--;
		if (front == capacity)
			front = 0;
		return x;
	}

	//--- 큐에서 데이터를 피크(맨앞 데이터를 들여다봄 ) ---*/
	public E peek() throws EmptyGqueueException {
		if (num <= 0)
			throw new EmptyGqueueException();				// 큐가 비어 있음
		return que[front];
	}

	//--- 큐에서 x를 검색하여 인덱스(발견하지 못하면 -1)를 반환합니다 ---//
	public int indexOf(E x) {
		for (int i = 0; i < num; i++)
			if (que[(i + front) % capacity].equals(x))	// 검색 성공
				return i + front;
		return -1;									// 검색 실패
	}

	//--- 큐에서 x를 검색하여 맨앞에서 몇 번째인가(발견하지 못하면 -1)을 반환합니다 ---//
	public int search(E x) {
		for (int i = 0; i < num; i++)
			if (que[(i + front) % capacity].equals(x))	// 검색 성공
				return i + 1;
		return -1;									// 검색 실패
	}

	//--- 큐를 비웁니다 ---//
	public void clear() {
		num = front = rear = 0;
	}

	//--- 큐의 용량을 반환합니다 ---//
	public int getCapacity() {
		return capacity;
	}

	//--- 큐에 쌓여있는 데이터수를 반환합니다 ---//
	public int size() {
		return num;
	}

	//--- 큐가 비어 있는가? ---//
	public boolean isEmpty() {
		return num <= 0;
	}

	//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return num >= capacity;
	}

	//--- 큐 안의 모든 데이터를 맨앞 → 맨끝의 순서로 출력 ---//
	public void dump() {
		if (num <= 0)
			System.out.println("큐가 비어 있습니다.");
		else {
			for (int i = 0; i < num; i++)
				System.out.print(que[(i + front) % capacity].toString() + " ");
			System.out.println();
		}
	}
}
