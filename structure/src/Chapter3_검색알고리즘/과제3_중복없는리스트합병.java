package Chapter3_검색알고리즘;
/*
 * 3장 과제3 : 파일 리스트를 읽어 중복 제거후 2개의 리스트를 합병하여 정렬후 파일에 저장
 * file1: 서울,도쿄,북경,상해,서울,도쿄, 뉴욕,부산 , 상해,도쿄 ,  서울, 도쿄
 * file2: 런던, 로마,방콕, 도쿄,서울,부산
 * file > string split() > 배열 > ArrayList > sort > iterator 사용하여 merge > 중복 제거 > string 배열 > file에 저장
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class 과제3_중복없는리스트합병 {

	// 이진 검색 메서드
	static int binSearch(String[] list3, int size, String key) {		// 
		//자료구조 책 페이지 113 코드 사용한다.
		int pl = 0;
		int pr = size - 1;
		
		while(pl <= pr) {
			int pc = (pl + pr) / 2;		// 중앙 요소의 인덱스
			int cmp = key.compareTo(list3[pc]);		// 키와 중앙 요소 비교
			if (cmp == 0)		// 키와 일치하면 인덱스 반환
				return pc;
			else if (cmp < 0)		// 키가 중앙 요소보다 작으면 왼쪽 검색
				pr = pc - 1;		// 오른쪽 부분 버림
			else		// 키가 중앙 요소보다 크면 오른쪽 검색
				pl = pc + 1;		// 왼쪽 부분 버림		
		}
		
		return -1;
	}
	
	// 중복을 제거하는 메서드
	static ArrayList<String> removeDuplicate(ArrayList<String> list) {		// 중복 제거, List 객체를 매개변수로 받아 요소들의 중복을 제거한 후 리스트를 반환한다
		/*
		 * 구현할 부분 : 리스트에서 중복을 제거한다 - 배열로 변환하여 구현하는 것이 아님 
		 * 리스트를 정렬한후에 이 함수가 호출된다
		*/
		ArrayList<String> lst = new ArrayList<>();		// 중복 제거 후 저장할 리스트
		Iterator<String> it = list.iterator();		// 리스트의 반복자		
		String prev = null;		// 이전 값을 저장할 변수
		while(it.hasNext()) {		// 리스트의 모든 요소를 순회
			String current = it.next();		// 현재 요소
			if(!current.equals(prev))		// 현재 요소가 이전 요소와 다르면 리스트에 추가
				lst.add(current);
			prev = current;		// 현재 요소를 이전 요소로 업데이트
		}
		return lst;		// 중복 제거된 리스트 반환
	}

	// 두 개의 정렬된 리스트를 변환하는 메서드
	static List<String> mergeList(List<String> list1, List<String> list2) {	// 두 개의 List 객체를 매개변수로 받아 정렬된 List3 객체로 병합하여 반환
		/*
		 * list3 = merge(list1, list2);으로서 새로운 리스트에 정렬 값 순서로 merge하는 알고리즘 구현 
		 */
		
		ArrayList<String> list3 = new ArrayList<>();		// 정렬된 List를 병합하여 저장할 리스트
		// ------- ArrayList의 get()을 사용한 merge
		int i = 0, j = 0;		// 각 리스트의 Index
		while(i < list1.size() && j < list2.size()) {		// 두 리스트의 크기만큼 반복 
			if(list1.get(i).compareTo(list2.get(j)) < 0) {		// list1의 요소가 list2의 요소보다 더 작으면 list3에 추가, list1의 index를 증가
				list3.add(list1.get(i));
				i++;
			}
			else {
				list3.add(list2.get(j));		// list2의 요소가 list1의 요소보다 더 작으면 list3에 추가, list2의 index를 증가
				j++;
			}
		}
		
		// 각각 list1과 list2를 비교하여 정렬시킨 list3에 병합한 후 list에 남은 요소가 있을 경우 list3에 마저 추가  
		while(i < list1.size()) {
			list3.add(list1.get(i));
			i++;
		}
		
		while(j < list2.size()) {
			list3.add(list2.get(j));
			j++;
		}
		
		list3 = removeDuplicate(list3);		// 정렬 병합된 list3의 중복 요소 제거 
		
		return list3;
	}
	
	public static void main(String[] args) {
		try(FileInputStream fi = new FileInputStream("a1.txt")) {
			/*
			 * 자바 교재 547: 이클립스 > edu 프로젝트 - 마우스 우측 > New>File >a.txt 생성
			 * 입력 데이터를 다음과 같이 만든다:
			 *    file1: 서울,도쿄,북경,상해,서울,도쿄, 뉴욕,부산
			 *               상해,도쿄
			 *               서울, 도쿄
			 *    file2: 런던, 로마,방콕, 도쿄,서울,부산           
			 * 자바 교재 580: Path 클래스 - 파이썬 유사 
			 */
			
			// 파일을 읽어들여 바이트 배열로 저장
			Path input1 = Paths.get("a1.txt");		// 첫 번째 파일 경로
			byte[] bytes1 = Files.readAllBytes(input1);
			//readAllBytes: 파일의 모든 바이트를 읽어오는 메서드입니다. 
			//이 메서드는 파일을 열고 파일의 크기만큼 바이트를 읽어서 바이트 배열로 반환합니다.
			System.out.println("bytes[]의 길이 = "+bytes1.length);		// 바이트 배열의 길이 출력
			
			Path input2 = Paths.get("a2.txt");		// 두 번째 파일 경로
			byte[] bytes2 = Files.readAllBytes(input2);		// 두 번째 파일의 모든 바이트 읽기
			
			String s1 = new String(bytes1);		// 첫 번째 파일 내용을 문자열로 변환
			String s2 = new String(bytes2);		// 두 번째 파일 내용을 문자열로 변환
			System.out.println("\n입력 스트링: s1 = " + s1);
			System.out.println("\n입력 스트링: s2 = " + s2);
			String[] sarray1 = s1.split("[,\\s]+|\r\n");// [,\\s]+\r\n은 쉼표나 공백이 하나 이상 나오고 이어서 캐리지 리턴과 개행 문자가 있는 패턴으로 분리하여 배열로 변환
			String[] sarray2 = s2.split("[,\\s]+|\r\n");//file에서 enter키는 \r\n으로 해야 분리됨
			showData("\n스트링 배열 sarray1", sarray1);		// 첫 번째 배열 출력
			showData("\n스트링 배열 sarray2", sarray2);		// 두 번째 배열 출력

			trimSpace(sarray1);
			trimSpace(sarray2);

			showData("\ntrimSpace() 실행후 :스트링 배열 sarray1", sarray1);
			showData("\ntrimSpace() 실행후 :스트링 배열 sarray2", sarray2);
			
			System.out.println("\n" + "+".repeat(70) + "\n");
			
			// file1에서 read하여 list1.add()한다.
			// 배열을 list로 만드는 방법
			// 방법1:
			ArrayList<String> list1 = new ArrayList<>();		// 첫 번째 리스트 생성
			makeList(sarray1, list1);		// 첫 번째 배열을 리스트로 변환하여 추가
			showList("\n리스트1: ", list1);		// 첫 번째 리스트 출력
			// 방법2
			ArrayList<String> list2 = new ArrayList<>(Arrays.asList(sarray2));		// 두 번째 배열을 리스트로 변환하여 생성
			showList("\n리스트2: ", list2);		// 두 번째 리스트 출력
			
			System.out.println("\n+++++++++++++++++++++++++++ collection.sort()::++++++++++++++++++++++++++++++++++++++++++");
			
			Collections.sort(list1);
			showList("\n정렬후 리스트1: ", list1);

			//Arrays.sort(list2, null);//에러 발생 확인하고 이유는?
			Collections.sort(list2);
			showList("\n정렬후 리스트2: ", list2);	
	
			//정렬된 list에서 중복 제거 코드
			list1 = removeDuplicate(list1);
			list2 = removeDuplicate(list2);
			showList("\n중복 제거후 리스트1: ", list1);	
			showList("\n중복 제거후 리스트2: ", list2);	
	
			
			List<String> list3 = new ArrayList<>();
			
			// 방법3:
			list3 = mergeList(list1, list2);
			showList("\nmerge후 합병리스트: ", list3);	

			// ArrayList를 배열로 전환
			String[] st = list3.toArray(new String[list3.size()]);		// 병합된 리스트를 배열로 변환

			//binary search 구현
			String key = "부산";
			int index = binSearch(st, st.length, key);
			System.out.println("\n검색 결과: " + key + "의 Index = " + index);		// 검색 결과 출력

			// 정렬된 list3을 file에 출력하는 코드 완성
			System.out.println("\n" + "file에 출력:");
			
			int bufferSize = 10240;
			ByteBuffer buffer = ByteBuffer.allocate(bufferSize);		// 버퍼 할당
			writeFile(list3, buffer);		// 병합된 리스트를 파일에 쓰기
			
			FileOutputStream file = new FileOutputStream("c.txt");		// 파일 출력 스트림 생성
			FileChannel channel = file.getChannel();		// 파일 채널 생성
			channel.write(buffer);		// 버퍼 내용을 파일에 쓰기
			file.close();		// 파일 스트림 닫기
		} catch (IOException e) {		// 입출력 예외 처리
			e.printStackTrace();		// 예외 메시지 출력
		}
	}


	static void writeFile(List<String> list3, ByteBuffer buffer) {
		String b = " ";
		for (String sx : list3) {
			System.out.println(" " + sx);
			buffer.put(sx.getBytes());
			buffer.put(b.getBytes());
		}
		buffer.flip();
	}
	
	static void trimSpace(String[]arr) {
		/*
		 * string.trim() 사용으로 좌우 빈공백 제거
		 */
		for (int i = 0; i <arr.length; i++) {
			arr[i] = arr[i].trim();
		}
	}
	
	static void makeList(String[] sarray1, List<String>list1) {		// 매개변수로 sarray1 문자열 배열 객체를 list1 문자열 리스트 객체로 변환하는 메소드
		/*
		 * 배열을 list로 만드는 함수 구현 lst.add() 호출
		 */
		for (String s : sarray1) {		// 배열의 각 요소에 대해서 리스트에 추가
			list1.add(s);
		}
	}
	
	static void showData(String msg, String[] array) {		// 배열 출력 메서드
		System.out.println(msg);		// 메시지 출력
		for(String s : array)		// 배열의 각 요소에 대해서 출력
			System.out.print(s + " ");
		System.out.println();
	}
	
	private static void showList(String msg, List<String> list) {		// 리스트 출력 메서드
		System.out.println(msg);
		for(String s : list)		// 리스트의 각 요소에 대해서 출력
			System.out.print(s + " ");
		System.out.println();
	}
	
}
