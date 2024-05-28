// 달력의 배열을 정렬

import java.util.Arrays;
import java.util.GregorianCalendar;
import static java.util.GregorianCalendar.*;

class SortCalendar {

    public static void main(String[] args) {
        GregorianCalendar[] x = {
            new GregorianCalendar(2022, NOVEMBER, 1),        // 2022年11月01日
            new GregorianCalendar(1963, OCTOBER, 18),        // 1963年10月18日
            new GregorianCalendar(1985, APRIL, 5),           // 1985年04月05日
        };

        Arrays.sort(x);  // 배열 x를 정렬

        for (int i = 0; i < x.length; i++)
            System.out.printf("%04d년%02d월%02d일\n", 
              x[i].get(YEAR),
              x[i].get(MONTH) + 1,
              x[i].get(DATE));
    }
}