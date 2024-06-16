package Chapter6_정렬알고리즘;

<<<<<<< HEAD
class Polynomial implements Comparable<Polynomial>{
	double coef;           // 계수
=======
class Term implements Comparable<Term>{
    double coef;           // 계수
>>>>>>> 3e18cfad28a97a9bb1fbfe9ca155806f33f2219e
    int    exp;            // 지수

    public Polynomial(double coef, int exp) {
    	this.coef = coef;
    	this.exp = exp;
    }

	@Override
	public int compareTo(Polynomial p) {
		// 지수를 기준으로 비교
		return Integer.compare(this.exp, p.exp);
	}

	@Override
	public String toString() {
		return "Polynomial (coef=" + coef + ", exp=" + exp + ")";
	}

}
public class 과제10_다항식merge연산 {

<<<<<<< HEAD
	static void merge(Polynomial[] a, int lefta, int righta, int leftb, int rightb ) {
		int size = rightb - lefta + 1;
		
		Polynomial[] temp = new Polynomial[size];
		
		int i = lefta, j = leftb, k = 0;
		
		while (i <= righta && j <= rightb) {
			if (a[i].compareTo(a[j]) <= 0) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
			}
		}
		
		while (i <= righta) 
			temp[k++] = a[i++];
		
		while (j <= rightb)
			temp[k++] = a[j++];
		
		for (i = 0; i < size; i++)
			a[lefta + i] = temp[i];
		
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Polynomial[] a, int left, int right) {
		int mid = (left+right)/2;			// 중간 인덱스 계산
		if (left == right) return;			// 배열이 하나의 원소만 남으면 정렬 종료
		MergeSort(a, left, mid);			// 왼쪽 부분 배열 정렬
		MergeSort(a, mid+1, right);			// 오른쪽 부분 배열 정렬
		merge(a, left, mid, mid+1, right);	// 정렬된 두 부분 배열 mertge 병합
=======
	static void merge(Term[] a, int lefta, int righta, int leftb, int rightb ) {

	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Term[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
>>>>>>> 3e18cfad28a97a9bb1fbfe9ca155806f33f2219e
	}

	public static void main(String[] args) {
		Term[] polynomialX = {
		         new Term(1.5, 3),
		         new Term(2.5, 7),
		         new Term(3.3, 2),
		         new Term(4.0, 1),
		         new Term(2.2, 0),
		         new Term(3.1, 4),
		         new Term(3.8, 5),
		     };
		Term[] polynomialY = {
		         new Term(1.5, 1),
		         new Term(2.5, 2),
		         new Term(3.3, 3),
		         new Term(4.0, 0),
		         new Term(2.2, 4),
		         new Term(3.1, 5),
		         new Term(3.8, 6),
		     };
		
		// 배열의 원소 개수
		int nx = x.length;

<<<<<<< HEAD
		// 초기 다항식 출력
=======
		// ex) f(x) = 5x**3  + 4x**2
>>>>>>> 3e18cfad28a97a9bb1fbfe9ca155806f33f2219e
		ShowPolynomial(x);
		ShowPolynomial(y);
		
		// 다항식 배열 정렬
		MergeSort(x, 0, x.length - 1); // 배열 x를 퀵정렬
		MergeSort(y, 0, y.length - 1); // 배열 x를 퀵정렬
		
		// 정렬된 다항식 출력
		ShowPolynomial(x);
		ShowPolynomial(y);
<<<<<<< HEAD
		
		// 덧셈과 곱셈 결과를 저장할 배열
		Polynomial[] z = new Polynomial[20];
=======
		Term[] polynomialZ = new Term[20];
>>>>>>> 3e18cfad28a97a9bb1fbfe9ca155806f33f2219e
		AddPolynomial(x,y,z);//다항식 덧셈 z = x + y
		ShowPolynomial(z);
		ShowPolynomial(y);
		MultiplyPolynomial(x,y,z);//다항식 곱셈 z = x * y
		ShowPolynomial(z);
		ShowPolynomial(y);
		int result = EvaluatePolynomial(z, 10);//다항식 값 계산 함수 z(10) 값 계산한다 
		System.out.println(" result = " + result );
	}

	static void ShowPolynomial(Polynomial[] x) {
		for (Polynomial p : x)
			if (p != null)
				System.out.print(p + " ");
		System.out.println();
	}

	static void AddPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {

	}
	
	static void MultiplyPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {

	}

	static int EvaluatePolynomial(Polynomial[] z, int x) {
		return result;
	}
	
}
