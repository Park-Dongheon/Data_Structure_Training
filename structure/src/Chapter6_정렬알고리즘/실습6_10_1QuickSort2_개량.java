// 퀵 정렬(비재귀 버전)
package Chapter6_정렬알고리즘;

import java.util.EmptyStackException;

class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}

	@Override
	public String toString() {
		return "<" + ix + ", " + iy + ">";
	}

}

class Stack3<T> {
	private T[] stack;
	private int top;

	public Stack3(int capacity) {
		stack = (T[]) new Object[capacity];
		top = 0;
	}

	public void push(T item) {
		if (top >= stack.length - 1) {
			throw new StackOverflowError("Stack is Full");
		}
		stack[top++] = item;
	}

	public T pop() {
		if (top < 0) {
			throw new EmptyStackException();
		}
		return stack[--top];
	}

	public T peek() {
		if (top < 0) {
			throw new EmptyStackException();
		}
		return stack[top];
	}

	public int size() {
		return top;
	}

	public boolean isEmpty() {
		return top == 0;
	}

}

public class 실습6_10_1QuickSort2_개량 {

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void quickSort(int[] a, int left, int right) {

		Stack3<Point> st = new Stack3<>(10);
		Point pt = new Point(left, right);
		st.push(pt);
		while (!st.isEmpty()) {
			Point rt = st.pop();
			int pl = left = rt.getX();
			int pr = right = rt.getY();
			int mp = a[(pr + pl) / 2];

			do {
				while (a[pl] < mp)
					pl++;
				while (a[pr] > mp)
					pr--;
				if (pl <= pr)
					swap(a, pl++, pr--);

			} while (pl <= pr);
			System.out.println();
			System.out.println("left = " + left + ", " + "right = " + right);
			for (int i = left; i <= right; i++)
				System.out.print(a[i] + " ");
			System.out.println();
			if (left < pr) {
				Point pt2 = new Point(left, pr);
				st.push(pt2);

			}
			if (pl < right) {
				Point pt2 = new Point(pl, right);
				st.push(pt2);

			}

		}
	}

	static void showData(int[] d) {
		System.out.println();
		for (int i = 0; i < d.length; i++)
			System.out.print(d[i] + " ");
	}

	public static void main(String[] args) {
		int nx = 10;
		int[] x = new int[10];
		for (int ix = 0; ix < 10; ix++) {
			double d = Math.random();
			x[ix] = (int) (d * 20);
		}
		showData(x);

		quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬

		System.out.println("\n오름차순으로 정렬했습니다.");
		showData(x);

	}
}
