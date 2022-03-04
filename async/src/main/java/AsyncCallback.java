import java.nio.channels.CompletionHandler;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class AsyncCallback {

    //CompletionHandler : 비동기 결과값에 대한 정의를 내릴수 있고, completed, failed를 이용하여 성공과 실패에 대한 분기처리가 가능하다.
    //비동기 호출 이후 결과 값에 대한 정의를 할 수 있다.
    //completed, failed를 이용하여
    private static ExecutorService executorService;

    public static void main(String[] args) {
        executorService = Executors.newCachedThreadPool();


        completionHandler();
//        consumer();
//        future();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //함수형 인터페이스
    //Runnable : 인자와 리턴값이 모두 없다.
    //Supplier<R>, Callable<R> : 인자는 없고, R 타입의 객체를 리턴한다.
    //Consumer<T> : T타입의 인자를 받고, 마우것도 리턴하지 않는다.
    //Function<T, R> : T 타입의 인자를 받고, R 타입의 객체를 리턴한다.
    //@FuntionalInterface를 통해 커스텀 함수형 인터페이스를 제작할 수 있다.

    //Future 객체를 사용한 비동기 처리방식은 현재 현재 사용자가 정의한 콜백함수의
    //작업 완료 여부를 확인하고 (isDone, isCanceled)
    //작업이 완료될때 까지 블로킹 상태로 대기 시킬 수 있다. (get)
    public static void future(){

        Future<String> future = executorService.submit(() -> {
            log("작업 1 시작");
            sleep(2000);
            log("작업 1 종료");
            return "Future";
        });

        log("작업 2 시작 (작업 1 종료 대기)");
        String result = "";
        log("작업 1 종료 여부 : " + future.isDone());
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        log("작업 1 종료 여부 : " + future.isDone());

        log("작업 1의 결과 : " + result);
        log("작업 2 종료");

    }

    public static void consumer(){
        consumerExecutor(parameter -> {
            log("작업 2 시작 (작업 1의 결과 " + parameter+ ")");
            sleep(1000);
            log("작업 2 종료");
        });
    }
    //consumer 비동기 I/O 작업의 결과를 처리하기 위한 목적
    //인자를 넣어서 처리할 수있다.
    public static void consumerExecutor(Consumer<String> callback){
        executorService.submit(() -> {
            log("작업 1 시작");
            sleep(2000);
            log("작업 1 종료");
            String result = "Alice";

            callback.accept(result);
        });

       log("작업 3 시작");
       sleep(1000);
       log("작업 3 종료");

    }

    //CompletionHandler : 비동기 I/O 작업의 결과를 처리하기 위한 목적, completed, failed를 이용하여 성공과 실패에 대한 분기처리가 가능하다.
    //비동기 호출 이후 결과 값에 대한 정의를 할 수 있다.
    //completed, failed를 이용하여
    public static void completionHandlerExecute(CompletionHandler<String, Void> completionHandler){
        executorService.submit(() -> {
            log("작업 1 시작");
            sleep(2000);
            log("작업 1 종료");
            String result = "CompletionHandler";

            if(result.equals("Alice")){
                completionHandler.completed(result, null);
            }else{
                completionHandler.failed(new IllegalStateException(), null);
            }
        });
    }

    public static void completionHandler(){
        completionHandlerExecute(new CompletionHandler<String, Void>() {
            @Override
            public void completed(String result, Void attachment) {
                log("작업 2 시작 (작업 1의 결과 " + result+ ")");
                sleep(1000);
                log("작업 2 종료");

            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                log("작업 1 실패 : " + exc.toString());
            }
        });

       log("작업 3 시작");
       sleep(1000);
       log("작업 3 종료");

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
