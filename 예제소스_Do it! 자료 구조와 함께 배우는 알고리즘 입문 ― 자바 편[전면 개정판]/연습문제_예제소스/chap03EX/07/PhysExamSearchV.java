// 연습3-7
// 신체 검사 데이터 배열에서 검색(시력)

import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

class PhysExamSearchV {

	//--- 신체 검사 데이터 ---//
	static class PhyscData {
		private String name;	// 이름
		private int    height;		// 키
		private double vision;	//시력

		//--- 생성자 ---//
		public PhyscData(String name, int height, double vision) {
			this.name = name;  this.height = height;  this.vision = vision;
		}

		//--- 문자열 만들기 --//
		public String toString() {
			return name + " " + height + " " + vision;
		}

		//---시력 내림차순용 컴퍼레이터 ---//
		public static final Comparator<PhyscData> VISION_ORDER =
															new VisionOrderComparator();

		private static class VisionOrderComparator
													 implements Comparator<PhyscData> {
			public int compare(PhyscData d1, PhyscData d2) {
				return (d1.vision > d2.vision) ?  1 :
							 (d1.vision < d2.vision) ? -1 : 0;
			}
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		PhyscData[] x = {					// 배열 요소는 시력 오름차순으로 정렬
			new PhyscData("강민하", 162, 0.3),
			new PhyscData("이수연", 168, 0.4),
			new PhyscData("김찬우", 173, 0.7),
			new PhyscData("황지안", 169, 0.8),
			new PhyscData("장경오", 174, 1.2),
			new PhyscData("유서범", 171, 1.5),
			new PhyscData("박준서", 175, 2.0),
		};
		System.out.print("검색할 시력은?  : ");
		double vision = stdIn.nextDouble();			   // 키값을 입력받음
		int idx = Arrays.binarySearch(
						x,							     // 배열 x에서 
						new PhyscData("", 0, vision), 	// 키가 vision인 요소를 
						PhyscData.VISION_ORDER					// VISION_ORDER를 사용하여 검색
					 );

		if (idx < 0)
			System.out.println("그 값의 요소는 존재하지 않습니다.");
		else {
			System.out.println("그 값은 " + "x[" + idx + "]에 있습니다.");
			System.out.println("데이터 : " + x[idx]);
		}
	}
}

