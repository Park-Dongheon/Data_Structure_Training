// 입력한 정숫값을 판단(2)(2의 본모습)

import java.util.Scanner;

class JudgeABC2x {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.print("정수를 입력하세요.: ");
        int n = stdIn.nextInt();

        if (n == 1)
            System.out.println("Ａ");
        else if (n == 2)
            System.out.println("Ｂ");
        else if (n == 3)
            System.out.println("Ｃ");
        else
            ;
    }
}