package Chapter3_검색알고리즘;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

/*
 * 3장 과제2 : 객체 배열 정렬/검색 - 람다식 사용
 * 
 * Comparator를 사용하는 방법
 * MyComparator implements Comparator<>
 * MyComparator myComparator = new MyComparator();
 * 
 * Arrays.sort(array, myComparator);
 * Collections.sort(list, new MyComparator());
 */

class Fruit4 {
    String name;
    int price;
    String expire;

    public Fruit4(String name, int price, String expire) {
        this.name = name;
        this.price = price;
        this.expire = expire;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    
    public String getExpire() {
    	return expire;
    }

    @Override
    public String toString() {
        return "Fruit4{name='" + name + "', price=" + price + ", expire='" + expire + "'}";
    }

}

// 교재 123~129 페이지 참조하여 구현
class FruitName implements Comparator<Fruit4> {
    @Override
    public int compare(Fruit4 f1, Fruit4 f2) {
        return f1.getName().compareTo(f2.getName());
    }
}

class FruitPrice implements Comparator<Fruit4> {
    @Override
    public int compare(Fruit4 f1, Fruit4 f2) {
        return Integer.compare(f1.getPrice(), f2.getPrice());
    }
}

public class 과제2_실습3_7객체배열정렬검색_람다식 {

    private static void sortData(Fruit4[] arr, Comparator<Fruit4> cc_price) {
        Arrays.sort(arr, cc_price);
    }

    public static void main(String[] args) {

        Fruit4[] arr = {
        		new Fruit4("사과", 200, "2023-5-8"), new Fruit4("감", 500, "2023-6-8"),
                new Fruit4("대추", 200, "2023-7-8"), new Fruit4("복숭아", 50, "2023-5-18"),
                new Fruit4("수박", 880, "2023-5-28"), new Fruit4("산딸기", 10, "2023-9-8") };

        System.out.println("\n정렬 전 객체 배열: ");
        showData("정렬 전 객체", arr);

        // 이름으로 정렬하는 Comparator 사용
        FruitName cc = new FruitName();
        System.out.println("\nComparator cc 객체를 사용: ");
        Arrays.sort(arr, cc);
        showData("Arrays.sort(arr, cc) Name 정렬 후", arr);

        // 가격으로 정렬하는 Comparator 사용
        sortData(arr, new FruitPrice());
        showData("\nArrays.sort(arr, cc)  Price 실행 후", arr);

        // 람다식은 익명 클래스와 비슷하게 동작하지만 더 간결한 문법을 제공한다. 
        Arrays.sort(arr, (a, b) -> {
            try {
				return new SimpleDateFormat("yyyy-MM-dd").parse(a.getExpire()).compareTo(
				       new SimpleDateFormat("yyyy-MM-dd").parse(b.getExpire()) );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
        });
        showData("\n람다식 변수 cc_expire을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);

        // 람다식을 사용하여 가격으로 정렬
        Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice());
        showData("\n람다식: (a, b) -> a.getPrice() - b.getPrice()을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);

        // 익명 클래스를 사용하여 이름으로 정렬
        Arrays.sort(arr, new Comparator<Fruit4>() {
            @Override
            public int compare(Fruit4 a1, Fruit4 a2) {
                return a1.getName().compareTo(a2.getName());
            }
        });
        System.out.println("\n comparator 정렬(이름) 후 객체 배열: ");
        showData("\n name comparator - 익명 객체를 사용한 정렬:", arr);

        // 익명 클래스를 사용한 comparator 객체
        Comparator<Fruit4> cc_name = new Comparator<Fruit4>() {// 익명 클래스 사용

            @Override
            public int compare(Fruit4 f1, Fruit4 f2) {
                return f1.name.compareTo(f2.name);
            }

        };
        showData("\n name comparator - 익명 클래스를 사용한 정렬:", arr, cc_name);

        Comparator<Fruit4> cc_price = new Comparator<Fruit4>() {

            @Override
            public int compare(Fruit4 f1, Fruit4 f2) {
                return f1.getPrice() - f2.getPrice();
            }// 익명 클래스 사용

        };
        System.out.println("\n 익명 클래스 객체로 정렬(가격) 후 객체 배열: ");
        showData("price comparator - 익명 클래스를 사용한 정렬:", arr, cc_price);

        System.out.println();
        Fruit4 newFruit4 = new Fruit4("수박", 880, "2023-5-18");
        /*
         * 교재 115 Arrays.binarySearch에 의한 검색
         */
        // Arrays.binarySearch를 사용하여 이름으로 검색
        int result3Index = Arrays.binarySearch(arr, newFruit4, cc_name);
        System.out.println("\n Arrays.binarySearch([수박,880,2023-5-18]) 조회 결과: " + result3Index);

        // 커스텀 binarySearch 함수를 사용하여 가격으로 검색
        result3Index = binarySearch(arr, newFruit4, cc_price);//교재 113 binSearch() 함수를 고쳐서 구현 
        System.out.println("\n binarySearch([수박,880,2023-5-18]) 조회 결과: " + result3Index);

        sortData(arr, cc_price);
        System.out.println("\n comparator 정렬(가격) 후 객체 배열: ");
        showData("comparator를 사용한 정렬 후:", arr);
    }

	private static void showData(String msg, Fruit4[] arr) {
        System.out.println(msg);
        for (Fruit4 f : arr)
            System.out.print(f + " ");
    }
	
	private static void showData(String msg, Fruit4[] arr, Comparator<Fruit4> comparator) {
	    System.out.println(msg);
	    Arrays.sort(arr, comparator);
	    for (Fruit4 f : arr) {
	        System.out.print(f + " ");
	    }
	}

    private static int binarySearch(Fruit4[] arr, Fruit4 key, Comparator<Fruit4> c) {
        int pl = 0;
        int pr = arr.length - 1;

        while (pl <= pr) {
            int pc = (pl + pr) / 2;
            Fruit4 midF = arr[pc];
            int cmp = c.compare(midF, key);

            // 오른쪽 절반
            if (cmp < 0)
                pl = pc + 1;
            // 왼쪽 절반
            else if (cmp > 0)
                pr = pc - 1;
            else
                return pc;
        }
        return -(pl + 1);
    }

}
