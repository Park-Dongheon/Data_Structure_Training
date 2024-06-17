package Chapter7_집합_문자열검색;

class Sets {
	public Sets(int sz) {
		n = sz;
		parent = new int[n + 1]; // Don't want to use parent[0]
		for (int i = 1; i <= n; i++)
			parent[i] = -1; // 0 for Simple versions, 0이 root
	}

	public void display() {

		for (int i = 1; i <= n; i++)
			System.out.print(" " + i);
		System.out.println();
		for (int i = 1; i <= n; i++)
			System.out.print(" " + parent[i]);
		System.out.println();
	}

	public void SimpleUnion(int i, int j)
	// Replace the disjoint sets with roots i and j, i != j with their union
	{
		// i,j는 임의 노드로서 각 집합 i의 root를 찾아 j의 root에 연결, Union(1, 3) == Union(0, 2) /
		// Union(i, j)
		//
	}

	public int SimpleFind(int i)
	// Find the root of the tree containing element i
	{
		while (parent[i] > 0)
			i = parent[i];
		return i;
	}

	public void WeightedUnion(int i, int j)
	// Union sets with roots i and j, i != j, using the weighting rule.
	// parent[i]=-count[i] and parent[i]=-count[i].
	{
		// i가 속한 집합과 j가 속한 집합의 갯수를 비교하여 작은 집합에 큰 집합에 속하게 구현
	}

	public int CollapsingFind(int i)
	// Find the root of the tree containing element i.
	// Use the collapsing rule to collapse all nodes from @i@ to the root
	// 경로가 길경우 연결을 끊고 root에 붙임
	{
		int r;
		for (r = i; parent[r] > 0; r = parent[r])
			; // find root
		while (i != r) {
			int s = parent[i];
			parent[i] = r;
			i = s;
		}
		return r;
	}

	public int Getter() {
		return n;
	}

	int[] parent;
	private int n;
}

public class 실습7_1세트연산 {
	static boolean IsCycle(Sets s, int i, int j) {
		// i,j가 같은 집합에 있는가?
	}

	static int HowManySets(Sets s) {
		int count = 0;
		// s에 있는 집합의 갯수는?
		return count;
	}

	public static void main(String[] args) {

		Sets s1 = new Sets(20);
		s1.SimpleUnion(0, 5);
		s1.SimpleUnion(7, 1);
		s1.SimpleUnion(2, 3);
		s1.SimpleUnion(4, 5);
		s1.SimpleUnion(6, 7);
		s1.SimpleUnion(4, 2);
		s1.SimpleUnion(5, 7);
		s1.SimpleUnion(9, 11);
		s1.SimpleUnion(13, 9);
		s1.display();
		int n1 = s1.SimpleFind(5);
		int n2 = s1.SimpleFind(7);
		System.out.println("5의 parent = " + n1 + "  7의 parent = " + n2);

		if (IsCycle(s1, 7, 9))
			System.out.println("7과 9는 같은 집합이다");
		else
			System.out.println("7과 9는 다른 집합이다");
		System.out.println("세트의 갯수는 " + HowManySets(s1));
		s1.WeightedUnion(1, 2);
		s1.WeightedUnion(3, 4);
		s1.WeightedUnion(5, 6);
		s1.WeightedUnion(7, 8);
		s1.display();
		System.out.println("*find 5: " + s1.CollapsingFind(5) + "\n");

		s1.display();
		System.out.println("**find 5: " + s1.CollapsingFind(5) + "\n");
		System.out.println("find 6: " + s1.CollapsingFind(6) + "\n");
		s1.WeightedUnion(1, 3);
		s1.WeightedUnion(5, 7);
		s1.display();
		System.out.println("find 5: " + s1.CollapsingFind(5) + "\n");
		System.out.println("find 6: " + s1.CollapsingFind(6) + "\n");
		System.out.println("find 7: " + s1.CollapsingFind(7) + "\n");
		System.out.println("find 8: " + s1.CollapsingFind(8) + "\n");
		s1.WeightedUnion(1, 5);
		s1.display();
		System.out.println("find 1: " + s1.CollapsingFind(1) + "\n");
		System.out.println("find 2: " + s1.CollapsingFind(2) + "\n");
		System.out.println("find 3: " + s1.CollapsingFind(3) + "\n");
		System.out.println("find 4: " + s1.CollapsingFind(4) + "\n");
		System.out.println("find 5: " + s1.CollapsingFind(5) + "\n");
		System.out.println("find 6: " + s1.CollapsingFind(6) + "\n");
		System.out.println("find 7: " + s1.CollapsingFind(7) + "\n");
		System.out.println("find 8: " + s1.CollapsingFind(8) + "\n");
		s1.display();
	}
}