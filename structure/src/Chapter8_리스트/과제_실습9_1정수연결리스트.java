package Chapter8_리스트;
//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class Node1 {
	int data;
	Node1 link;

	public Node1(int element) {
		data = element;
		link = null;
	}
}

class LinkedList1 {
	Node1 first;

	public LinkedList1() {
		first = null;
	}
	
	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node1 p = first;
		int num = 0;
		
		while (p != null) {
			System.out.print(p.data + " -> ");
			p = p.link;
			if (p == null)  {
				System.out.println("리스트 끝\n");
			}
		}
		
	}

	public boolean Delete(int element) // 전달된 element 값이 존재 하면 삭제하고 true로 리턴
	{
		Node1 q, current = first;
		// current는 리스트의 첫 번째 노드를 가리킴
		q = current;
		
		// 현재 정의된 first 리스트가 존재하지 않을 경우 
		if (current == null) {
			System.out.println("리스트가 존재하지 않습니다.");
			return false;
		}
		
		// 현재 정의된 first 리스트의 첫번째 데이터가 삭제하고자 하는 element 인 경우
		if (current.data == element) {
			// 첫 번째 노드를 삭제하고 리스트의 링크를 다음 노드로 변경,  true 를 반환 
			first = current.link;
			return true;
		}
		
		// q 링크의 데이터, 즉 다음 리스트가 null 이 아니고, q 링크의 데이터, 즉 다음 리스트의 데이터가 삭제하고자 하는 element 가 아닌 경우 
		while (q.link != null && q.link.data != element) {
			// 현재 리스트 q 에 q 링크를 저장
			q = q.link;
		}
		
		// q 링크의 데이터, 즉 다음 리스트가 null 이 아니고, q 링크의 데이터, 즉 다음 리스트의 데이터가 삭제하고자 하는 element 일 경우
		while (q.link != null && q.link.data == element) {
			// q 가 가리키는 link 에 다음 리스트의 링크 데이터가 저장되고 true 를 반환
			q.link = q.link.link;
			return true;
		}
		
		return false;
	}


	public void Add(int element) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{
		// ex) L -> 5 -> 10 -> 20 // L -> 5 -> 10 -> 15 -> 20 // element = 15 //
		// if(element < p.data)
		Node1 newNode = new Node1(element);

		if (first == null) // insert into empty list
		{
			first = newNode; // linked list 객체 -> newNode = data.link

			return;
		}

		Node1 p = first, q = null; // p라는 변수를 도입해서 각 노드를 따라간다.
		while (p != null) { // p 리스트가 null 이 아니라면 반복, 즉 리스트가 존재하지 않을 경우까지 반복
			if (element > p.data) { // 입력값이 p 리스트의 data 보다 클 경우
				q = p; // q가 p를 따라다닌다
				p = p.link; // p와 q는 first 리스트
			} else { // q(10) -> 15 -> p(20)
				if (q == null) { // 1번처리, 처음 위치에 삽입할 경우, q는 현재 null 이고 p는 첫번째 리스트 요소
					newNode.link = p;
					first = newNode;
					return;
				}
				q.link = newNode; // 2번처리, 리스트 중간에 삽입할 경우, q 리스트 -> newNode -> p 리스트
				newNode.link = p;
				return;
			}

		}

		if (q != null) { // 3번 처리, 리스트 끝에 삽입할 경우, q는 현재 리스트 마지막요소 앞 리스트 이고 p는 현재 null
			q.link = newNode;
		}

	}

	public boolean Search(int data) { // 전달된 data 값을 찾아 존재하면 true로 리턴, 없으면 false로 리턴
		Node1 ptr = first;
		while (ptr != null) {
			if (data == ptr.data) {
				return true;
			}

			ptr = ptr.link;
		}
		return false;
	}

	// 두 LinkedList를 오름차순으로 병합하여 첫 번째 리스트에 in-place 하는 메서드
	void Merge(LinkedList1 list) {
		/*
		 * 연결리스트 a,b에 대하여 a = a + b merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지
		 * 않고 합병하는 알고리즘 구현 난이도 등급: 최상 a = (3, 5, 7), b = (2,4,8,9)이면 a = (2,3,4,5,8,9)가
		 * 되도록 구현하는 코드
		 */

		// a -> 3 - 5 - 7 / b -> 2 - 4 -8 - 9 merge(a, b) = a -> 2 - 3 - 4 - 5 - 6 - 7 -
		// 8 - 9
		

		// 첫 번째 리스트의 현재 노드를 가리킴
		Node1 a = first;	
		// 두 번째 리스트의 현재 노드를 가리킴
		Node1 b = list.first;
		// a1 : 첫 번째 리스트에서 현재 노드의 이전 노드를 가리킴
		// b1 : 두 번째 리스트에서 현재 노드의 이전 노드를 가리킴 (병합 과정에서 사용)
		Node1 a1 = null, b1 = null;
		
		// 첫 번째 리스트가 비어 있는 경우
		if (a == null) {
			first = b;				// 두 번째 리스트의 첫 노드를 첫 번째 리스트의 첫 노드로 설정
			return;
		}
		
		// 두 번째 리스트가 비어 있는 경우
		if (b == null) {
			return;					// 아무 작업도 하지 않음
		}
		
		// 두 리스트의 첫 번째 노드를 비교하여 첫 번째 리스트의 first를 결정
		if (a.data > b.data) {
			a1 = a;					// 첫 번째 리스트의 현재 노드를 a1에 저장
			a = b;					// 두 번재 리스트의 현재 노드를 첫 번째 리스트의 현재 노드로 설정
			b = a1;					// 첫 번째 리스트의 이전 노드 a1을 두 번째 리스트의 현재 노드로 설정
			first = a;				// 첫 번째 리스트의 첫 노드를 현재 노드로 설정
		}
		
		// 첫 번째 리스트와 두 번째 리스트가 비어있지 않는 경우 반복
		while (a != null && b != null) {
			// 첫 번째 리스트의 데이터가 두 번째 리스트의 데이터보다 작거나 같은 경우
			if (a.data <= b.data) {
				a1 = a;				// 첫 번째 리스트의 현재 노드를 a1에 저장
				a = a.link;			// a를 다음 노드로 이동
			}
			// 두 번째 리스트의 데이터가 첫 번째 리스트의 데이터보다 작을 경우
			else {
				a1.link = b;		// 첫 번째 리스트의 이전 노드의 다음 노드를 두 번째 리스트의 현재 노드로 설정
				b1 = b.link;		// 두 번째 리스트의 다음 노드를 b1에 저장
				b.link = a;			// 두 번째 리스트의 현재 노드의 다음 노드를 첫 번째 리스트의 현재 노드로 설정
				b = b1;				// b를 다음 노드로 이동
				a1 = a1.link;		// a1을 다음 노드로 이동
			}
		}
		
		// 첫 번째 리스트가 null 인 경우 남아있는 두 번째 리스트 현재 노드를 병합 리스트에 추가
		if (a == null) {
			// a1의 다음 노드를 두 번째 리스트로 설정
			a1.link = b;
		}
		
		
	}

}

public class 과제_실습9_1정수연결리스트 {
	
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("합병"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values()) // values() 메서드, {Add() : 0, Delete() : 1...}
				if (m.ordinal() == idx) // ordinal() 메서드 "메뉴명" 반환
					return m;
			return null;
		}

		// "Add" 상수가 정의될 때 Menu("삽입") 생성자가 호출되어 message 필드가 "삽입"으로 초기화
		// 생성자는 각 상수가 정의될 때 호출되며, 해당 상수의 message 필드를 초기화하는 역할
		// enum 상수가 언제 정의되는가를 찾아 보아야 한다
		Menu(String string) { // 생성자(constructor)가 언제 호출되는지 파악하는 것이 필요하다
			message = string;
			System.out.println("\nMenu 생성자 호출:: " + string);
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage()); // ex) (0) Add, (1) Delete
				// n%3은 3으로 나누어 나머지를 계산한다
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())// 메뉴 출력시에 다음행에 출력하라는 의미
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();// 메뉴 선택 번호로 입력된 값이 key이다
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());// 입력된 key가 음수이거나 Exit에 대한 enum 숫자보다 크면 다시 입력받는다
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴 참조 변수일 뿐이다
		Random rand = new Random();
		LinkedList1 l = new LinkedList1();
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 난수 숫자 개수::");
		int count = sc.nextInt(); //난수 생성 갯수
		
		int data = 0;
		do {
			switch (menu = SelectMenu()) {// Menu 생성자 호출 - menu 객체를 리턴한다
			case Add: // 난수를 삽입하는데 올림차순으로 정렬되도록 구현
				for (int i = 0; i < count; i++) {
					data = rand.nextInt(20); // 난수 생성
					l.Add(data); // Linked List에 추가
				}
				break;
			case Delete:
				System.out.println("삭제할 값을 입력: ");
				data = sc.nextInt();
				boolean tag = l.Delete(data);
				System.out.println("삭제 데이터 존재여부: " + tag);
				break;
			case Show: // 리스트 전체를 출력
				l.Show();
				break;
			case Search: // 입력 숫자 n을 검색한다.
				int n = sc.nextInt();
				System.out.println("검색할 값을 입력: ");
				boolean result = l.Search(n);
				if (!result)
					System.out.println("검색 값 = " + n + " 데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + n + " 데이터가 존재합니다.");
				break;
			case Merge:// 리스트 l과 l2를 합병하여 올림차순 정렬이 되게 구현한다
				LinkedList1 l2 = new LinkedList1();
				for (int i = 0; i < count; i++) {
					data = rand.nextInt(20);
					l2.Add(data);
					
				}
				l2.Show();
				l.Merge(l2);// merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
