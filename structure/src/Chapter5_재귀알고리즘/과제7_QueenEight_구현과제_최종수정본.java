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

//좌표를 나타내는 Point 클래스 정의
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
		if (o == null || getClass() != o.getClass()) return false;
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

	public static void EightQueen(int[][] d) throws Stack4.EmptyGenericStackException {
		int count = 0;// 퀸 배치 갯수
		int numberSolutions = 0; // 가능한 해의 수
		int ix = 0, iy = 0;// 현재 행 ix, 열 iy
		Stack4 st = new Stack4(100); // 100개를 저장할 수 있는 스택 생성
		Point p = new Point(ix, iy);// 현 위치를 객체로 생성
		d[ix][iy] = 1;// 현 위치 d[0][0]에 queen 배치 표시
		count++; // 퀸 배치 갯수 증가
		ix++; // 다음 행으로 이동
		st.push(p);// 스택에 현 위치 객체를 push
				
	    while (true) {	// 열을 이동하면서 퀸을 배치하는 작업을 반복
	        while (ix < 8) {	// 8행 이전에만 실행
	            iy = nextMove(d, ix, iy); // 현재 ix = 1, iy = 0, 다음 이동할 열 결정
	            if (iy < 0) {	// iy가 음수인 경우(다음 이동할 열이 없는 경우)
	                p = st.pop(); // 스택에서 위치 꺼내기
	                ix = p.getIX();
	                iy = p.getIY();
	                d[ix][iy] = 0; // 해당 위치에 있는 퀸 제거
	                count--;
	                iy++; // 다음 열로 이동
	                // 물리기는 pop한 것이다 > 그 다음칸에 배치할 수 있는지를 체크한다.
	                // ix를 증가시켜서 queen을 놓을 수 있는지 체크 => nextMove() 호출하여 처리	
	            } else {	// iy가 음수가 아닌 경우(다음 이동할 열이 있는 경우)
	                p = new Point(ix, iy);	// 퀸을 해당 위치에 배치
	                st.push(p);	// 스택에 추가
	                d[ix][iy] = 1; // 퀸 배치 표시
	                count++;
	                ix++;	// 다음 행으로 이동
	                iy = 0; // 첫 번째 열부터 시작
	            }
	        }
	        numberSolutions++;	// 해의 수를 증가
	        showQueens(d);	// 현재 퀸의 배치를 출력
	        if (st.isEmpty()) {	// 스택이 비어있는지 확인, 비어잇으면 모든 가능한 해를 찾은 것
	            System.out.println("Number of solutions: " + numberSolutions);	// 92
	            return;
	        }
	        p = st.pop();	// 스택에서 위치를 꺼냄
	        ix = p.getIX();
	        iy = p.getIY();
	        d[ix][iy] = 0;	// 해당 위치에 있는 퀸 제거
	        count--;
	        iy++; // 다음 열로 이동
	    }
	}

	// 특정 행에 퀸을 배치할 수 있는지 검사하는 메서드
	public static boolean checkRow(int[][] d, int crow) { // 배열 d 에서 행 crow 에 퀸을 배치할 수 있는지 조사
		for (int j = 0; j < d.length; j++) {	// 행 crow 의 모든 열을 검사
			if (d[crow][j] == 1)	// 열에 퀸(1)이 이미 존재할 경우, 배치할 수 없음
				return false;
		}
		return true;	// 행 crow 에 퀸을 배치 가능
	}

	// 특정 열에 퀸을 배치할 수 있는지 검사하는 메서드
	public static boolean checkCol(int[][] d, int ccol) {// 배열 d에서 열 ccol에 퀸을 배치할 수 있는지 조사
		for (int i = 0; i < d[0].length; i++) {		// 열 ccol 의 모든 행을 검사
			if (d[i][ccol] == 1)	// 행에 퀸(1)이 이미 존재할 경우, 배치할 수 없음
				return false;
		}
		return true;	// 열 ccol 에 퀸을 배치 가능
	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남서, 북동 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSW(int[][] d, int cx, int cy) {	// x++, y-- or x--, y++ where 0<= x, y <= 7
		int x = cx, y = cy;
		// 남서 방향 대각선 검사
		while (x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
			if(d[x][y] == 1)
				return false; // 이미 퀸이 배치된 경우
			x++; // 행 증가
			y--; // 열 감소
		}
		
		x = cx; y = cy;
		 // 북동 방향 대각선 검사
		while (x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
			if(d[x][y] == 1)
				return false; // 이미 퀸이 배치된 경우
			x--; // 행 감소
			y++; // 열 증가
		}
		return true; // 배치 가능
	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남동, 북서 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSE(int[][] d, int cx, int cy) {	// x++, y++ or x--, y--
		int x = cx, y = cy;
		// 남동 방향 대각선 검사
		while(x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
			if(d[x][y] == 1)
				return false; // 이미 퀸이 배치된 경우
			x++; // 행 증가
			y++; // 열 증가
		}
		
		x = cx; y = cy;
		// 북서 방향 대각선 검사
		while(x >= 0 && x < d.length && y >= 0 && y < d[0].length) {
			if(d[x][y] == 1)
				return false; // 이미 퀸이 배치된 경우
			x--; // 행 감소
			y--; // 열 감소
		}
		return true; // 배치 가능
	}

	// 배열 d에서 (x,y)에 퀸을 배치할 수 있는지 조사
	public static boolean checkMove(int[][] d, int x, int y) {// (x,y)로 이동 가능한지를 check
		// 모든 조건을 만족해야 배치 가능
		return checkRow(d, x) && checkCol(d, y) && checkDiagSW(d, x, y) && checkDiagSE(d, x, y);
	}

	// 배열 d에서 현재 위치(row,col)에 대하여, 다음에 이동할 위치 nextCol을 반환, 이동이 가능하지 않으면 -1를 리턴
	public static int nextMove(int[][] d, int row, int col) {// 현재 row, col에 대하여 이동할 col을 return
		for(int j = col; j < d.length; j++) {	// 현재 열부터 끝까지 검사
			if(checkMove(d, row, j)) {	// 이동 가능한 열인 경우
				return j; // 해당 열 반환			
			}
		}
		return -1; // 이동할 열이 없음
	}

	// 퀸 배치 결과를 출력하는 메서드
	static void showQueens(int[][] data) {// 배열 출력
	    for (int i = 0; i < data.length; i++) {
	        for (int j = 0; j < data[0].length; j++) {
	            if (data[i][j] == 1) {
	                System.out.print("Q "); // Queen이 있는 위치에는 'Q' 출력
	            } else {
	                System.out.print(data[i][j] + " "); // Queen이 없는 위치에는 값 그대로 출력
	            }
	        }
	        System.out.println();
	    }
	    System.out.println();
	}

	public static void main(String[] args) throws Stack4.EmptyGenericStackException {
		int row = 8, col = 8;	// 행과 열의 크기 설정
		int[][] data = new int[row][col]; // 8 x 8 행열 배열 선언 초기화

		for (int i = 0; i < data.length; i++) // 행열의 요소를 0으로 초기화
			for (int j = 0; j < data[0].length; j++)
				data[i][j] = 0;	// 0으로 초기화

		EightQueen(data); // EightQueen() 메서드 호출
	}
}