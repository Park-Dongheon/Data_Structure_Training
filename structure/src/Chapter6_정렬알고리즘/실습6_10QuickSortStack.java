package Chapter6_정렬알고리즘;

import java.util.EmptyStackException;

//퀵 정렬(비재귀 버전) - 교재 버젼으로 stack을 2개 사용하는 문제가 있다 

import java.util.Scanner;

class PointQ{
	private int left;
	private int right;
	
	public PointQ(int left, int right) {
		this.left = left;
		this.right = right;
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	@Override
	public String toString() {
		return "Point [left=" + left + ", right=" + right + "]";
	}
	
}


class IntStack {
	private PointQ[] stack;
	private int capacity;
	private int top;
	
	public IntStack(int capacity) {
		stack = new PointQ[capacity];
		this.capacity = capacity;
		top = 0;
	}

	public PointQ push(PointQ pq) {
		if (isFull()) {
			throw new StackOverflowError("Stack is Full");
		}
		stack[top] = pq;
		return stack[top++];
	}

	public PointQ pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack[--top];
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int size() {
		return top;
	}
	
	public boolean isFull() {
		return top >= capacity;
	}
	
	public boolean isEmpty() {
		return top <= 0;
	}
	
	
}

public class 실습6_10QuickSortStack {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void quickSort(int[] a, int left, int right) {
		IntStack stack = new IntStack(right - left + 1);
//		IntStack lstack = new IntStack(right - left + 1);
//		IntStack rstack = new IntStack(right - left + 1);

		PointQ pq = new PointQ(left, right);
		stack.push(pq);
//     lstack.push(left);
//     rstack.push(right);

		while (!stack.isEmpty()) {
			pq = stack.pop();
			int pl = left = pq.getLeft();
			int pr = right = pq.getRight();

//			int pl = left = lstack.pop(); // 왼쪽 커서
//			int pr = right = rstack.pop(); // 오른쪽 커서
			int x = a[(left + right) / 2]; // 피벗은 가운데 요소

			do {
				while (a[pl] < x)
					pl++;
				while (a[pr] > x)
					pr--;
				if (pl <= pr)
					swap(a, pl++, pr--);
			} while (pl <= pr);
			showData(a);
			System.out.println();
			if (left < pr) {
				System.out.println("left = " + left + ", pr = " + pr);
				PointQ pq2 = new PointQ(left, pr);
				stack.push(pq2);
//				lstack.push(left); // 왼쪽 그룹 범위의
//				rstack.push(pr); // 인덱스를 푸시
			}
			if (pl < right) {
				System.out.println("pl = " + pl + ", right = " + right);
				PointQ pq2 = new PointQ(pl, right);
				stack.push(pq2);
//				lstack.push(pl); // 오른쪽 그룹 범위의
//				rstack.push(right); // 인덱스를 푸시
			}
		}
	}

	static void showData(int[] d) {
		System.out.println();
		for (int i = 0; i < d.length; i++)
			System.out.print(d[i] + " ");
	}

	public static void main(String[] args) {
		try (Scanner stdIn = new Scanner(System.in)) {
			System.out.println("퀵 정렬");
			System.out.print("요솟수: ");
			int nx = stdIn.nextInt();
			int[] x = new int[nx];

			for (int i = 0; i < nx; i++) {
				double d = Math.random();
				x[i] = (int) (d * 20);
				// System.out.print("x[" + i + "]: ");
				// x[i] = stdIn.nextInt();
			}
			showData(x);

			quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬

			System.out.println("오름차순으로 정렬했습니다.");
			showData(x);
		}
	}
}
