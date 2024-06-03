package Chapter3_검색알고리즘;

/*
 * 3장 2번 실습과제 - 스트링 배열의 정렬과 이진검색 
* 교재 121 실습 3-6 
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;
public class 실습3_6_0스트링배열정렬이진탐색 {

	public static void main(String[] args) {
		String []data = {"사과","포도","복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외"};
		showData("정렬전", data);
		sortData(data);//올림차순으로 정렬 교재211-212 단순 선택 정렬 알고리즘으로 구현
		showData("\n정렬후", data);

		String key = "포도";
		int resultIndex = linearSearch(data, key);//교재 100 페이지 seqSearch() 함수로 구현, 선형 검색
		System.out.println("\nlinearSearch(포도): key = " + key + ", result 색인 = " + resultIndex);

		key = "배";
		resultIndex = binarySearch(data, key);//교재 109 페이지 binSearch() 함수로 구현, 이진 검색
		System.out.println("\nbinarySearch(배):key = " + key + ",  result = " + resultIndex);
		
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		key = "산딸기";
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(산딸기): key = " + key + ", result = " + resultIndex);
	}

	// 배열의 내용을 출력하는 메서드
	static void showData(String msg, String[] data) {
		System.out.println(msg);
		for(String d : data) {
			System.out.print(d + " ");
		}
	}
	
	// 단순 선택 정렬을 이용, 배열을 정렬하는 메서드
	static void sortData(String[] data) {
		for(int i = 0; i < data.length - 1; i++) {
			int min = i;	// 현재 위치를 최솟값 인덱스로 설정
			for(int j = i + 1; j < data.length; j++) {
				if(data[j].compareTo(data[min]) < 0) {		// 더 작은 값이 있으면 인덱스를 갱신
					min = j;
				}
			}
			swap(data, i, min);		// 현재 위치와 최솟값 위치를 교환
		}
	}

	// 두 인덱스의 값을 교환하는 메서드
	static String swap(String[] data, int i, int min) {
		String tmp = data[i];
		data[i] = data[min];
		data[min] = tmp;
		return tmp;
	}
	
	// 선형 검색을 통해 키를 찾는 메서드
	private static int linearSearch(String[] data, String key) {
		for(int i = 0; i < data.length; i++) {
			if(data[i].equals(key)) {		// 키를 찾으면 인덱스를 반환
				return i;
			}
		}
		return -1;
	}
	
	// 이진 검색을 통해 키를 찾는 메서드
	private static int binarySearch(String[] data, String key) {
		int pl = 0;		// 검색 범위의 시작 인덱스
		int pr = data.length - 1;		// 검색 범위의 끝 인덱스
		
		while(pl <= pr) {
			int pc = (pl + pr) / 2;		// 중간 인덱스를 계산
			if(data[pc] == key) {		// 키를 찾으면 인덱스를 반환
				return pc;				
			}
			else if(data[pc].compareTo(key) < 0) {		// 키가 중간값보다 크면 오른쪽 반 검색
				pl = pc + 1;				
			}
			else {		// 키가 중간값보다 작으면 왼쪽 반 검색 
				pr = pc - 1;				
			}
		}		
		return -1;
	}
	
}
