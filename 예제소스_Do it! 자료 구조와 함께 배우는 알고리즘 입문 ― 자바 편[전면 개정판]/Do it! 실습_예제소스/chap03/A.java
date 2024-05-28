// 자연 정렬을 하려면 다음과 같은 패턴으로 클래스를 정의(예)

class A implements Comparable<A> {

    // 필드, 메소드 등

    public int compareTo(A c) {
        // this가 c보다 크면 양수를,
        // this가 c보다 작으면 음수를,
        // this가 c와 같으면 0을 반환합니다.
    }

    public boolean equals(Object c) {
        // this가 c와 같으면 true를,
        // this가 c와 같지 않으면 false를 반환합니다.
    }
}