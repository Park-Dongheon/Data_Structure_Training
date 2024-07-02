package Chapter11_최소신장트리_그래프;

import java.util.Scanner;

//minimal spanning tree:: Kruskal’s source code
//min heap, set 사용하여 MST 구현
interface MaxHeap {
	public void Insert(Edge x);
	public Edge DeleteMin();
}

class Edge implements Comparable<Edge> {
	int src;
	int dest;
	int weight;
}

class Heap implements MaxHeap {
	final int heapSize = 100;
	private Edge[] heap;
	private int n; // current size of MaxHeap
	private int MaxSize; // Maximum allowable size of MaxHeap

	public Heap(int sz) {
		MaxSize = sz;
		n = 0;
		heap = new Edge[MaxSize + 1]; // heap[0]은 사용하지 않음
	}

	public void display() {
		int i;
		for (i = 1; i <= n; i++)
			System.out.print("  (" + i + ", " + heap[i] + ") ");
		System.out.println();
	}

	@Override
	public void Insert(Edge x) {

	}

	@Override
	public Edge DeleteMin() {

	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}

class InputGraph2 {
	int start;
	int end;
	int weight;

	public InputGraph2(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
}

public class 수정_Chap11_test_minimalspanningtree {

	//The main function to construct MST using Kruskal's algorithm
	static int addEdgeSet(Edge[] edgeSet, int e, int from, int to, int w) {

	}

	static int makeEdgeSet(Graph grp, Edge[] edgeSet, int e) {
		// graph의 adjacency list를 읽어 edge set를 만든다

	}

	static void KruskalMST(Graph graph, int n) {
		Edge[] edgeSet = new Edge[30];
		Edge result[] = new Edge[30]; // MST
		for (int p = 0; p < 30; p++) {
			edgeSet[p] = new Edge();
			result[p] = new Edge();
		}

		int t = 0; // MST result[]
		int edgeNum = 0; // edgeSet
		edgeNum = makeEdgeSet(graph, edgeSet, edgeNum);

		Heap hp = new Heap(100);
		for (int j = 0; j < edgeNum; j++) {
			hp.Insert(edgeSet[j]);
		}

		Sets m = new Sets(20);

		while (t < n - 1)// t contains less than n-1 edges
		{
			// choose an edge (v,w) from E of lowest cost

			// Else discard the next_edge
		}
		if (t < n - 1) {
			System.out.println("no spanning tree");
			return;
		}
		// MST 출력

		for (int k = 0; k < t; k++)
			System.out.println(result[k] + " ");
		return;
	}

	//Driver program to test above functions
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int select = 0, n, weight;
		int startBFSNode = 100;// the start node to BFS
		int startEdge, endEdge;
		System.out.println("Input the total node number: ");
		n = sc.nextInt();

		/* Let us create following weighted graph */

		Graph graph = new Graph(n);
		while (select != '0') {
			System.out.println("\n명령선택:: 1.edges/Weight 입력, 2. Adjacency Lists 출력, 3. spanningTree, 4: Quit => ");
			select = sc.nextInt();
			switch (select) {

			case 1:
				InputGraph2[] inputData = {
						new InputGraph2(0, 1, 28), new InputGraph2(0, 5, 10),
						new InputGraph2(1, 2, 16), new InputGraph2(1, 6, 14), new InputGraph2(2, 3, 12),
						new InputGraph2(3, 4, 22), new InputGraph2(3, 6, 18), new InputGraph2(4, 6, 24),
						new InputGraph2(4, 5, 25) };
				for (int i = 0; i < inputData.length; i++) {
					startEdge = inputData[i].start;
					endEdge = inputData[i].end;
					weight = inputData[i].weight;
					graph.InsertVertex(startEdge, endEdge, weight);
				}
				break;
				
			case 2:
				// display
				graph.displayAdjacencyLists();
				break;
				
			case 3:
				System.out.println("\n Minimal SpanningTree 작업 시작");
				KruskalMST(graph, n);
				break;
				
			case 4:
				System.exit(0);
				break;
				
			default:
				System.out.println("WRONG INPUT  ");
				System.out.println("Re-Enter");
				break;
			}
		}
		return;
	}
}
