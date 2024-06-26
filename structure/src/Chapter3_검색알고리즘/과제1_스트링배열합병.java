package Chapter3_검색알고리즘;

/*
 * 3장 과제1: 스트링 배열 합병 정렬
 */
import java.util.Arrays;

public class 과제1_스트링배열합병 {
    public static void main(String[] args) {
        // 문자열 배열 s1을 생성하고 초기화
        String[] s1 = { "홍길동", "강감찬", "을지문덕", "계백", "김유신", "최치원" };
        // 문자열 배열 s2를 생성하고 초기화
        String[] s2 = { "독도", "울릉도", "한산도", "영도", "오륙도", "동백섬" };
        
        // 배열 s1을 정렬
        Arrays.sort(s1);
        // 배열 s2을 정렬
        Arrays.sort(s2);

        // s1 배열 출력
        showList("s1배열 = ", s1);
        // s2 배열 출력
        showList("\ns2배열 = ", s2);

        // mergeList 함수를 이용하여 s1과 s2를 병합하고 그 결과를 s3에 저장하여 출력
        String[] s3 = mergeList(s1, s2);
        showList("\n스트링 배열 s3 = s1 + s2:: ", s3);
    }

    // 문자열 배열을 출력하는 메서드
    private static void showList(String msg, String[] s) {
        System.out.print(msg);
        // 배열 s의 요소를 모두 출력
        for(String name : s) {
            System.out.print(name + " ");
        }
    }
    
    // 두 개의 정렬된 문자열 배열을 병합하여 하나의 정렬된 배열을 반환하는 메서드
    private static String[] mergeList(String[] s1, String[] s2) {
        // 병합된 배열의 크기를 계산하여 새로운 배열 s3를 생성
        String[] s3 = new String[s1.length + s2.length];
        // 배열 s1의 인덱스를 가리키는 변수 pa
        int pa = 0;
        // 배열 s2의 인덱스를 가리키는 변수 pb
        int pb = 0;
        // 배열 s3의 인덱스를 가리키는 변수 pc
        int pc = 0;
    
        // 배열 s1과 s2의 요소를 비교하면서 s3에 병합
        while(pa < s1.length && pb < s2.length)
            // s1의 현재 요소가 s2의 현재 요소보다 작거나 같으면 s1의 요소를 s3에 병합
            if (s1[pa].compareTo(s2[pb]) <= 0 )
                s3[pc++] = s1[pa++];
            // 그렇지 않으면 s2의 요소를 s3에 병합
            else
                s3[pc++] = s2[pb++];
        
        // 배열 s1이 남아있는 경우 남은 요소를 s3에 병합
        while(pa < s1.length)
            s3[pc++] = s1[pa++];
        
        // 배열 s2가 남아있는 경우 남은 요소를 s3에 병합
        while(pb < s2.length)
            s3[pc++] = s2[pb++];
        
        // 병합된 배열 s3를 반환
        return s3;
    }

}