import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Async {

    /**
     * 실행 결과
     * main> 실행
     * pool-1-thread-1> 작업 1 시작
     * main> 종료
     * pool-1-thread-2> 작업 2 시작
     * pool-1-thread-1> 작업 1 종료
     * pool-1-thread-2> 작업 2 종료
     *
     *
     * 작업1과 2를 다른 스레드에게 맡기고 메인 스레드는 그대로 밑으로 내려가 작업을 진행한다.
     *
     */
    private static ExecutorService executorService;
    public static void main(String[] args) {
        executorService = Executors.newCachedThreadPool();
        log("실행");
        work1();
        work2();
        log("종료");

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   //작업 1 (스레드)
    private static void work1(){
        executorService.submit(() -> {
            log("작업 1 시작");
            sleep(1000);
            log("작업 1 종료");
        });

    }

    //작업 2 (스레드)
    private static void work2(){
        executorService.submit(() -> {
            log("작업 2 시작");
            sleep(1000);
            log("작업 2 종료");
        });
    }

    private static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void log(String content){
        System.out.println(Thread.currentThread().getName() + "> " + content);
    }

}
