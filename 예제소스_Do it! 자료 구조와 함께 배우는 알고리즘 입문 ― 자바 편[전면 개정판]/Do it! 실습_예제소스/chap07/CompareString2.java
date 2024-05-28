// 문자열의 대소관계를 판단

import java.util.Scanner;

class CompareString2 {

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.print("문자열 s1 : ");  String s1 = stdIn.next();
        System.out.print("문자열 s2 : ");  String s2 = stdIn.next();

        int comp = s1.compareTo(s2);
        if (comp < 0)
            System.out.println("s1 < s2 입니다.");
        else if (comp > 0)
            System.out.println("s1 > s2 입니다.");
        else
            System.out.println("s1 == s2 입니다.");
    }
}
