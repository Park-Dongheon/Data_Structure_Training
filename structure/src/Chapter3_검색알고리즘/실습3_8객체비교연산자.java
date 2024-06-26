package Chapter3_검색알고리즘;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 3장 4번 실습과제 - 객체 배열의 정렬과 이진검색 - Comparator 구현
 * 3장 실습 3-8를 수정하여 객체 배열의 정렬 구현
 */

/*
 * 객체 배열의 정렬과 이진 검색을 수행
 * 클래스의 인스턴스를 생성, Comparator 인터페이스와 compare 메서드를 클래스로 구현하여 인스턴스 객체 비교
 * 이름, 키, 시력에 따라 객체 배열을 정렬하고 이진 검색을 수행
 */

class PhyscData3 {		// 홍길동 구현 - 사수
	// 필드 정의
	private String name;
	private int height;
	private double vision;
	
	// 생성자 메소드: 객체 생성시 필드 초기화
	public PhyscData3(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	// getName(), getHeight(), getVision() 등의 접근자 메서드로 PhyscData3 부모클래스의 필드에 접근 
	public String getName() {
		return name;
	}

	public int getHeight() {
		return height;
	}

	public double getVision() {
		return vision;
	}

	@Override
	public String toString() {
		return "[" + name + "," + height + "," + vision + "]";
	}
}

/* 
 * Comparator<PhyscData3> 인터페이스를 구현
 * NameOrder, HeightOrder, VisionOrder 클래스가 객체를 (이름, 키, 시력)에 따라 비교하는 기준을 정의
 * compare() 메소드를 오버라이드하여 객체 비교하는 논리를 재정의
 */ 
class NameOrder implements Comparator<PhyscData3>{		// 이름 기준으로 정렬하는 클래스
	@Override
	public int compare(PhyscData3 p1, PhyscData3 p2) {		
		return p1.getName().compareTo(p2.getName());
	}
}

class HeightOrder implements Comparator<PhyscData3>{		// 키 기준으로 정렬하는 클래스
	@Override
	public int compare(PhyscData3 o1, PhyscData3 o2) {
		return Integer.compare(o1.getHeight(), o2.getHeight());
	}
}

class VisionOrder implements Comparator<PhyscData3>{		// 시력 기준으로 정렬하는 클래스
	@Override
	public int compare(PhyscData3 p1, PhyscData3 p2) {		
		//return (int)(p1.getVision() - p2.getVision());
		
		// 시력을 비교할 때 소수점까지 고려해야 하므로 Double.compare()를 사용
		return Double.compare(p1.getVision(), p2.getVision());
	}
}

public class 실습3_8객체비교연산자 {	
	static final Comparator<PhyscData3> HEIGHT_ORDER = new HeightOrder();

	public static void main(String[] args) {
		// 객체 배열 초기화
		PhyscData3[] data = {
				new PhyscData3("홍길동", 162, 0.3),
				new PhyscData3("나가자", 164, 1.3),
				new PhyscData3("다정해", 152, 0.7),
				new PhyscData3("소주다", 172, 0.4),
				new PhyscData3("사이다", 182, 0.6),
				new PhyscData3("신정신", 166, 1.2),
				new PhyscData3("이기자", 167, 1.5),
		};
		showData("정렬전 객체 배열", data);
		// Comparator 인터페이스를 구현한 클래스의 인스턴스 HEIGHT_ORDER를 두 번째 인수로 전달하여 정렬 기준을 지정
		Arrays.sort(data, HEIGHT_ORDER);	// 두 번째 변수가 객체
		showData("\nheight로 정렬후 객체 배열", data);
		
		PhyscData3 key = new PhyscData3("길동", 167, 0.2);		
		int idx = Arrays.binarySearch(data, key, HEIGHT_ORDER);		// 이진 검색을 수행, 정렬된 배열에서 원하는 값(key)를 찾음
		System.out.println("\nArrays.binarySearch(): result = " + idx);
		
		Arrays.sort(data, new VisionOrder() {	// 익명 객체 구현, 시력을 기준으로 정렬
			@Override
			public int compare(PhyscData3 p1, PhyscData3 p2) {
				return Double.compare(p1.getVision(), p2.getVision());
			}
		});
		showData("\nvision로 정렬후 객체 배열", data);
		
		Arrays.sort(data, new Comparator<PhyscData3>() {	// 익명 객체 구현, 이름을 기준으로 정렬
			@Override
			public int compare(PhyscData3 a1, PhyscData3 a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});
		showData("\nname로 정렬후 객체 배열", data);
	}

	// 데이터 출력 메소드
	private static void showData(String msg, PhyscData3[] data) {
		System.out.println(msg);
		for(PhyscData3 p3 : data) {
			System.out.println(p3);
		}
	}

}
