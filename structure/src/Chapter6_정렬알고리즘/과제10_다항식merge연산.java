package Chapter6_정렬알고리즘;

//Term 클래스는 다항식의 항을 나타냅니다. Comparable 인터페이스를 구현하여 지수(exp) 기준으로 비교
class Term implements Comparable<Term> {
	double coef; 	// 계수 (coefficient)
	int exp; 		// 지수 (exponent)
	String var; 	// 변수명 (variable)

	// 생성자: 계수와 지수를 받아 초기화
	public Term(double coef, int exp) {
		this.coef = coef;
		this.exp = exp;
		this.var = ""; // 초기에는 빈 문자열로 설정
	}

	// compareTo 메서드: 지수를 기준으로 비교
	@Override
	public int compareTo(Term p) {
		return Integer.compare(this.exp, p.exp);
	}

	// toString 메서드: Term 객체를 문자열로 변환하여 반환
	@Override
	public String toString() {
		return "f(" + var + ") = " + coef + var + "**" + exp + " ";
	}
	
	// setVariable 메서드: 변수명을 설정
	public void setVariable(String var) {
        this.var = var;
    }

}

// 다항식의 병합 정렬, 덧셈, 곱셈, 평가를 수행
public class 과제10_다항식merge연산 {
	
	// merge 메서드: 두 부분 배열을 병합
	static void merge(Term[] a, int lefta, int righta, int leftb, int rightb) {
		// 병합할 두 배열의 총 크기를 계산
		int size = rightb - lefta + 1;

		// 임시 배열을 생성하여 병합된 결과를 저장
		Term[] temp = new Term[size];

		// 두 부분 배열의 시작 인덱스
		int i = lefta, j = leftb, k = 0;

		// 두 부분 배열을 비교하여 작은 값을 임시 배열에 추가
		while (i <= righta && j <= rightb) {
			// a[i]가 a[j]보다 작거나 같으면 a[i]를 temp에 추가하고 i를 증가
			if (a[i].compareTo(a[j]) <= 0) {
				temp[k++] = a[i++];
			}
			// a[j]가 a[i]보다 작으면 a[j]를 temp에 추가하고 j를 증가
			else {				
				temp[k++] = a[j++];
			}
		}

		// 첫 번째 부분 배열에 남은 요소들을 임시 배열에 추가
		while (i <= righta)
			temp[k++] = a[i++];

		// 두 번째 부분 배열에 남은 요소들을 임시 배열에 추가
		while (j <= rightb)
			temp[k++] = a[j++];

		// 병합된 결과를 원래 배열에 복사
		for (i = 0; i < size; i++)
			a[lefta + i] = temp[i];

	}

	// --- 퀵 정렬(비재귀 버전)---//
	// MergeSort 메서드: 병합 정렬을 수행
	static void MergeSort(Term[] a, int left, int right) {
		int mid = (left + right) / 2; // 중간 인덱스 계산
		if (left == right)
			return; // 배열이 하나의 원소만 남으면 정렬 종료
		MergeSort(a, left, mid); // 왼쪽 부분 배열 정렬
		MergeSort(a, mid + 1, right); // 오른쪽 부분 배열 정렬
		merge(a, left, mid, mid + 1, right); // 정렬된 두 부분 배열 mertge 병합
		return;
	}

	public static void main(String[] args) {
		Term[] termX = { 
				new Term(1.5, 3), 
				new Term(2.5, 7), 
				new Term(3.3, 2), 
				new Term(4.0, 1), 
				new Term(2.2, 0),
				new Term(3.1, 4), 
				new Term(3.8, 5), 
				};
		
		Term[] termY = { 
				new Term(1.5, 1), 
				new Term(2.5, 2), 
				new Term(3.3, 3), 
				new Term(4.0, 0), 
				new Term(2.2, 4),
				new Term(3.1, 5), 
				new Term(3.8, 6), 
				};

		// 초기 다항식 출력
		// ex) f(x) = 5x**3 + 4x**2
		System.out.println("----------------------------초기다항식 배열-----------------------------");
		for (Term term : termX) {
            term.setVariable("X");
        }
        for (Term term : termY) {
            term.setVariable("Y");
        }
		ShowTerm(termX);
		ShowTerm(termY);

		// 다항식 배열 정렬
		MergeSort(termX, 0, termX.length - 1); // 배열 x를 퀵정렬
		MergeSort(termY, 0, termY.length - 1); // 배열 x를 퀵정렬

		// 정렬된 다항식 출력
		System.out.println("\n---------------------------정렬된 다항식 배열---------------------------");
		ShowTerm(termX);
		ShowTerm(termY);

		// 덧셈과 곱셈 결과를 저장할 배열
		Term[] termZ = new Term[20];

		// 다항식 덧셈 z = x + y
		System.out.println("\n------------------------------다항식 덧셈-------------------------------");
		AddTerm(termX, termY, termZ);
		for (Term term : termZ) {
            if (term != null) {
                term.setVariable("Z");
            }
        }
		ShowTerm(termZ);

		// 다항식 곱셈 z = x * y
		System.out.println("\n------------------------------다항식 곱셈-------------------------------");
		MultiplyTerm(termX, termY, termZ);
		for (Term term : termZ) {
            if (term != null) {
                term.setVariable("Z");
            }
        }
		ShowTerm(termZ);

		// 다항식 값 계산 함수 z(10) 값 계산한다
		System.out.println("\n--------------------------다항식 값 계산 f(10)---------------------------");
		int result = EvaluateTerm(termZ, 10);
		System.out.println(" result = " + result);
	}

	// 다항식을 출력하는 메서드
	static void ShowTerm(Term[] x) {
		for (Term p : x)
			if (p != null)
				System.out.print(p + " ");
		System.out.println();
	}

	// AddTerm 메서드: 두 다항식을 더하는 메서드
	static void AddTerm(Term[] x, Term[] y, Term[] z) {
		int i = 0, j = 0, k = 0;
		
		// 두 다항식의 항을 비교하면서 z 배열에 추가
		while (i < x.length && j < y.length) {
			// x와 y의 항이 모두 null이면 종료
			if (x[i] == null && y[j] == null)
				break;

			// x[i]가 null이거나 y[j]가 x[i]보다 작으면 y[j]를 z에 추가
			if (x[i] == null || (y[j] != null && y[j].compareTo(x[i]) < 0))
				z[k++] = y[j++];
			// y[j]가 null이거나 x[i]가 y[j]보다 작으면 x[i]를 z에 추가
			else if (y[i] == null || (x[j] != null && x[i].compareTo(y[j]) < 0))
				z[k++] = x[i++];
			// x[i]와 y[j]의 지수가 같으면 두 항을 더해서 z에 추가
			else {
				z[k] = new Term(x[i].coef + y[j].coef, x[i].exp);
				i++;
				j++;
				k++;
			}
		}

		// 남아 있는 x의 항을 z에 추가
		while (i < x.length && x[i] != null) {
			z[k++] = x[i++];
		}

		// 남아 있는 y의 항을 z에 추가
		while (j < y.length && y[j] != null) {
			z[k++] = y[j++];
		}

	}

	// 다항식 곱셈 메서드
	static void MultiplyTerm(Term[] x, Term[] y, Term[] z) {
		// z 배열 초기화
		for (int i = 0; i < z.length; i++)
			z[i] = null;

		// x와 y 배열의 모든 항을 곱
		for (int i = 0; i < x.length; i++) {
			if (x[i] == null)
				continue;
			for (int j = 0; j < y.length; j++) {
				if (y[j] == null)
					continue;

				int exp = x[i].exp + y[j].exp; // 지수 더하기
				double coef = x[i].coef * y[j].coef; // 계수 곱하기

				boolean found = false;
				// z 배열에서 동일한 지수 찾기
				for (int k = 0; k < z.length; k++) {
					if (z[k] != null && z[k].exp == exp) {
						z[k].coef += coef; // 동일한 지수가 있으면 계수를 더함
						found = true;
						break;
					}
				}

				// 동일한 지수가 없으면 새로운 항을 추가
				if (!found) {
					for (int k = 0; k < z.length; k++) {
						if (z[k] == null) {
							z[k] = new Term(coef, exp); // 새로운 항을 추가
							break;
						}
					}
				}

			}
		}

	}

	// 다항식을 특정 값에서 평가하는 메서드
	static int EvaluateTerm(Term[] z, int x) {
		int result = 0; // 결과를 저장할 변수 초기화

		// z 배열의 각 항을 순회
		for (Term t : z) {
			if (t != null)
				result += t.coef * Math.pow(x, t.exp); // 각 항의 값을 계산하여 결과에 더함
		}

		return result; // 계산된 결과 반환
	}

}
