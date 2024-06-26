package Chapter8_리스트;

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject6 {
	static final int NO = 1;
	static final int NAME = 2;
	static final int EXPIRE = 3;

	private String no;
	private String name;
	private String expire;

	public String toString() {
		return "[" + no + "] " + name + ", 유효기간: " + expire;
	}

	public SimpleObject6(String no, String name, String expire) {
		this.no = no;
		this.name = name;
		this.expire = expire;
	}

	public SimpleObject6() {
		this.no = null;
		this.name = null;
		this.expire = null;
	}

	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) {
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

	public static final Comparator<SimpleObject6> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject6> {
		public int compare(SimpleObject6 d1, SimpleObject6 d2) {
			int num1 = Integer.parseInt(d1.no);
			int num2 = Integer.parseInt(d2.no);
			return (num1 > num2) ? 1 : (num1 < num2) ? -1 : 0;
		}
	}

	public static final Comparator<SimpleObject6> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject6> {
		public int compare(SimpleObject6 d1, SimpleObject6 d2) {
			return d1.name.compareTo(d2.name);
		}
	}

	public static final Comparator<SimpleObject6> EXPIRE_ORDER = new ExpireOrderComparator();

	private static class ExpireOrderComparator implements Comparator<SimpleObject6> {
		public int compare(SimpleObject6 d1, SimpleObject6 d2) {
			int exp1 = Integer.parseInt(d1.expire);
			int exp2 = Integer.parseInt(d2.expire);
			return (exp1 > exp2) ? 1 : (exp1 < exp2) ? -1 : 0;
		}
	}
}

class Node6 {
	SimpleObject6 data;
	Node6 link;

	public Node6(SimpleObject6 element) {
		data = element;
		link = null;
	}
}

class CircularList2{
	Node6 first;

	public CircularList2() {
		SimpleObject6 data = new SimpleObject6();
		first = new Node6(data);
		first.link = first;
	}

	public void Add(SimpleObject6 element, Comparator<SimpleObject6> cc) {
		Node6 newNode = new Node6(element);

		if (first.link == first) {
			newNode.link = first;
			first.link = newNode;
			return;
		}

		Node6 p = first.link;
		Node6 q = first;

		while (p != first) {
			if (cc.compare(p.data, element) > 0) {
				break;
			}
			q = p;
			p = p.link;
		}

		newNode.link = p;
		q.link = newNode;
	}

	public int Delete(SimpleObject6 element, Comparator<SimpleObject6> cc) {
		Node6 q = first;
		Node6 current = first.link;

		if (current == first) {
			System.out.println("리스트가 비어 있습니다.");
			return -1;
		}

		if (cc.compare(current.data, element) == 0) {
			first.link = current.link;
			return 1;
		}

		do {
			if (cc.compare(current.data, element) == 0) {
				q.link = current.link;
				return 1;
			}
			q = current;
			current = current.link;
		} while (current != first.link);

		System.out.println("해당 데이터를 찾을 수 없습니다.");
		return -1;
	}

	public void Show() {
		Node6 p = first.link;
		SimpleObject6 so;

		while (p != first) {
			so = p.data;
			System.out.print(so + " -> ");
			p = p.link;

			if (p == first) {
				System.out.println("회원이 없습니다. \n");
				break;
			}
		}

		System.out.println();
	}

	public boolean Search(SimpleObject6 element, Comparator<SimpleObject6> cc) {
		Node6 q, current = first.link;
		q = current;

		while (q != null) {
			if (cc.compare(q.data, element) == 0) {
				System.out.println("회원 정보: " + q.data);
				return true;
			}

			q = q.link;
		}

		return false;
	}

	void Merge(CircularList2 cl, Comparator<SimpleObject6> cc) {
		Node6 a = first.link;
		Node6 b = cl.first.link;
		Node6 mergedLast = first;

		if (b == cl.first) {
			System.out.println("리스트 B가 비어 있어 병합할 데이터가 없습니다.");
			return;
		}

		if (first.link == first) {
			first.link = b;

			Node6 bLastNode = b;
			while (bLastNode.link != cl.first) {
				bLastNode = bLastNode.link;
			}
			bLastNode.link = first;
			cl.first.link = cl.first;
			return;
		}

		while (b != cl.first) {
			while (a != first && cc.compare(a.data, b.data) <= 0) {
				mergedLast = a;
				a = a.link;
			}

			Node6 temp = b.link;
			mergedLast.link = b;
			b.link = a;
			mergedLast = b;
			b = temp;
		}

		cl.first.link = cl.first;
	}
}

public class 과제_실습9_4객체원형리스트_NoHead {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("합병"), Exit("종료");

		private final String message;

		static Menu MenuAt(int idx) {
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) {
			message = string;
		}

		String getMessage() {
			return message;
		}
	}

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
		Menu menu;
		CircularList2 l = new CircularList2();
		CircularList2 l2 = new CircularList2();
		Scanner sc = new Scanner(System.in);
		SimpleObject6 data;
		SimpleObject6 temp = new SimpleObject6();
		int count = 3;// l2 객체의 숫자를 3개로 한다

		do {
			switch (menu = SelectMenu()) {
				case Add:
					data = new SimpleObject6();
					data.scanData("입력", 3);
					l.Add(data, SimpleObject6.NO_ORDER);
					break;
				case Delete:
					temp.scanData("삭제", SimpleObject6.NO);
					l.Delete(temp, SimpleObject6.NO_ORDER);
					break;
				case Show:
					l.Show();
					break;
				case Search:
					temp.scanData("검색", SimpleObject6.NO);
					boolean result = l.Search(temp, SimpleObject6.NO_ORDER);
					if (result) {
						System.out.println("해당 회원을 찾았습니다.");
					} else {
						System.out.println("해당 회원을 찾을 수 없습니다.");
					}
					break;
				case Merge:
					System.out.println("리스트 l2에 입력할 데이터를 입력하세요.");
					for (int i = 0; i < count; i++) {// 3개의 객체를 연속으로 입력받아 l2 객체를 만든다
						data = new SimpleObject6();
						data.scanData("병합", 3);
						l2.Add(data, SimpleObject6.NO_ORDER);
					}
					System.out.println("리스트 l::");
					l.Show();
					System.out.println("리스트 l2::");
					l2.Show();
					l.Merge(l2, SimpleObject6.NO_ORDER);
					System.out.println("병합 리스트 l::");
					l.Show();
					break;
				case Exit:
					System.out.println("프로그램을 종료합니다.");
					break;
			}
		} while (menu != Menu.Exit);
		sc.close();
	}
}