package Chapter3_검색알고리즘;

/*
 * 3장 3번 실습과제 - 객체 배열의 정렬과 이진검색 - Comparable 구현
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData2 implements Comparable<PhyscData2>{
	String name;
	int height;
	double vision;

	public PhyscData2(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}
	
	@Override
	public String toString() {//[홍길동,162,0.3] 형태로 리턴한다 , Object 클래스
		return "[" + name + "," + height + "," + vision + "]";
	}
	
	@Override
	public int compareTo(PhyscData2 p) {//추상 메소드
		return this.name.compareTo(p.name) 
				&& Integer.compare(height, p.height)
				&& Double.compare(vision, p.vision);
	}
	
	@Override
	public boolean equals(Object obj) {//Object 클래스
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		PhyscData2 pd = (PhyscData2) obj;
		return this.name.equals(pd.name) 
				&& height == pd.height 
				&& Double.compare(this.vision, pd.vision) == 0;
	}

}
public class 실습3_6_1객체배열이진탐색 {
	public static void main(String[] args) {
		PhyscData2[] data = {
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("나동", 164, 1.3),
				new PhyscData2("최길", 152, 0.7),
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("박동", 182, 0.6),
				new PhyscData2("박동", 167, 0.2),
				new PhyscData2("길동", 167, 0.5),
		};
		showData("정렬전", data);
		sortData(data);//6장 06-4 단순 삽입 정렬 InsertionSort() 함수로 구현
		showData("정렬후", data);
		reverse(data);
		showData("역순 재배치후", data);
		Arrays.sort(data);//사용된다 그 이유는? 이해가 되어야 한다 
		showData("Arrays.sort() 정렬후", data);
		
		PhyscData2 key = new PhyscData2("길동", 167, 0.5);
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(<길동,167,0.5>): result index = " + resultIndex);
		/*
		 교재 109~113
		 */
		key = new PhyscData2("박동", 167, 0.6);
		resultIndex = binarySearch(data, key);//comparable를 사용
		System.out.println("\nbinarySearch(<박동,167,0.6>): result index = " + resultIndex);
//		/*
//		 * 교재 115 Arrays.binarySearch에 의한 검색
//		 */
		key = new PhyscData2("나동", 164, 0.6);
//		resultIndex = Arrays.binarySearch(data, key);//comparable를 사용
//		System.out.println("\nArrays.binarySearch(<나동,164,0.6>): result index = " + resultIndex);
	}

	static void showData(String msg, PhyscData2[] data) {
		System.out.println(msg);
		for(PhyscData2 d : data) {
			System.out.println(d);
		}
	}
	
	static void sortData(PhyscData2[] data) {
		for(int i = 1; i < data.length; i++) {
			PhyscData2 current = data[i];
			int j;
			for(j = i; j > 0 && data[j - 1].compareTo(current) > 0; j--) {
				data[j] = data[j - 1];
			}
			data[j] = current;
		}
	}
	
	private static void reverse(PhyscData2[] data) {
		for(int i = 0; i < data.length / 2; i++) {
			PhyscData2 temp = data[i];
			data[i] = data[data.length - i - 1];
			data[data.length - i - 1] = temp;
		}
	}
	
	private static int linearSearch(PhyscData2[] data, PhyscData2 key) {
		for(int i = 0; i < data.length; i++) {
			if(data[i].compareTo(key) == 0) {
				return i;
			}
		}
		return -1;
	}
	
	private static int binarySearch(PhyscData2[] data, PhyscData2 key) {
		int pl = 0;
		int pr = data.length - 1;
		
		while(pl <= pr) {
			int pc = (pl + pr) / 2;
			if(data[pc].compareTo(key) == 0)
				return pc;
			else if(data[pc].compareTo(key) < 0)
				return pl = pc + 1;
			else
				pr = pc - 1;
		}
		return -1;
	}

}