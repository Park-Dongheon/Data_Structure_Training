package Chap2_기본자료구조;

/*
 * 3번째 실습
 * 교재 83 - 배열 처리 + function parameter 전달 숙달 훈련 
 */

import java.util.Arrays;
import java.util.Random;
public class 실습2_6다차원배열 {

	public static void main(String[] args) {
		double [][]A = new double[2][3];
		double [][]B = new double[3][4];
		double [][]C = new double[2][4];

		inputData(A);inputData(B);
		double [][]D = A.clone();//교재83 - 배열 복제
	
		showData("A[2][3] = ",A);
		
		showData("D[2][3] = ",D);
	
		
		showData("B[3][4] = ",B);
		double [][]E = addMatrix(A,D);//행렬 덧셈
	
		showData("E[2][3] = ",E);
		C = multiplyMatrix(A,B);//행렬 곱셈
	
		showData("C[2][4] = ",C);

		double [][]F = transposeMatrix(A);//전치 행렬
		
		showData("F[3][2] = ",F);
		boolean result = equals(A, D);//행렬 동등 비교
		System.out.println(" equals(A,D) = " + result);
		
		System.out.println("F = " + Arrays.deepToString(F));//2차원 배열 처리 
	}
	inputData() {//double 난수 0.0 ~ 1.0 생성


	}
	static void showData(String msg, int[][]data) {//주어진 문자열을 출력하고 배열을 2차원 형태로 출력
		System.out.println(msg);
		for() {
			
		}
	}
	equals() {//두 행렬의 사이즈가 같고 값도 모두 같아야 행렬 2개는 equals을 true로 리턴


	}
	static double[][] addMatrix(double[][]A, double[][]B) {//행렬 덧셈 결과를 리턴
		double[][] C = new double[2][3];
		// 코딩
		return C;
	}
	multiplyMatrix() {//행렬 곱셈 결과를 리턴

		
	}
	transposeMatrix() {//전치 행렬을 리턴

		
	}
}

