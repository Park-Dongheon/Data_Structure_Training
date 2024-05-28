// 연습5-10
// ８퀸 문제를 비재귀적으로 vnfdl(배치 상황을 □와 ■으로 출력)

class EightQueenN {

	static boolean[] flag_a = new boolean[8];		// 각행에 퀸을 배치했는지 체크
	static boolean[] flag_b = new boolean[15];		// /대각선에 퀸을 배치했는지 체크
	static boolean[] flag_c = new boolean[15];		// ＼대각선에 퀸을 배치했는지 체크
	static int[] pos = new int[8];	// 각열의 퀸의 위치

	//--- 배치 상황(각열의 퀸의 위치)을 출력 ---//
	static void print() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				System.out.printf("%s", j == pos[i] ? "■" : "□");
			System.out.println();
		}
		System.out.println();
	}

	//--- i열의 알맞은 위치에 퀸을 배치 ---//
	static void set(int i) {
		int j;
		int[] jstk = new int[8];

		Start : while (true) {
			j = 0;
			while (true) {
				while (j < 8) {
					if (!flag_a[j] && !flag_b[i + j] && !flag_c[i - j + 7]) {
						pos[i] = j;
						if (i == 7)								// 모든 열에 배치종료
							print();
						else {
							flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = true;
							jstk[i++] = j;					// i열의 행을 Push
							continue Start;
						}
					}
					j++;
				}
				if (--i == -1) return;
				j = jstk[i];									// i열의 행을 Pop
				flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = false;
				j++;
			}
		}
	}

	public static void main(String[] args) {
		set(0);
	}
}

