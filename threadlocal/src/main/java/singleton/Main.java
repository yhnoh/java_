package singleton;

public class Main {

    Singleton instance = Singleton.getInstance();
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        for (int i = 0; i < 10; i ++){


            final int finalI = i;
            Thread t = new Thread(() -> {

                //변수를 공유
                instance.setNum(finalI);
                System.out.println("[Start Thread Num] = " + Thread.currentThread().getName() + " [Var Num] = " + instance.getNum());
                System.out.println("[End Thread Num] = " + Thread.currentThread().getName() + " [Var Num] = " + instance.getNum());

            }, "" + finalI);
            t.start();
        }
    }
}
