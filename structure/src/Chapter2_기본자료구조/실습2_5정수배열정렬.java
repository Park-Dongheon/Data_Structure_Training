package Chapter2_기본자료구조;

import java.util.Arrays;
//교재 67 - 실습 2-5
//2번 실습
import java.util.Random;
public class 실습2_5정수배열정렬 {
	static int top = 0;
	static final int MAX_LENGTH = 20;
	
	public static void main(String[] args) {
		float []data = new float[MAX_LENGTH];// 0.0 ~ 1.0 사이의 실수를 저장
		inputData(data, 10);//10개의 난수를 입력
		showData(data);//top 개수 만큼 출력
		
		reverse(data);//역순으로 재배치 - 교재 67페이지 참조
		System.out.println(Arrays.toString(data));// 교재 84페이지 코드 참조, Array 컬렉션
				
		sortData(data);//교재 205 bubbleSort() 함수 코드를 사용 - 올림차순으로 정렬
		showData(data);
		
		Random rnd = new Random();
		float realData = rnd.nextFloat() * 100;	// 실수 난수 생성;
		System.out.println("난수 data stack에 insert: " + realData);
		insertData(data, realData);//정렬된 배열에 삽입
		showData(data);
	}
	
	static void showData(float[] data) {//실수 배열을 top 갯수만 출력
		for(int i = 0; i < top; i++) {
			System.out.println("data stack: " + data[i] + " ");
		}
	}
	
	static void inputData(float[] data, int count) {
		//난수 실수를 입력
		Random rnd = new Random();
		
		for(int i = 0; i < count; i++) {
			data[top + i] = rnd.nextFloat() * 100;
		} 
		top += count;
	}
	
	static void reverse(float[] data) {//역순으로 재배치, length -> top 으로 변경
//		float[] temp = new float[MAX_LENGTH];
//		
//		for(int i = 0; i < top; i++) {
//			temp[i] = data[i];
//		}
//		
//		for(int i = 0; i < top; i++) {
//			data[i] = temp[top-i-1];
//			System.out.println("data stack-Reverse: " + data[i]);
//		}
		
		for(int i = 0; i < top / 2; i++) {
			swap(data, i, top - i -1);
		}
	}
	
	static void swap(float[] data, int idx1, int idx2) {//교재 67페이지 - 맞교환
		float temp = data[idx1];
		data[idx1] = data[idx2];
		data[idx2] = temp;
	}
	
	static void sortData(float[] data) {//올림차순으로 정렬
		for(int i = 0; i < top; i++) {
			for(int j = top-1; j > i; j--) {
				if(data[j - 1] > data[j]) {
					swap(data, j - 1, j);
				}
			}
		}
	}
	
	static void insertData(float[] data, float realData) {	//insert되는 실수 값이 insert될 위치를 찾아 보다 큰 값은 우측으로 이동
		int i;
		for(i = top-1; i >= 0 && data[i] > realData; i--) {
			data[i + 1] = data[i];	// 삽입할 공간 확보를 위해 오른쪽으로 이동
		}
		data[i + 1] = realData;
		top++;	
	}
	
}
