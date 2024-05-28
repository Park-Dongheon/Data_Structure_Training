// 연습9-3
// 이진검색트리 클래스 BinTree<K,V>의 사용 예
// 주의 : 
// 이 프로그램의 키(key)는 【정수의 내림차순】입니다.
// 가장 작은 키값은 , 회원번호의 【최댓값】이고, 
// 가장 큰 키값은 , 회원번호의 【최솟값】입니다.

import java.util.Scanner;
import java.util.Comparator;

class BinTreeTester {
	static Scanner stdIn = new Scanner(System.in);

	//--- 데이터(회원번호＋이름) ---//
	static class Data {
		public static final int NO   = 1;	// 번호를 입력 받을까요?
		public static final int NAME = 2;	// 이름을 입력 받을까요?

		private Integer no;				// 회원번호(키값)
		private String  name;			// 이름

		//--- 키값 ---//
		Integer keyCode() {
			return no;
		}

		//--- 문자열로 만들어 반환합니다 ---//
		public String toString() {
			return name;
		}

		//--- 데이터를 입력받음 ---//
		void scanData(String guide, int sw) {
			System.out.println(guide + "할 데이터를 입력하세요.");

			if ((sw & NO) == NO) {
				System.out.print("번호 : ");
				no = stdIn.nextInt();
			}
			if ((sw & NAME) == NAME) {
				System.out.print("이름 : ");
				name = stdIn.next();
			}
		}
	}

	//--- 메뉴 열거형 ---//
	enum Menu {
		ADD(      "삽입"),
		REMOVE(   "삭제"),
		SEARCH(   "검색"),
		PRINT(    "출력"),
		PRINTR(   "내림차순출력"),
		MIN_KEY(  "가장 작은 키값"),
		MIN_DATA( "가장 작은 키(key)를 갖는 데이터"),
		MAX_KEY(  "가장 큰 키값"),
		MAX_DATA( "가장 큰 키(key)를 갖는 데이터"),
		TERMINATE("종료");

		private final String message;			// 출력용 문자열

		static Menu MenuAt(int idx) {			// 순서가 idx인 열거를 반환합니다
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) {				// 생성자
			message = string;
		}

		String getMessage() {				// 출력용 문자열을 반환합니다
			return message;
		}
	}

	//--- 메뉴 선택 ---//
	static Menu SelectMenu() {
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu;								// 메뉴 
		Data data;								     // 추가용 데이터 참조
		Data ptr;									// 검색용 데이터 참조
		Data temp = new Data();		                // 입력용 데이터

		class IntegerDecComparator implements Comparator<Integer> {
			public int compare(Integer n1, Integer n2) {
				return (n1 > n2) ? -1 : (n1 < n2) ? 1 : 0;
			}
		}

		//---정수의 내림차순으로 순서를 정하는 컴퍼레이터  ---//
		final IntegerDecComparator INT_DEC_COMP = new IntegerDecComparator();

		BinTree<Integer, Data> tree = new BinTree<Integer, Data>(INT_DEC_COMP);

		do {
			switch (menu = SelectMenu()) {
			 case ADD :					// 노드의 삽입
					data = new Data();
			 		data.scanData("삽입", Data.NO | Data.NAME);
			 		tree.add(data.keyCode(), data);
			 		break;

			 case REMOVE :			// 노드의 삭제
					temp.scanData("삭제", Data.NO);
			 		tree.remove(temp.keyCode());
			 		break;

			 case SEARCH :			// 노드의 검색
					temp.scanData("검색", Data.NO);
					ptr = tree.search(temp.keyCode());
					if (ptr != null)
						System.out.println("그 번호의 이름은 " + ptr + "입니다.");
					else
						System.out.println("해당 데이터가 없습니다.");
					break;

			 case PRINT :				// 모든 노드를 키값의 오름차순으로 출력
					tree.print();
					break;

			 case PRINTR :			// 모든 노드를 키값의 내림차순으로 출력
					tree.printReverse();
					break;

			 case MIN_KEY : {		// 가장 작은 키값을 출력
					Integer key = tree.getMinKey();
					if (key != null)
						System.out.println("가장 작은 키값은 " + key + "입니다.");
					else
						System.out.println("데이터가 없습니다.");
					break;
					}

			 case MIN_DATA :		// 가장 작은 키값을 갖는 데이터를 출력
					ptr = tree.getDataWithMinKey();
					if (ptr != null)
						System.out.println("가장 작은 키값을 갖는 데이터는 " + ptr + "입니다.");
					else
						System.out.println("데이터가 없습니다.");
					break;

			 case MAX_KEY : {		// 가장 큰 키값을 출력
					Integer key = tree.getMaxKey();
					if (key != null)
						System.out.println("가장 큰 키값은 " + key + "입니다.");
					else
						System.out.println("데이터가 없습니다.");
					break;
					}

			 case MAX_DATA :		// 가장 큰 키값을 갖는 데이터를 출력
					ptr = tree.getDataWithMaxKey();
					if (ptr != null)
						System.out.println("가장 큰 키값을 갖는 데이터는 " + ptr + "입니다.");
					else
						System.out.println("데이터가 없습니다.");
					break;
			}
		} while (menu != Menu.TERMINATE);
	}
}
