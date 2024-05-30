package Chapter2_기본자료구조;

public class 실습2_14스트링배열정렬 {
	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental_melon"};

		showData("정렬전", data);
		sortData(data);
		showData("\n정렬후", data);
		
		String[] newData = insertString(data, "banana");
		showData("\n삽입후", newData);
		
	}
	static void showData(String msg,String[] data) {//확장된 for 문으로 출력 
		System.out.println(msg);
		for(String d : data) {
			System.out.print(d + " ");
		}
	}

	static void swap(String[] data, int j) {//스트링의 맞교환 함수로 sortData()에서 호출됨
		String temp = data[j];
		data[j] = data[j + 1];
		data[j + 1] = temp;
	}
	
	static void sortData(String[] data) {	//올림차순으로 정렬, 버블정렬 알고리즘
		for(int i = 0; i < data.length - 1; i++) {
			for(int j = 0; j < data.length - 1 - i; j++) {
				if(data[j].compareTo(data[j + 1]) > 0) {
					swap(data, j);
				}
			}
		}
		
	}
	static String[] insertString(String[] data, String word){//배열의 사이즈를 1개 증가시킨후 insert되는 스트링 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 스트링 배열을 리턴
		// 새로운 배열 생성 (크기는 기존 배열 크기 + 1)
		String[] newData = new String[data.length + 1];
		
		int i;
		// 기존 배열의 요소를 새로운 배열로 복사 (삽입 위치 전까지)
		for(i = 0; data.length > i && data[i].compareTo(word) < 0; i++ ) {
			newData[i] = data[i];
		}
		
		// 새로운 요소 삽입
		newData[i] = word;
		
		// 기존 배열의 나머지 요소를 새로운 배열로 복사 (삽입 위치 이후)
		for(int j = i; j < data.length; j++) {
			newData[j + 1] = data[j];
		}
		
		return newData;
	}
}