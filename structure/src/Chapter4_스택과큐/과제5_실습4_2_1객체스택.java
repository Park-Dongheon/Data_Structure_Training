package Chapter4_스택과큐;
/*
 * point(x,y) 좌표 객체를 스택에 저장
 * 좌표를 스택에 구현 소스 코드 - 5장에서 활용
 * 예외 처리 코드를 이해하는 것이 필요
 * 스택 구현을 배열이 아닌 list로 구현
 */


import java.util.ArrayList;
import java.util.List;
/*
* objectStack에 Point2 객체를 push, pop하는 코드를 구현 실습한다
*/

// 2차원 좌표를 나타내는 Point2 클래스
import java.util.Random;
import java.util.Scanner;
class Point2 {
	private int ix;		// x좌표
	private int iy;		// y좌표

	// 생성자: 좌표를 초기화하는 메서드
	public Point2(int x, int y) {
		ix = x;
		iy = y;
	}

	// 좌표를 문자열로 반환하는 메서드
	public String toString() {
		return "x좌표: " + ix + ", " + "y좌표: " + iy;
	}

	// 좌표 객체의 동등성 비교를 위한 equals 메서드 재정의
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Point2 p = (Point2) o;
		return ix == p.ix && iy == p.iy;
	}
}

//객체 스택을 구현한 objectStack 클래스
class objectStack{
	//--- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;
		public EmptyGenericStackException(String message) {
			super(message);
		}
	}

	//--- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException(String message) {
			super(message);
		}
	}

    private List<Point2> data;           // 스택용 배열: 순서가 있고 연속적 저장, 인덱스 == 배열과 같음, List Collection은 배열과 달리 요소를 삽입, 삭제가 쉽다.
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

//--- 생성자(constructor) ---//
	public objectStack(int capacity) {
		//구현
 		top = 0;		// 스택의 맨 위 인덱스 초기화
		this.capacity = capacity;		// 용량 설정
		try {
			data = new ArrayList<Point2> (capacity);		// ArrayList로 데이터 저장 구조 초기화
		} catch(OutOfMemoryError e) {		// 메모리 부족으로 ArrayList를 생성할 수 없을 때
			this.capacity = 0;		// 용량을 0으로 설정
		}
	}

//--- 스택에 x를 푸시 ---//
	public boolean push(Point2 x) throws OverflowGenericStackException {
		//구현
		if(top >= capacity)		// 스택이 가득 차면 예외 발생
			throw new OverflowGenericStackException("push: stack overflow");
		top++;		// top을 한 칸 위로 이동
		return data.add(x);		// 데이터를 스택에 추가하고 성공 여부 반환
	}

//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Point2 pop() throws EmptyGenericStackException  {
		//구현
		if(top <= 0)		// 스택이 비어있으면 예외 발생
			throw new EmptyGenericStackException("pop: stack empty");
		return data.remove(--top);		// top 위치의 데이터를 제거하고 반환
	}

//--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Point2 peek() throws EmptyGenericStackException  {
		//구현
		if(top <= 0)		// 스택이 비어있으면 예외 발생
			throw new EmptyGenericStackException("peek: stack current top data");
		return data.get(top - 1);		// top 위치의 데이터 반환
	}

//--- 스택을 비움 ---//
	public void clear() {
		top = 0;		// top을 초기화하여 스택을 비움
	}

//--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(Point2 x) {
		//구현
		for(int i = top - 1; i >= 0; i--)		// 스택을 역순으로 탐색
			if(data.get(i).equals(x)) 		// 데이터가 같은 경우 해당 인덱스 반환
				return i;
		return -1;		// 데이터가 없는 경우 -1 반환
	}

//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;		// 용량 반환
	}

//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;		// 현재 top 값이 저장된 데이터의 개수
	}

//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;		// top이 0 이하면 비어있음
	}

//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;		// top이 용량 이상이면 가득 참
	}

//--- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() throws EmptyGenericStackException {
		//구현
		if(top <= 0)		// 스택이 비어있으면 예외 발생
			throw new EmptyGenericStackException("Stack empty");
		else {
			System.out.println();
			for (Point2 d : data) {		// 데이터 순회하며 출력
				System.out.println(d);
			}	
		}
	}
}
public class 과제5_실습4_2_1객체스택 {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		objectStack s = new objectStack(8); // 최대 8 개를 push할 수 있는 stack
		Random random = new Random();		// 랜덤 객체 생성
		int rndx = 0, rndy = 0;		// 랜덤한 x, y 좌표를 생성할 변수 초기화
		Point2 p = null;		// Point2 객체 초기화
		while (true) {
			System.out.println(); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(0)종료: ");

			int menu = stdIn.nextInt();		// 사용자 입력 받기
			if (menu == 0)		// 입력이 0이면 반복 종료
				break;

			switch (menu) {		// 메뉴에 따른 처리
			case 1: // 푸시
				rndx = random.nextInt(1, 20);		// 1부터 20 사이의 랜덤한 정수 생성
				rndy = random.nextInt(1, 20);
				System.out.print("데이터: (" + rndx + ", " + rndy + ")" + "\n");
				p = new Point2(rndx,rndy);		// 생성된 좌표로 Point2 객체 생성
				try {
					s.push(p);		// 스택에 좌표 푸시
				} catch(objectStack.OverflowGenericStackException e) {		// 스택이 가득 찼을 때 예외 처리
					System.out.println("\nstack이 가득차있습니다.");
				}
				break;

			case 2: // 팝
				try {
					p = s.pop();		// 스택에서 데이터 팝
					System.out.println("\npop한 데이터는 " + p + "입니다.");
				} catch(objectStack.EmptyGenericStackException e) {		// 스택이 비어있을 때 예외 처리
					System.out.println("\nstack이 비어있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = s.peek();		// 스택의 맨 위 데이터 피크
					System.out.println("\npeek한 데이터는 " + p + "입니다.");		
				} catch (objectStack.EmptyGenericStackException e) {		// 스택이 비어있을 때 예외 처리
					System.out.println("\nstack이 비어있습니다.");
				}
				break;

			case 4: // 덤프: 스택 전체 출력
				try {
					s.dump();		// 스택 전체 출력					
				} catch (objectStack.EmptyGenericStackException e) {		// 스택이 비어있을 때 예외 처리
					System.out.println("\nstack이 비어있습니다.");
				}
				break;
			}
		}
	}
}