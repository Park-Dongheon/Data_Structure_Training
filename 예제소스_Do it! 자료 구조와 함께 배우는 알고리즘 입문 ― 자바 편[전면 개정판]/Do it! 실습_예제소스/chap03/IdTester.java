// 아이디를 부여하는 클래스

class Id {
    private static int counter = 0;        //아이디를 몇 개 부여했는지 저장
    private int id;                        // 아이디

    //-- 생성자(constructor) --//
    public Id() { id = ++counter; }

    //--- counter를 반환하는 클래스 메서드(가장 큰 식별 번호를 얻음) ---//
    public static int getCounter() { return counter; }

    //--- 아이디를 반환하는 인스턴스 메서드(식별 번호를 얻음) ---//
    public int getId() { return id; }
}

public class IdTester {
    public static void main(String[] args) {
        Id a = new Id();        // 아이디1
        Id b = new Id();        // 아이디2

        System.out.println("a의 아이디: " + a.getId());
        System.out.println("b의 아이디: " + b.getId());

        System.out.println("부여한 아이디의 개수 = " + Id.getCounter());
    }
}