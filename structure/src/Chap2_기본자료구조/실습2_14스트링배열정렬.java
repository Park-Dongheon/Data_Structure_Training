package Chap2_기본자료구조;

public class 실습2_14스트링배열정렬 {
	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental melon"};

		showData("정렬전", data);
//		sortData(data);
//		showData("정렬후", data);
//		String[] newData = insertString(data, "banana");
//		showData("삽입후", data);
		
	}
	static void showData(String msg,String[] data) {//확장된 for 문으로 출력 
		System.out.println(msg);
		for(String d : data) {
			System.out.print(d + " ");
		}
	}

//	swap() {//스트링의 맞교환 함수로 sortData()에서 호출됨
//		
//	}
	static void sortData(String[] data) {	//올림차순으로 정렬
		String[] temp;
		for(String d : data) {
			
		}
		
	}
//	insertString(){//배열의 사이즈를 1개 증가시킨후 insert되는 스트링 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 스트링 배열을 리턴
//		
//	}
}