package Chapter10_해시;

import java.util.Scanner;
//체인법에 의한 해시
//--- 해시를 구성하는 노드 ---//

class Node {
	int key; 									// 키값
	Node next; 									// 뒤쪽 포인터(뒤쪽 노드에 대한 참조)

	// 생성자
	public Node(int key, Node next) {
		this.key = key;
		this.next = next;
	}

	// 키 값 반환
	public int getKey() {
		return key;
	}

}

class SimpleChainHash {
	private int size; 								// 해시 테이블의 크기
	private Node[] table; 							// 해시 테이블

	public SimpleChainHash(int size) {
		try {
			this.size = size;
			table = new Node[size];
		} catch (OutOfMemoryError e) { 				// 테이블을 생성할 수 없음
			this.size = 0;
		}
	}

	//--- 해시 값을 구함 ---//
	public int hashValue(int key) {
//		return key.hashCode() % size;
		return (key * key * 31) % size; 			// 31은 소수이자 홀수, 비트 연산 최적화가 가능
	}
	
	//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(int key) {
		int hash = hashValue(key); 					// 검색할 데이터의 해시값
		Node selectN = table[hash]; 				// 선택 노드

		while (selectN != null) { 					// 선택 노드가 null 일 때까지 반복
			if (selectN.getKey() == key) { 			// 선택 노드의 key 값과 검색할 key 값이 일치할 경우
				return 1; 							// 검색 성공
			}
			selectN = selectN.next; 				// 다음 노드를 선택
		}

		return 0; 									// 검색 실패
	}

	//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(int key) {
		int hash = hashValue(key); 					// 추가할 데이터의 해시값
		Node selectN = table[hash]; 				// 선택 노드

		// 선택 노드의 해시 값이 존재할 경우, key 값이 중복값인지 확인
		while (selectN != null) { 					// 선택 노드가 null 일 때까지 반복
			if (selectN.key == key) { 				// 선택 노드의 key 값과 추가할 데이터의 키 값이 같은지 비교
				return 0;							// 이 키 값은 이미 등록됨, 중복 Duplicated data
			}
			selectN = selectN.next;					// 중복값이 아닌 경우 다음 노드로 이동
		}

		// 충돌이 발생할 경우 연결 리스트(체이닝)를 사용하여 새로운 노드를 추가
		Node newNode = new Node(key, table[hash]); 	// 새 노드를 기존의 첫 번째 노드 앞에 추가
		table[hash] = newNode; 						// 새 노드를 테이블에 삽입
		return 1;									// 새 노드가 성공적으로 추가
	}

	//--- 키값이 key인 요소를 삭제 ---//
	public int delete(int key) {
		int hash = hashValue(key);					// 삭제할 데이터의 해시값
		Node selectN = table[hash];					// 선택 노드
		Node prevN = null;							// 이전 노드, 선택 노드를 따라가는 노드, 선택 노드 바로 앞을 가리킴
		
		while (selectN != null) {					// 선택 노드가 null 일 때까지 반복
			if (selectN.key == key) {				// 삭제할 키 값을 찾은 경우
				if (prevN == null) {				// 이전 노드가 null 인 경우, 해시 값이 충돌이 발생하지 않은 경우
					table[hash] = selectN.next;		// 해시 테이블의 값을 선택 노드의 링크로 설정
				}
				else {								// 해시 값의 충돌이 발생하여 오픈 해시법(Chaining)로 LinkedList 인 경우
					prevN.next = selectN.next;		// 이전 노드의 링크를 선택 노드의 다음 노드로 설정
				}
				return 1;
			}
			// 삭제할 키 값을 찾지 못 한 경우
			prevN = selectN;						// 이전 노드를 현재 선택 노드로 설정
			selectN = selectN.next;					// 다음 노드로 이동
			
		}
		
		return 0;									// 삭제할 데이터가 없음
	}

	//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < size; i++) {					// 해시 테이블을 순회
			Node selectN = table[i];						// 각 버킷의 연결리스트(체이닝) 생성
			System.out.printf("%02d : ", i);				// 버킷 출력
			while (selectN != null) {						// 선택 노드가 null 일 때까지 반복
				System.out.printf("-> %s ", selectN.key);	// 선택 노드의 key 값 출력
				selectN = selectN.next;						// 다음 노드로 이동
			}
			System.out.println();							// 해시 테이블의 다음 버킷
		}
		System.out.println();
	}
	
}

public class 실습10_1정수체인해시 {

	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), Show("출력"), Exit("종료");

		private final String message; 			// 표시할 문자열

		static Menu MenuAt(int idx) { 			// 순서가 idx 번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { 					// 생성자(constructor)
			message = string;
		}

		String getMessage() { 					// 표시할 문자열을 반환
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

	// 체인법에 의한 해시 사용 예
	public static void main(String[] args) {
		Menu menu;
		SimpleChainHash hash = new SimpleChainHash(11);
		Scanner stdIn = new Scanner(System.in);
		int select = 0, result = 0, val = 0;
		final int count = 15;
		
		do {
			switch (menu = SelectMenu()) {
			case Add:
				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					double d = Math.random();
					input[ix] = (int) (d * 20);
					System.out.print(input[ix] + " ");
				}
				System.out.println();
				for (int i = 0; i < count; i++) {
					if ((hash.add(input[i])) == 0)
						System.out.println("Insert Duplicated data : " + input[i]);
				}
				System.out.println();
				break;

			case Delete:
				// Delete
				System.out.println("Delete Value:: ");
				val = stdIn.nextInt();
				result = hash.delete(val);
				if (result == 1)
					System.out.println("삭제할 데이터가 존재한다. " + val);
				else
					System.out.println("삭제할 데이터가 없음");
				System.out.println();
				break;

			case Search:
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.search(val);
				if (result == 1)
					System.out.println("검색 데이터가 존재한다. " + "(" + val + ")");
				else
					System.out.println("검색 데이터가 없음");
				System.out.println();
				break;

			case Show:
				hash.dump();
				System.out.println();
				break;
			
			case Exit:
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		} while (menu != Menu.Exit);

		stdIn.close();
	}
}
