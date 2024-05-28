// 배열의 복제 배열을 만듭니다.

import java.util.Arrays;

class CloneArray {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = a.clone();            // b는 a를 복제한 배열을 참조

        b[3] = 0;                                    // 한 요소만 따로 변경

        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));
    }
}
