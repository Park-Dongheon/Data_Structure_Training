// 부분 문자열을 꺼냄

import java.util.Scanner;

class Substring {

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.print("문자열 s : ");  String s = stdIn.next();
        System.out.print("시작 인덱스 begin : ");  int begin = stdIn.nextInt();
        System.out.print("종료 인덱스 end   : ");  int end = stdIn.nextInt();

        System.out.println("s.substring(begin)      = " + s.substring(begin));
        System.out.println("s.substring(begin, end) = " + s.substring(begin, end));
    }
}
