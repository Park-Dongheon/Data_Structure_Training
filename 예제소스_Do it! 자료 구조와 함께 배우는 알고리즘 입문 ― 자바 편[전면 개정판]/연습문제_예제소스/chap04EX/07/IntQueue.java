// 연습4-5
// int형 고정 길이 큐(메서드 search를 추가)

public class IntQueue {
	private int[] que;			// 큐용 배열 
	private int capacity;		// 큐의 용량
	private int front;			// 맨앞 요소 커서
	private int rear;				// 맨끝 요소 커서
	private int num;				// 현재 데이터 개수

	//--- 실행 시 예외 : 큐가 비어 있음 ---//
	public class EmptyIntQueueException extends RuntimeException {
		public EmptyIntQueueException() { }
	}

	//--- 실행 시 예외 : 큐가 가득 참 ---//
	public class OverflowIntQueueException extends RuntimeException {
		public OverflowIntQueueException() { }
	}

	//--- 생성자 ---//
	public IntQueue(int maxlen) {
		num = front = rear = 0;
		capacity = maxlen;
		try {
			que = new int[capacity];				// 큐 본체용 배열을 생성
		} catch (OutOfMemoryError e) {		// 생성할 수 없음
			capacity = 0;
		}
	}

	//--- 큐에 데이터를  인큐 ---//
	public int enque(int x) throws OverflowIntQueueException {
		if (num >= capacity)
			throw new OverflowIntQueueException();			// 큐가 가득 참
		que[rear++] = x;
		num++;
		if (rear == capacity)
			rear = 0;
		return x;
	}

	//--- 큐에서 데이터를  디큐 ---//
	public int deque() throws EmptyIntQueueException {
		if (num <= 0)
			throw new EmptyIntQueueException();			// 큐가 비어 있음
		int x = que[front++];
		num--;
		if (front == capacity)
			front = 0;
		return x;
	}

	//--- 큐에서 데이터를 피크(맨앞 데이터를 들여다봄 ) ---//
	public int peek() throws EmptyIntQueueException {
		if (num <= 0)
			throw new EmptyIntQueueException();			// 큐가 비어 있음
		return que[front];
	}

	//--- 큐를 비웁니다 ---//
	public void clear() {
		num = front = rear = 0;
	}

	//--- 큐에서 x를 검색하여 인덱스(발견하지 못하면 -1)를 반환합니다 ---//
	public int indexOf(int x) {
		for (int i = 0; i < num; i++) {
			int idx = (i + front) % capacity;
			if (que[idx] == x)				// 검색 성공
				return idx;
		}
		return -1;							// 검색 실패
	}

	//--- 큐에서 x를 검색하여 맨앞에서 몇 번째인가(발견하지 못하면 0)를 반환합니다 ---//
	public int search(int x) {
		for (int i = 0; i < num; i++)
			if (que[(i + front) % capacity]  == x)	// 검색 성공
				return i + 1;
		return 0;								// 검색 실패
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
				System.out.print(que[(i + front) % capacity] + " ");
			System.out.println();
		}
	}
}
