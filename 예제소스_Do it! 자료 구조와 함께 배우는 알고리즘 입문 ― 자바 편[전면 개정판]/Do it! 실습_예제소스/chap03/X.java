// 클래스 X의 내부에서 COMPARATOR를 정의하는 방법(예)

import java.util.Comparator;

class X {
    // 필드, 메소드 등
    public static final Comparator<T> COMPARATOR = new Comp();

    private static class Comp implements Comparator<T> {
        public int compare(T d1, T d2) {
            // d1이 d2보다 크면 양의 값을,
            // d1이 d2보다 작으면 음의 값을,
            // d1이 d2와 같으면 0을 반환합니다.
        }
    }
}