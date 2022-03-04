public class Sync {

    //두 작업 사이에는 연관성이 없는데 작업1을 기다리고 작업 2가 실행된다.
    //이럴 경우 스레드를 나누어 오래 걸리는 작업을 다른 주체에게 맡겨 다른 작업과 동시에
    //실행되게 만들면 시간을 아낄 수 있다.
    public static void main(String[] args) {
        log("실행");
        work1();
        work2();
        log("종료");
    }
    private static void work1(){
        log("작업 1 시작");
        sleep(1000);
        log("작업 1 종료");
    }

    private static void work2(){
        log("작업 2 시작");
        sleep(1000);
        log("작업 2 종료");
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
