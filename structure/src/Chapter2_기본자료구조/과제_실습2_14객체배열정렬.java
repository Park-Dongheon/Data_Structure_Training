package Chapter2_기본자료구조;

import java.util.Objects;

//5번 실습 - 2장 실습 2-10를 수정하여 객체 배열의 정렬 구현, 클래스 PhyscData 사용
class PhyscData implements Comparable<PhyscData>{
	String name;
	int height;
	double vision;
	
	PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public String toString() {
		return "'" + name + "'" + ", " + height + ", " + vision;
	}
	
	@Override
	public int compareTo(PhyscData p) {
		//교재 p.123
		
		int compareName = this.name.compareTo(p.name);
		if(compareName != 0) return compareName;
		
		int compareHeight = Integer.compare(this.height, p.height);
		if(compareHeight != 0) return compareHeight;
		
		return Double.compare(this.vision, p.vision);
		
	}
	
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		PhyscData pd = (PhyscData) o;
		
		return height == pd.height && Objects.equals(vision, pd.vision) && Objects.equals(name, pd.name); 
	}

}

public class 과제_실습2_14객체배열정렬 {
	static void swap(PhyscData[] p, int idx1, int idx2) {
		PhyscData temp = p[idx1];
		p[idx1] = p[idx2];
		p[idx2] = temp;
	}
	
	static void sortData(PhyscData[] pd) {//객체 배열을 이름 순서로 정렬, 이름이 같으면 키 값을 정렬, 키 값이 같으면 시력으로 
		for(int i = 0; i < pd.length - 1; i++) {
			for(int j = pd.length - 1; j > i; j--) {
				if(pd[j - 1].compareTo(pd[j]) > 0) {
					swap(pd, j - 1, j);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		PhyscData[] data = {
				new PhyscData("홍길동", 162, 0.3),
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길동", 162, 0.7),
				new PhyscData("김홍길동", 172, 0.3),
				new PhyscData("이길동", 182, 0.6),
				new PhyscData("이길동", 167, 0.2),
				new PhyscData("최길동", 169, 0.5),
		};
		showData("정렬전", data);
		sortData(data);
		showData("정렬후", data);
		PhyscData[] newData= insertObject(data, new PhyscData("이기자", 179, 1.5));//배열의 사이즈를 1개 증가시킨후 insert되는 객체 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 객체 배열을 리턴
		showData("삽입후", newData);
		System.out.println("data와 newData와 비교 " + data.equals(newData));
	}
	
	static void showData(String msg, PhyscData[] pd) {
		System.out.println(msg);
		for(PhyscData p : pd) {
			System.out.println(p);
		}
	}
	
	static PhyscData[] insertObject(PhyscData[] pd, PhyscData p){//배열의 사이즈를 1개 증가시킨후 insert되는 스트링 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 스트링 배열을 리턴
		// 새로운 배열 생성 (크기는 기존 배열 크기 + 1)
		PhyscData[] newData = new PhyscData[pd.length + 1];
		
		int i;
		// 기존 배열의 요소를 새로운 배열로 복사 (삽입 위치 전까지)
		for(i = 0; pd.length > i && pd[i].compareTo(p) < 0; i++ ) {
			newData[i] = pd[i];
		}
		
		// 새로운 요소 삽입
		newData[i] = p;
		
		// 기존 배열의 나머지 요소를 새로운 배열로 복사 (삽입 위치 이후)
		for(int j = i; j < pd.length; j++) {
			newData[j + 1] = pd[j];
		}
		
		return newData;
	}

}
