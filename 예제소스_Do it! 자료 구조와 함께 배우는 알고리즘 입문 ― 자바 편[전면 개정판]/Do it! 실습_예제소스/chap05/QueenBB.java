// 각 행과 열에 퀸 1개를 배치하는 조합을 재귀적으로 나열

class QueenBB {
    static boolean[] flag = new boolean[8];        // 각 행에 퀸이 이미 배치되었는가?
    static int[] pos = new int[8];                 // 각 열의 퀸의 위치

    //--- 각 열의 퀸의 위치를 출력 ---//
    static void print() {
        for (int i = 0; i < 8; i++)
            System.out.printf("%2d", pos[i]);
        System.out.println();
    }

    //--- i열의 알맞은 위치에 퀸을 배치 ---//
    static void set(int i) {
        for (int j = 0; j < 8; j++) {
            if (flag[j] == false) {    // j 행에 퀸을 아직 배치하지 않음
                pos[i] = j;            // 퀸을 j행에 배치함
                if (i == 7)            // 모든 열에 배치함
                    print();
                else {
                    flag[j] = true;
                    set(i + 1);
                    flag[j] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        set(0);
    }
}
