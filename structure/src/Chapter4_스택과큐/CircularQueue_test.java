package Chapter4_스택과큐;

public class CircularQueue_test {
	private int[] queue;
	private int front;
	private int rear;
	private int size;
	private int capacity;
	
	public CircularQueue_test(int capacity) {
		this.capacity = capacity;
		this.queue = new int[capacity];
		this.front = -1;
		this.rear = -1;
		this.size = 0;
	}
	
	public boolean isFull() {
		return size == capacity;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void enqueue(int item) {
		if(isFull()) {
			throw new RuntimeException("Queue is full");
		}
		rear = (rear + 1) % capacity;
		queue[rear] = item;
		if(front == -1) {
			front = rear;
		}
		size++;
	}
	
	public int dequeue() {
		if(isEmpty()) {
			throw new RuntimeException("Queue is empty");
		}
		int item = queue[front];
		if(front == rear) {
			front = -1;
			rear = -1;
		}
		else {
			front = (front + 1) % capacity;
		}
		size--;
		return item;
	}
	
	public static void main(String[] args) {
		CircularQueue_test cq = new CircularQueue_test(4);
		
		cq.enqueue(5);
		cq.enqueue(6);
		cq.enqueue(1);
		cq.enqueue(2);
		cq.enqueue(3);
		
		System.out.println(cq.dequeue());	// 1
		System.out.println(cq.dequeue());	// 2
		
		cq.enqueue(8);
		cq.enqueue(9);
		
		while(!cq.isEmpty()) {
			System.out.println(cq.dequeue());
		}
		
	}
	
}
