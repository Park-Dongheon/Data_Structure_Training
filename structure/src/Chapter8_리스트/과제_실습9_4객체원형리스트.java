package Chapter8_리스트;

/*
 * 정수 리스트 > 객체 리스트> 객체 원형 리스트 
 * 헤드 노드가 있는 원형 리스트, 헤드 노드가 없는 원형 리스트 구현
 * merge 구현: in-place 구현, 새로운 노드를 생성하여 합병 구현 
 * 원형 이중 리스트로 동일하게 적용
 */
import java.util.Comparator;
import java.util.Scanner;


class SimpleObject3 {
	static final int NO = 1; 		// 번호를 읽어 들일까요?
	static final int NAME = 2; 		// 이름을 읽어 들일까요?
	static final int EXPIRE = 3; 	// 유효기간을 읽어 들일까요?

	private String no; 				// 회원번호
	private String name; 			// 이름
	private String expire;			// 유효기간 필드를 추가
	
	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "[" + no + "] " + name + ", 유효기간: " + expire;
	}

	public SimpleObject3(String no, String name, String expire) {	// LinkedList 생성자
		this.no = no;
		this.name = name;
		this.expire = expire;
	}

	public SimpleObject3() {	// head node를 만들 때 사용
		this.no = null;
		this.name = null;
		this.expire = null;
	}

	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) { // & 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
		if ((sw & EXPIRE) == EXPIRE) {
			System.out.print("유효기간: ");
			expire = sc.next();
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject3> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject3> {
		public int compare(SimpleObject3 d1, SimpleObject3 d2) {
			int num1 = Integer.parseInt(d1.no);
			int num2 = Integer.parseInt(d2.no);
			return (num1 > num2) ? 1 : (num1 < num2) ? -1 : 0;
		//  return Integer.compare(num1, num2);	
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject3> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject3> {
		public int compare(SimpleObject3 d1, SimpleObject3 d2) {
			return d1.name.compareTo(d2.name);
		}
	}
	
	// --- 유효기간으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject3> EXPIRE_ORDER = new ExpireOrderComparator();

	private static class ExpireOrderComparator implements Comparator<SimpleObject3> {
		public int compare(SimpleObject3 d1, SimpleObject3 d2) {
			int exp1 = Integer.parseInt(d1.expire);
			int exp2 = Integer.parseInt(d2.expire);
			return (exp1 > exp2) ? 1 : (exp1 < exp2) ? -1 : 0;
		//	return Integer.compare(exp1, exp2);
		}
	}	
	
}

class Node3 {
	SimpleObject3 data;
	Node3 link;

	public Node3(SimpleObject3 element) {
		data = element;
		link = null;
	}
}

class CircularList {
	Node3 first;		// 리스트의 첫 번째 노드를 가리키는 포인터

	public CircularList() { // head node
		SimpleObject3 data = new SimpleObject3();
		first = new Node3(data);
		first.link = first;
	}
	/*
	 * static void sortData(Fruit []arr, Comparator<Fruit> cc) {
	 *  	for (int i = 0; i < arr.length; i++) {
	 *  		for (int j = i; j < arr.length; j++) { 
	 *  			if (cc.compare(arr[i], arr[j])> 0) 
	 *  				swap(arr, i, j); 
	 *  		} 
	 *  	}
	 *  }
	 */
	
	// 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	public void Add(SimpleObject3 element, Comparator<SimpleObject3> cc)
	{
		Node3 newNode = new Node3(element);			// 새 노드를 생성하고 element 로 초기화
		
		// 리스트가 비어 있는 경우, 리스트에 새 노드를 추가하고 메서드를 종료
		if (first.link == first) 
		{
			newNode.link = first;					// 새 노드의 링크를 자기 자신을 가리키는 first 로 설정
			first.link = newNode;					// first 의 링크를 새 노드로 설정하여 리스트에 추가
			return;									// 메서드 종료
		}
		
		Node3 p = first.link;						// 현재 노드를 가리킬 변수 p 를 첫 번째 데이터 노드로 초기화
		Node3 q = first;							// 이전 노드를 가리킬 변수 q 를 first 로 초기화
		
		// 리스트를 오름차순으로 정렬하여 삽입할 위치를 찾음
		while (p != first) {						// 리스트의 끝까지 반복
			if (cc.compare(p.data, element) > 0) {	// p 의 데이터가 element 보다 크면
				break;								// 반복 중지 (p 가 삽입 위치를 가리키게 됨)
			}
			q = p;									// 이전 노드를 현재 노드로 이동
			p = p.link; 							// 현재 노드를 다음 노드로 이동ㄴ
		}
		
		// 새 노드를 적절한 위치에 삽입
		newNode.link = p;							// 새 노드의 링크를 현재 노드로 설정하여 삽입
		q.link = newNode;							// 이전 노드의 링크를 새 노드로 설정하여 삽입
	}
	
	public int Delete(SimpleObject3 element, Comparator<SimpleObject3> cc) // delete the element
	{
	    Node3 q = first;					// 이전 노드를 가리킬 변수 q를 첫 번째 노드로 초기화
	    Node3 current = first.link;			// 현재 노드를 가리킬 변수 current를 첫 번째 노드로 초기화

	    // 리스트가 비어 있는지 확인 (첫 노드가 자기 자신을 가리키는 경우)
	    if (current == first) {
	        System.out.println("리스트가 비어 있습니다.");
	        return -1;						// 삭제할 대상이 없는 경우 -1 반환
	    }

	    // 첫 번째 데이터 노드를 삭제하는 경우
	    if (cc.compare(current.data, element) == 0) {
	        first.link = current.link;		// 첫 번째 노드의 링크를 두 번째 노드로 연결하여 첫 번째 노드 삭제
	        return 1;						// 삭제 성공 시 1 반환
	    }

	    // 삭제할 노드를 찾는 반복문
	    do {
	        if (cc.compare(current.data, element) == 0) {
	            q.link = current.link;		// 이전 노드의 링크를 삭제할 노드의 다음 노드로 연결하여 삭제
	            return 1;					// 삭제 성공 시 1 반환
	        }
	        q = current;					// q 를 현재 노드로 이동
	        current = current.link;			// current 를 다음 노드로 이동
	    } while (current != first.link);	// 다시 첫 번째 데이터 노드에 올때까지 반복
	    
	    // 삭제할 대상을 찾지 못한 경우
	    System.out.println("해당 데이터를 찾을 수 없습니다.");
	    return -1;							// 삭제할 대상을 찾지 못했을 경우 -1 반환
	}
	
	// 전체 리스트를 순서대로 출력한다.
	public void Show() {
		Node3 p = first.link;				// 첫 번째 데이터 노드를 가리킬 변수 p 초기화
		SimpleObject3 so;					// 출력할 데이터 객체를 담을 변수 so 선언
		
		while (p != first) {				// 리스트의 끝에 도달할 때까지 반복
			so = p.data;					// 현재 노드의 데이터를 so 에 저장
			System.out.print(so + " -> ");	// 데이터 출력
			p = p.link;						// 다음 노드로 이동
			
			if (p == first) {				// 다음 노드가 첫 번째 노드일 경우
				System.out.println("회원이 없습니다. \n");	// 리스트가 비어있음을 출력
				break;						// 반복 종료
			}
		}
		
		System.out.println();
	}

	// 회원 번호로 검색하여 출력
	public boolean Search(SimpleObject3 element, Comparator<SimpleObject3> cc) {
		Node3 q, current = first.link;						// 현재 노드를 가리킬 변수 current 초기화
		q = current;										// 검색할 노드를 가리킬 변수 q 초기화
		
		while (q != null) {									// 노드가 있는 동안 반복
			if (cc.compare(q.data, element) == 0) {			// 현재 노드의 데이터와 찾고자 하는 데이터 비교하여 같을 경우 
				System.out.println("회원 정보: " + q.data);	// 회원 정보 출력
				return true;								// 데이터 찾음, true 반환
			}
			
			q = q.link;										// 다음 노드로 이동
		}
		
		return false;										// 리스트 끝까지 찾지 못할 경우 false 반환
	}

	// 리스트 a 와 b 를 병합, 리스트 b 의 모든 요소를 리스트 a 에 병합 
	void Merge(CircularList cl, Comparator<SimpleObject3> cc) {
		/*
		 * 연결리스트 a,b에 대하여 a = a + b merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지
		 * 않고 합병하는 알고리즘 구현 난이도 등급: 최상급 회원번호에 대하여 a = (3, 5, 7), b = (2,4,8,9)이면 a =
		 * (2,3,4,5,8,9)가 되도록 구현하는 코드
		 */
		
		Node3 a = first.link;				// 리스트 a 의 첫 번째 데이터 노드를 가리키는 포인터
		Node3 b = cl.first.link;			// 리스트 b 의 첫 번째 데이터 노드를 가리키는 포인터
		Node3 mergedLast = first;			// 병합된 리스트의 마지막 노드를 가리키는 포인터
		
		// 리스트 b 가 비어 있을 경우 병합할 필요가 없으므로 종료
		if (b == cl.first) {
			System.out.println("리스트 B가 비어 있어 병합할 데이터가 없습니다.");
			return;
		}
		
		// 리스트 a 가 비어있을 경우 리스트 b 의 데이터를 복사하여 리스트 a 에 할당
		if (first.link == first) {
			first.link = b;					// 리스트 a 의 첫 번째 노드를 리스트 b 의 첫 번째 노드로 설정
			
			Node3 bLastNode = b;
			while (bLastNode.link != cl.first) {
				bLastNode = bLastNode.link;
			}
			bLastNode.link = first;			// 리스트 b 의 마지막 노드를 리스트 a 의 첫 번째 노드를 가리키도록 연결
			cl.first.link = cl.first;		// 리스트 b 의 첫 번째 노드를 head node 로 설정
			return;
		}
		
		// 리스트 a 와 b 모두 비어있지 않은 경우
		while (b != cl.first) {
			// 리스트 a 에서 삽입할 위치를 찾음
			while (a != first && cc.compare(a.data, b.data) <= 0) {
				mergedLast = a;				// 병합 리스트의 마지막 노드를 업데이트
				a = a.link;
			}
			
			// 리스트 b 의 현재 노드를 병합 리스트에 삽입
			Node3 temp = b.link;			// 임시 변수에 다음 노드를 저장
			mergedLast.link = b;			// 병합 리스트의 마지막 노드의 링크를 리스트 b 의 현재 노드로 설정
			b.link = a;						// 리스트 b 의 현재 노드의 링크를 리스트 a 의 삽입 위치로 설정
			mergedLast = b;					// 병합 리스트의 마지막 노드를 업데이트
			b = temp;						// 리스트 b 의 현재 노드를 다음 노드로 설정
		}
		
		cl.first.link = cl.first;			// 리스트 b 의 첫 번째 노드를 head node 로 설정
	}
}

public class 과제_실습9_4객체원형리스트 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("합병"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
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
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		CircularList l = new CircularList();
		CircularList l2 = new CircularList();
		Scanner sc = new Scanner(System.in);
		SimpleObject3 data;
		int count = 3;// l2 객체의 숫자를 3개로 한다

		do {
			switch (menu = SelectMenu()) {
			case Add: //
				data = new SimpleObject3();
				data.scanData("입력", 3);
				l.Add(data, SimpleObject3.NO_ORDER);
				break;
			case Delete: //
				data = new SimpleObject3();
				data.scanData("삭제", SimpleObject3.NO);
				int num = l.Delete(data, SimpleObject3.NO_ORDER);
				System.out.println("삭제된 데이터 성공은 " + num + "\n");
				break;
			case Show:
				l.Show();
				break;
			case Search: // 회원 번호 검색
				data = new SimpleObject3();
				data.scanData("탐색", SimpleObject3.NO);
				boolean result = l.Search(data, SimpleObject3.NO_ORDER);
				if (result)
					System.out.println("검색 성공 = " + result + "\n");
				else
					System.out.println("검색 실패 = " + result + "\n");
				break;
			case Merge:
				for (int i = 0; i < count; i++) {// 3개의 객체를 연속으로 입력받아 l2 객체를 만든다
					data = new SimpleObject3();
					data.scanData("병합", 3);
					l2.Add(data, SimpleObject3.NO_ORDER);
				}
				System.out.println("리스트 l::");
				l.Show();
				System.out.println("리스트 l2::");
				l2.Show();
				l.Merge(l2, SimpleObject3.NO_ORDER);
				// merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
				System.out.println("병합 리스트 l::");
				l.Show();
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);	
		sc.close();
	}
	
}
