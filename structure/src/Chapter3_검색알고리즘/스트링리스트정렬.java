package Chapter3_검색알고리즘;

/*
 * 3장 5번 실습과제 - 스트링 리스트 정렬
 * 리스트를 배열로 변환후 중복제거후 다시 리스트 만들기 등 실습
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 스트링리스트정렬 {

	// 배열에서 특정 요소를 제거하는 메서드
    public static String[] removeElement1(String[] arr, String item) {
    	// 새로운 리스트를 생성, 주어진 요소를 제외한 모든 요소를 추가
    	List<String> removeList = new ArrayList<>();
    	for(String s : arr) {
    		if(!s.equals(item)) {
    			removeList.add(s);
    		}
    	}
    	// 리스트를 배열로 변환하여 반환
    	return removeList.toArray(new String[0]);
    }

	static void getList(List<String> list) {
		list.add("서울");list.add("북경");
		list.add("상해");list.add("서울");
		list.add("도쿄");list.add("뉴욕");

		list.add("런던");list.add("로마");
		list.add("방콕");list.add("북경");
		list.add("도쿄");list.add("서울");

		list.add(1, "LA");	// 인덱스 1에 "LA" 추가
	}

	// 리스트의 내용을 출력
	static void showList(String topic, List<String> list) {
		System.out.println(topic);
		for (String city : list) {
			System.out.print(city + " ");
		}
		System.out.println();
	}

	  // 리스트를 배열로 변환하여 정렬 후 다시 리스트로 변환
	static void sortList(List<String> list) {
		// 리스트를 배열로 변환
		String[] cityArray = list.toArray(new String[0]);
		
		// 배열을 정렬
		Arrays.sort(cityArray);
		
		// 정렬된 배열을 다시 리스트로 변환
		list.clear();
		list.addAll(Arrays.asList(cityArray));
	}

	// 리스트에서 중복을 제거한 후 배열로 반환
	static String[] removeDuplicateList(List<String> list) {
		// 중복 제거를 위해 리스트를 배열로 변환
		String cities[] = new String[list.size()];
		cities = list.toArray(cities);

		// 중복을 제거한 배열을 담을 리스트
		List<String> newList = new ArrayList<>();
		
		for(String city :  cities) {
			if(!newList.contains(city)) {
				newList.add(city);
			}
		}
		
		// 리스트를 배열로 변환하여 반환
		return newList.toArray(new String[0]);
	}

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		getList(list);

		// 오름차순으로 정렬, 내림차순으로 정렬
		showList("입력후", list);
		Collections.sort(list);
		showList("\n오름차순 정렬후", list);
		Collections.reverse(list);
		showList("\n내림차순 정렬후", list);

		// 리스트를 배열로 변환하여 정렬한 후 다시 리스트로 변환
		sortList(list);
	    showList("\n정렬후", list);

	    // 중복 제거 후 배열로 변환
		System.out.println();
		System.out.println("중복제거::");

		String[] cities = removeDuplicateList(list);
		// 중복 제거된 배열을 다시 리스트로 변환
		ArrayList<String> lst = new ArrayList<>(Arrays.asList(cities)); // 배열을 다시 리스트로 변환
		showList("중복제거후", lst);
	}

}
