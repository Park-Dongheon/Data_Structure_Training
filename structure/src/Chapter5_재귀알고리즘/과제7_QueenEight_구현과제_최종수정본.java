package Chapter5_재귀알고리즘;

//해가 256개 확인 필요
import java.util.ArrayList;
import java.util.List;

import Chapter5_재귀알고리즘.Stack4.EmptyGenericStackException;

//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp
//N Queen problem / backtracking
//모든 해가 나오는 버젼 만들기 
/*
 * 체스판은 8 x 8 체스의 기물: king/가로세로대각선 1칸만 이동, queen/가로세로 대각선/같은 편의 기물을 넘을 수 없다,
 * Rook/가로,세로 이동/다른 기물을 넘을 수없다, bishop/대각선, knight/1-2칸 이동/다른 기물을 넘을 수 있다,
 * pawn/처음 이동은 2칸까지 가능, 그 후 한칸만 가능, 잡을 때는 대각선 가능 체스판 최대 배치 문제 : king/16,
 * Queen/8, rook/8, bishop/?, knight/? rook 2개/a, h, knight 2개/b, g, bishop
 * 2개/c, f, queen 1개/black queen 은 black 칸에, 폰 8개
 */
class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	public int getIX() {
		return ix;
	}

	public int getIY() {
		return iy;
	}

	@Override
	public String toString() {
		return "(" + ix + ", " + iy + ")"; // 좌표를 문자열로 반환
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null && getClass() != o.getClass())
			return false;
		Point p = (Point) o;
		return ix == p.ix && iy == p.iy;
	}
}

class Stack4 {
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

	private List<Point> data; // 스택용 배열
	// private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 생성자(constructor) ---//
	public Stack4(int capacity) {
		top = 0; // 스택의 맨 위 top 위치 초기화
		this.capacity = capacity; // 스택의 용량을 설정
		try {
			// 스택용 배열 생성, 용량을 매개변수로 받는 data 리스트 객체 생성
			// Point 객체를 저장할 수 있는 ArrayList를 생성한다.
			data = new ArrayList<Point>(capacity);
		} catch (OutOfMemoryError e) {
			// 메모리 부족 예외 처리
			this.capacity = 0; // 스택 용량을 0으로 초기화
		}
	}

	// --- 스택에 x를 푸시 ---//
	public boolean push(Point x) throws OverflowGenericStackException {
		if (top >= capacity) // top 의 값이 스택의 용량보다 크거나 같을 경우, Stack Overflow 예외를 던짐
			throw new OverflowGenericStackException("Push : Stack is Overflow");
		top++; // top 의 값이 스택의 용량보다 작을 경우, top 값 증가
		return data.add(x); // data 리스트에 Point 타입의 매개변수 x 값을 추가하고, 성공 여부를 반환
	}

	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Point pop() throws EmptyGenericStackException {
		if (top <= 0) // top 의 값이 0보다 작거나 같을 경우, 스택이 비어 있으므로 예외를 던짐
			throw new EmptyGenericStackException("Pop : Stack is Empty");
		return data.remove(--top); // top 값을 감소시키고, data 리스트에서 top 위치에 있는 요소를 제거하여 반환
	}

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Point peek() throws EmptyGenericStackException {
		if (top <= 0) // top 의 값이 0보다 작거나 같을 경우, 스택이 비어 있으므로 예외를 던짐
			throw new EmptyGenericStackException("Peek : Stack is Empty");
		return data.get(top - 1); // data 리스트의 top - 1 위치의 요소를 반환 (스택의 맨 위 요소를 반환)
	}

	// --- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	// --- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(Point x) {
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

public class 과제7_QueenEight_구현과제_최종수정본 {

	public static void EightQueen(int[][] d) throws EmptyGenericStackException {
		int count = 0;// 퀸 배치 갯수
		int numberSolutions = 0;
		int ix = 0, iy = 0;// 행 ix, 열 iy
		Stack4 st = new Stack4(100); // 100개를 저장할 수 있는 스택을 만들고
		Point p = new Point(ix, iy);// 현 위치를 객체로 만들고
		d[ix][iy] = 1;// 현 위치 d[0][0]에 queen을 넣었다는 표시를 하고
		count++;
		ix++; iy=0;
		st.push(p);// 스택에 현 위치 객체를 push
		while (true) {
			iy = nextMove(d, ix, iy); // 현재 ix = 1, iy = 0;
			if (iy < 0) {
				p = st.pop(); // pop() 처리
				iy = p.getIY();
				ix = p.getIX();
				d[ix][iy] = 0;
				count--;
				// 물리기는 pop한 것이다 > 그 다음칸에 배치할 수 있는지를 체크한다.
				// ix를 증가시켜서 queen을 놓을 수 있는지 체크 => nextMove() 호출하여 처리
			} else {
				p = new Point(iy, ix);
				st.push(p);
				count++;
				iy++;
				continue;
			}
		}
		
	}

	public static boolean checkRow(int[][] d, int crow) { // 배열 d 에서 행 crow 에 퀸을 배치할 수 있는지 조사
		for (int j = 0; j < d.length; j++) {	// 행 crow 의 모든 열을 검사
			if (d[crow][j] == 1)	// 열에 퀸(1)이 이미 존재할 경우, 배치할 수 없음
				return false;
		}
		return true;	// 행 crow 에 퀸을 배치 가능
	}

	public static boolean checkCol(int[][] d, int ccol) {// 배열 d에서 열 ccol에 퀸을 배치할 수 있는지 조사
		for (int i = 0; i < d[0].length; i++) {		// 열 ccol 의 모든 행을 검사
			if (d[i][ccol - 1] == 1)	// 행에 퀸(1)이 이미 존재할 경우, 배치할 수 없음
				return false;
		}
		return true;	// 열 ccol 에 퀸을 배치 가능
	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남서, 북동 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSW(int[][] d, int cx, int cy) {	// x++, y-- or x--, y++ where 0<= x, y <= 7
		int x = cx, y = cy;
		while (x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
			if(d[x][y] == 1)
				return false;
			x++;	y--;
		}
		
		x = cx; y = cy;
		while (x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
			if(d[x][y] == 1)
				return false;
			x--;	y++;
		}
		return true;
	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남동, 북서 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSE(int[][] d, int cx, int cy) {	// x++, y++ or x--, y--
		int x = cx, y = cy;
		while(x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
			if(d[x][y] == 1)
				return false;
			x++;	y++;
		}
		
		x = cx; y = cy;
		while(x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
			if(d[x][y] == 1)
				return false;
			x--;	y--;
		}
		return true;
	}

	// 배열 d에서 (x,y)에 퀸을 배치할 수 있는지 조사
	public static boolean checkMove(int[][] d, int x, int y) {// (x,y)로 이동 가능한지를 check
		int ix = x, iy = y;
		
		for(int i = 0; i < d.length; i++) {
			if(d[ix][i] == 1 || d[i][iy] == 1 )
				return false;
		}
		
		int diag_ix = x + 1, diag_iy = y + 1, diag_dy = y - 1; 
		for(int i = 0; i < d.length; i++) {
			if(d[diag_ix][diag_iy] == 1 && d[diag_ix][diag_dy] == 1) {
				return false;
			}
		}
		
		int diag_dx = x - 1;
		diag_iy = y + 1; diag_dy = y - 1; 
		for(int i = 0; i < d.length; i++) {
			if(d[diag_dx][diag_iy] == 1 && d[diag_dx][diag_dy] == 1) {
				return false;
			}
		}
		
		return true;
	}

	// 배열 d에서 현재 위치(row,col)에 대하여, 다음에 이동할 위치 nextCol을 반환, 이동이 가능하지 않으면 -1를 리턴
	public static int nextMove(int[][] d, int row, int col) {// 현재 row, col에 대하여 이동할 col을 return
		if(checkRow(d, row) && checkCol(d, col) && checkDiagSW(d, row, col) && checkDiagSE(d, row, col)) {
			return col;
		}
		return -1;
	}

	static void showQueens(int[][] data) {// 배열 출력
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws EmptyGenericStackException {
		int row = 8, col = 8;
		int[][] data = new int[8][8]; // 8 x 8 행열 배열 선언 초기화

		for (int i = 0; i < data.length; i++) // 행열의 요소를 0으로 초기화
			for (int j = 0; j < data[0].length; j++)
				data[i][j] = 0;

		EightQueen(data); // EightQueen() 메서드 호출
		showQueens(data);

	}
}