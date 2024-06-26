package Chapter6_정렬알고리즘;
/*
 * 6장 구현 실습과제1 
 */

class PhyscData implements Comparable<PhyscData> {
	String name; // 이름
	int height; // 키
	double vision; // 시력

	public PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	// 이름 → 키 → 시력 순서로 비교
	@Override
	public int compareTo(PhyscData p) {
		int nameComp = this.name.compareTo(p.name);
		int heightComp = Integer.compare(this.height, p.height);
		int visionComp = Double.compare(this.vision, p.vision);
		// 이름이 같을 경우 키를 비교
		if (nameComp == 0) {
			// 키도 같을 경우 시력 비교 결과를 반환
			if (heightComp == 0) {
				return visionComp;
			}
			// 이름이 같고, 키가 다를 경우 키 비교 결과를 반환
			return heightComp;
		}
		// 이름이 다를 경우 이름 비교 결과를 반환
		return nameComp;
	}

	@Override
	public String toString() {
		return "PhyscData [name=" + name + ", height=" + height + ", vision=" + vision + "]";
	}

}

public class 과제9_객체merge정렬 {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(PhyscData[] a, int lefta, int righta, int leftb, int rightb) {

		int size1 = righta - lefta + 1;
		int size2 = rightb - leftb + 1;

		PhyscData[] L = new PhyscData[size1];
		PhyscData[] R = new PhyscData[size2];

		for (int i = 0; i < size1; i++)
			L[i] = a[lefta + i];
		for (int j = 0; j < size2; j++)
			R[j] = a[leftb + j];

		int i = 0, j = 0;
		int k = lefta;

		while (i < size1 && j < size2) {
			if (L[i].compareTo(R[j]) <= 0) {
				a[k] = L[i];
				i++;
			} else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < size1) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < size2) {
			a[k] = R[j];
			j++;
			k++;
		}

	}

	// --- 퀵 정렬(비재귀 버전)---//, stack을 이용해 non-recursive 구현
	static void MergeSort(PhyscData[] a, int left, int right) {
		int mid = (left + right) / 2;
		if (left == right)
			return;
		MergeSort(a, left, mid);
		MergeSort(a, mid + 1, right);

		merge(a, left, mid, mid + 1, right);
	}

	public static void main(String[] args) {
		PhyscData[] x = { 
				new PhyscData("강민하", 162, 0.3), 
				new PhyscData("김찬우", 173, 0.7),
				new PhyscData("박준서", 171, 2.0), 
				new PhyscData("유서범", 171, 1.5), 
				new PhyscData("이수연", 168, 0.4),
				new PhyscData("장경오", 171, 1.2), 
				new PhyscData("황지안", 169, 0.8), 
				};
		int nx = x.length;

		MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬
		System.out.println("오름차순으로 정렬했습니다.");
		System.out.println("■ 신체검사 리스트 ■");
		System.out.println(" 이름     키  시력");
		System.out.println("------------------");
		for (PhyscData pd : x)
			System.out.printf("%-8s%3d%5.1f\n", pd.name, pd.height, pd.vision);
	}
}
