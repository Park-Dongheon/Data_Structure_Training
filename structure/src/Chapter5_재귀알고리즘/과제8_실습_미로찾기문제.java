package Chapter5_재귀알고리즘;
/*
 * 미로 찾기 문제
 * 플라토의 미로 찾기 문제 설명 자료 학습
 * int input[12][15] 테이블에서 입구는 (0,0)이며 출구는 (11,14)임
 * 미로 테이블 (12,15)을 상하좌우 울타리를 친 maze[14][17]을 만들고 입구는 (1,1)이며 출구는(12,15)
 * non-recursive알고리즘 -> stack을 사용한 backtracking 구현
 * 정답: 지나간 경로만 그림 표시
 */

import java.util.ArrayList;
import java.util.List;

import Chapter5_재귀알고리즘.StackList.EmptyGenericStackException;

//23.2.16 수정
//23.2.24: 객체 배열 초기화, static 사용, 내부 클래스와 외부 클래스 사용 구분을 못하는 문제 => 선행 학습 필요

//좌표를 나타내는 Items 클래스 정의
class Items {
	int x;
	int y;
	int dir;
	
	public Items(int x, int y, int dir) {
		x = x;
		y = y;
		dir = dir;
	}
	
	public int getIX() {
		return x;
	}
	
	public int getIY() {
		return y;
	}
	
	public int getDIR()  {
		return dir;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + dir + ")"; // 좌표를 문자열로 반환
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass()) return false;
		Items i = (Items) o;
		return x == i.x && y == i.y && dir == i.dir;
	}
	
}

class StackList {
	// --- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyGenericStackException(String message) {
			super(message);
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException(String message) {
			super(message);
		}
	}

	private List<Items> data; // 스택용 배열
	// private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 생성자(constructor) ---//
	public StackList(int capacity) {
		top = 0; // 스택의 맨 위 top 위치 초기화
		this.capacity = capacity; // 스택의 용량을 설정
		try {
			// 스택용 배열 생성, 용량을 매개변수로 받는 data 리스트 객체 생성
			// Point 객체를 저장할 수 있는 ArrayList를 생성한다.
			data = new ArrayList<Items>(capacity);
		} catch (OutOfMemoryError e) {
			// 메모리 부족 예외 처리
			this.capacity = 0; // 스택 용량을 0으로 초기화
		}
	}

	// --- 스택에 x를 푸시 ---//
	public boolean push(Items temp) throws OverflowGenericStackException {
		if (top >= capacity) // top 의 값이 스택의 용량보다 크거나 같을 경우, Stack Overflow 예외를 던짐
			throw new OverflowGenericStackException("Push : Stack is Overflow");
		top++; // top 의 값이 스택의 용량보다 작을 경우, top 값 증가
		return data.add(temp); // data 리스트에 Point 타입의 매개변수 x 값을 추가하고, 성공 여부를 반환
	}

	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Items pop() throws EmptyGenericStackException {
		if (top <= 0) // top 의 값이 0보다 작거나 같을 경우, 스택이 비어 있으므로 예외를 던짐
			throw new EmptyGenericStackException("Pop : Stack is Empty");
		return data.remove(--top); // top 값을 감소시키고, data 리스트에서 top 위치에 있는 요소를 제거하여 반환
	}

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Items peek() throws EmptyGenericStackException {
		if (top <= 0) // top 의 값이 0보다 작거나 같을 경우, 스택이 비어 있으므로 예외를 던짐
			throw new EmptyGenericStackException("Peek : Stack is Empty");
		return data.get(top - 1); // data 리스트의 top - 1 위치의 요소를 반환 (스택의 맨 위 요소를 반환)
	}

	// --- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	// --- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(Items x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기(top) 쪽부터 선형 검색
			if (data.get(i).equals(x))
				return i; // 검색 성공, 해당 인덱스를 반환
		return -1; // 검색 실패, -1을 반환
	}

	// --- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	// --- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	// --- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

	// --- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() throws EmptyGenericStackException {
		if (top <= 0) // top의 값이 0보다 작거나 같을 경우, 스택이 비어 있으므로 예외를 던짐
			throw new EmptyGenericStackException("Stack:: dump - empty");
		else {
			for (int i = 0; i < top; i++) // 스택의 인덱스 바닥(0)부터 꼭대기(top) 까지 순회
				System.out.print(data.get(i) + " "); // 각 인덱스 위치의 data 리스트 요소를 출력
			System.out.println(); // 모든 요소를 출력한 후 줄바꿈
		}
	}
}


enum Directions {N, NE, E, SE, S, SW, W, NW};


class Offsets {
	int a;
	int b;

	public Offsets(int a, int b) {
		this.a = a;
		this.b = b;
	}
}

public class 과제8_실습_미로찾기문제 {

	static Offsets[] moves = new Offsets[8];// static을 선언하는 이유를 알아야 한다

	public static void path(int[][] maze, int[][] mark, int m, int p) throws EmptyGenericStackException {	// input[12][15] 미로행렬의 행, 열 m = 12, p = 15
		//출발점 (1,1), 이동 방향 dir = 2(2는 동쪽) 을 스택에 push
		//initialize stack to the maze entrance coordinates and direction east;
		mark[1][1] = 1;
		StackList st = new StackList(50);
		Items temp = new Items(0, 0, 0);//N :: 0
		temp.x = 1;
		temp.y = 1;
		temp.dir = 2;//E:: 2
		mark[temp.x][temp.y] = 2;//미로 찾기 궤적은 2로 표시, 지나간 자리
		st.push(temp);

		while (!st.isEmpty()) // stack not empty
		{
			//(i,j,dir) = coordinates and direction deleted from top of stack;
			//현재 위치 (i,j)에 대하여 mark[][]을 1로 설정
			Items tmp = st.pop(); // unstack
			int i = tmp.x;
			int j = tmp.y;
			int d = tmp.dir;
			mark[i][j] = 1;//backtracking 궤적은 1로 표시
			while (d < 8) // moves forward, 8가지 방향중에서 남은 방향에 대하여
			{
				// outFile << i << " " << j << " " << d << endl;
				System.out.println("(" + i + ", " + j + ", " + d + ")");
				// (g,h) = coordinates of next move, 현재 위치 (i,j)에 대하여 이동 방향 계산
				int g = i + moves[d].a;
				int h = j + moves[d].b;
				if ((g == m) && (h == p)) { // reached exit
					// output path
					System.out.println("Success in Finding the Path");
					//(i,j)와 (g,h)에 대하여 mark 표시
					mark[g][h] = 1;
					System.out.println(mark);
					return;
				}
				if ((maze[g][h] == 0) && (mark[g][h]) == 0) { // new position, maze[g][h]에서 이동이 가능하고, mark[g][h]가 표시된 적인 없는 경우
					// push the old temp to the stack, but the direction changes.
					// Because the neighbor in the direction of d has been checked.
					mark[g][h] = 1;
					temp = new Items(i, j, d+1);
					st.push(temp);
					i = g;
					j = h;
					d = 0;
				} else {
					// try next direction
					d++;
				}
			}
		}
		System.out.println("no path in maze ");	
	}

	public static void main(String[] args) throws EmptyGenericStackException {
		int[][] maze = new int[14][17];	// 울타리를 친 데이터:  input[] -> maze[]
		int[][] mark = new int[14][17];

		int input[][] = {	// 12 x 15
	            { 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
	            { 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
	            { 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
	            { 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
	            { 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
	            { 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
	            { 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
	            { 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
	            { 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
	            { 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
	            { 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
	            { 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 },
	        };
		for (int ia = 0; ia < 8; ia++)
			moves[ia] = new Offsets(0, 0);// 배열에 offsets 객체를 치환해야 한다, 한 좌표의 이동가능한 방향
		moves[0].a = -1;	moves[0].b = 0;
		moves[1].a = -1;	moves[1].b = 1;
		moves[2].a = 0;		moves[2].b = 1;
		moves[3].a = 1;		moves[3].b = 1;
		moves[4].a = 1;		moves[4].b = 0;
		moves[5].a = 1;		moves[5].b = -1;
		moves[6].a = 0;		moves[6].b = -1;
		moves[7].a = -1;	moves[7].b = -1;
//		Directions d;
//		d = Directions.N; 	
//		 d = d + 1;//java는 지원안됨
		
		for (int i = 1; i < 13; i++) {	// 1부터 12까지 (input의 인덱스가 0부터 시작하므로)
			for (int j = 1; j < 16; j++) {	// 1부터 15까지 (input의 인덱스가 0부터 시작하므로)
				// input[][]을 maze[][]로 복사
				maze[i][j] = input[i-1][j-1];
			}
		}
		
		// 울타리를 친 나머지 부분을 1로 초기화
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {
				if (i == 0 || i == 13 || j == 0 || j == 16) {
					maze[i][j] = 1;
				}
			}
		}
		
		System.out.println("maze[12,15]::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("mark::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(mark[i][j] + " ");
				maze[i][j] = 0;
			}
			System.out.println();
		}
		
		path(maze, mark, 12, 15);	// 출구 path() 메서드 호출
		
		System.out.println("maze[12,15]::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("mark::");	
		for (int i = 1; i <= 12; i++) {
			for (int j = 1; j <= 15; j++) {
				System.out.print(mark[i][j] + " ");
			}
			System.out.println();
		}

	}
}
