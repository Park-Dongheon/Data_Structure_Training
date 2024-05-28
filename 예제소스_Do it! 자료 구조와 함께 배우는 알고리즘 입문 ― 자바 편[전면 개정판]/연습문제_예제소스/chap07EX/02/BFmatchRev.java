// 연습7-2
// 브루트-포스법에 의한 문자열검색(맨끝쪽부터 검색)

import java.util.Scanner;

class BFmatchRev {

	//--- 브루트-포스법에 의한 문자열검색(맨끝쪽부터 검색) ---//
	static int bfMatchR(String txt, String pat) {
		int pt = txt.length() - 1;		// txt 커서
		int pp = pat.length() - 1;		// pat 커서

		while (pt >= 0 && pp >= 0) {
			if (txt.charAt(pt) == pat.charAt(pp)) {
				pt--;
				pp--;
			} else {
				pt = pt + (pat.length() - pp) - 2;
				pp = pat.length() - 1;
			}
		}
		if (pp < 0)					// 검색 성공
			return pt + 1;
		return -1;					// 검색 실패
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("텍스트 : ");
		String s1 = stdIn.next(); 					// 텍스트용 문자열

		System.out.print("패  턴 : ");
		String s2 = stdIn.next();						// 패턴용 문자열

		int idx = bfMatchR(s1, s2);	// 문자열 s1에서 문자열 s2를 브루트-포스법으로 검색

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

