package threadlocal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Thread> pool = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        Singleton instance = Singleton.getInstance();
        for (int i = 0; i < 10; i ++){

            final int finalI = i;
            Thread t = new Thread(() -> {

                //멀티 스레드 환경에서도, 스레드 로컬을 이용하여 변수를 공유하지 않는다.
                try{
                    instance.setNum(finalI);
                    System.out.println("[Start Thread Num] = " + Thread.currentThread().getName() + " [Var Num] = " + instance.getNum());
                    System.out.println("[End Thread Num] = " + Thread.currentThread().getName() + " [Var Num] = " + instance.getNum());
                }finally {
                    //instance.clearThreadLocal();
                }

            }, "" + finalI);
            t.start();
            t.join();

            pool.add(t);

        }

        for (Thread thread : pool) {
            thread.start();
            thread.join();
//            Thread t = new Thread(() -> {
//
//                //멀티 스레드 환경에서도, 스레드 로컬을 이용하여 변수를 공유하지 않는다.
//                try{
//                    instance.setNum(finalI);
//                    System.out.println("[Start Thread Num] = " + Thread.currentThread().getName() + " [Var Num] = " + instance.getNum());
//                    System.out.println("[End Thread Num] = " + Thread.currentThread().getName() + " [Var Num] = " + instance.getNum());
//                }finally {
//                    instance.clearThreadLocal();
//                }
//
//            }, "" + finalI);

        }
    }
}
