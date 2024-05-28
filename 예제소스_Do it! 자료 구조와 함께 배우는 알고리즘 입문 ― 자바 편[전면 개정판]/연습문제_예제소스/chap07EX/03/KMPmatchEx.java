// 연습7-3
// KMP법에 의한 문자열검색(대조 과정을 자세히 출력)

import java.util.Scanner;

class KMPmatchEx {

   //--- KMP법에 의한 문자열검색 ---//
   static int kmpMatch(String txt, String pat) {
      int pt = 1;										// txt 커서
      int pp = 0;										// pat 커서
      int count = 0;								// 비교 회수
      int[] skip = new int[pat.length() + 1];	// 건너뛰기 표
      int k = -1;

      // 건너뛰기표 작성
      System.out.println("건너뛰기표 작성");
      skip[pt] = 0;
      while (pt != pat.length()) {
         if (k == pt - pp)
            System.out.print("    ");
         else {
            System.out.printf("%2d  ", pt - pp);
            k = pt - pp;
         } 
         for (int i = 0; i < txt.length(); i++)
            System.out.print(txt.charAt(i) + " ");
         System.out.println();

         for (int i = 0; i < pt * 2 + 4; i++)
            System.out.print(" ");
         System.out.print(txt.charAt(pt) == pat.charAt(pp) ? '+' : '|');
         System.out.println();

         for (int i = 0; i < (pt-pp) * 2 + 4; i++)
            System.out.print(" ");

         for (int i = 0; i < pat.length(); i++)
            System.out.print(pat.charAt(i) + " ");
         System.out.println();
         System.out.println();
         count++;
         if (pat.charAt(pt) == pat.charAt(pp))
            skip[++pt] = ++pp;
         else if (pp == 0)
            skip[++pt] = pp;
         else
            pp = skip[pp];
      }

      // 검색
      System.out.println("검색");
      pt = pp = 0;
      while (pt != txt.length() && pp != pat.length()) {
         if (k == pt - pp)
            System.out.print("    ");
         else {
            System.out.printf("%2d  ", pt - pp);
            k = pt - pp;
         } 
         for (int i = 0; i < txt.length(); i++)
            System.out.print(txt.charAt(i) + " ");
         System.out.println();

         for (int i = 0; i < pt * 2 + 4; i++)
            System.out.print(" ");
         System.out.print(txt.charAt(pt) == pat.charAt(pp) ? '+' : '|');
         System.out.println();

         for (int i = 0; i < (pt-pp) * 2 + 4; i++)
            System.out.print(" ");

         for (int i = 0; i < pat.length(); i++)
            System.out.print(pat.charAt(i) + " ");
         System.out.println();
         System.out.println();
         count++;
         if (txt.charAt(pt) == pat.charAt(pp)) {
            pt++;
            pp++;
         } else if (pp == 0)
            pt++;
         else
            pp = skip[pp];
      }

      System.out.printf("비교를 %d회 했습니다.\n", count);
      if (pp == pat.length())		// 패턴의 모든 문자를 대조
         return pt - pp;
      return -1;					// 검색 실패
   }

   public static void main(String[] args) {
      Scanner stdIn = new Scanner(System.in);

  		System.out.print("텍스트 : ");
  		String s1 = stdIn.next(); 					// 텍스트용 문자열

  		System.out.print("패  턴 : ");
  		String s2 = stdIn.next();						// 패턴용 문자열

      int idx = kmpMatch(s1, s2);	// 문자열 s1에서 문자열 s2를 KMP법으로 검색

      if (idx == -1)
         System.out.println("텍스트에 패턴이 없습니다.");
      else {
         // 일치하는 문자 바로 앞까지의 문자 개수를 반각문자로 환산하여 구함
         int len = 0;
         for (int i = 0; i < idx; i++)
            len += s1.substring(i, i + 1).getBytes().length;
         len += s2.length();

         System.out.println((idx + 1) + "번째 문자부터 일치합니다.");
         System.out.println("텍스트 : " + s1);
         System.out.printf(String.format("패  턴 : %%%ds\n", len), s2);
      }
   }
}
