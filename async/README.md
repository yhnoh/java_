# 동기,비동기,블로킹,논블로킹

[Notion](https://superb-ermine-a50.notion.site/11ba6766cd4c446c8c1a6dd314fd0d19)

### 시작하기 전

<aside>
📌 *블로킹과 논블로킹, 비동기와 동기는 비슷한 개념 같지만 바라보는 시점에 따라서 그 의미가 달라진다.*

</aside>

- 제어권
    - 제어권은 자신(함수)의 코드를 실행할 권리 같은 것이다. 제어권을 가진 함수는 자신의 코드를 끝까지 실행한 후, 자신을 호출한 함수에게 돌려준다.
- 결과값을 기다린다는 것
    - A 함수에서 B 함수를 호출했을 때, A 함수가 B 함수의 결과값을 기다리느냐 마냐의 여부를 의미하다.

### 블로킹/논블로킹

- 제어권을 어느쪽에서 가지고 잇는지에 따라 나뉜다.


- **블로킹**
    - 대상의 작업이 끝날때 까지 기다린다.
    - A함수가 B함수를 호출하는 순간, B함수에게 제어권을 넘겨주고, B함수의 실행이 끝나면, 다시 A 함수에게 제어권을 넘겨준다.
    

- **논블로킹**
    - 대상의 작업처리 여부와 상관없이 자신의 작업을 진행
    - A함수가 B함수를 호출하면, B 함수는 실행되지만, **제어권은 A함수가 그대로 가지고 있는다.** 즉, B함수가 호출되어도 A함수는 자신의 코드를 수행한다.
    

### 동기/비동기

- 호출되는 함수의 작업 완료 여부를 신경 쓰는지의 여부의 차이
- **동기**
    - 함수 A가 함수 B를 호출한 뒤, 함수 B의 리턴을 기다리는 것이 동기이다.
- **비동기**
    - 함수 A가 함수 B를 호출할 때 **콜백 함수를 함께 전달**해서, **함수 B의 작업이 완료되면 함께 보낸 콜백 함수를 실행**
    - 함수 A는 함수 B를 호출한 후로 함수 B의 작업 완료 여부에는 신경안쓴다.
    

### 동기/블로킹

```java

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
```

```
main> 실행
main> 작업 1 시작
main> 작업 1 종료
main> 작업 2 시작
main> 작업 2 종료
main> 종료
```

- work1에게 제어권을 넘겨주고  결과를 리턴 받고난 뒤, work2에게 제어권을 넘겨주고 결과를 리턴 받는다.
- work1과 work2가 만약에 서로 관련이 없는 작업 내용이면, 비효율적인 프로그램이 될 수 있다.

```java
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
```

```
main> 실행
pool-1-thread-1> 작업 1 시작
main> 종료
pool-1-thread-2> 작업 2 시작
pool-1-thread-1> 작업 1 종료
pool-1-thread-2> 작업 2 종료
```

- main은 work1과 work2의 완료 여부와 상관없이 계속 해서 실행한다.
- work1과 work2 `executorService.submit` 에 콜백 함수에 결과를 정의하였다.
- 다양한 콜백함수들이 존재한다. [AsyncCallback.java](https://github.com/yhnoh/java/blob/master/async/src/main/java/AsyncCallback.java) 참고


> [https://velog.io/@nittre/블로킹-Vs.-논블로킹-동기-Vs.-비동기](https://velog.io/@nittre/%EB%B8%94%EB%A1%9C%ED%82%B9-Vs.-%EB%85%BC%EB%B8%94%EB%A1%9C%ED%82%B9-%EB%8F%99%EA%B8%B0-Vs.-%EB%B9%84%EB%8F%99%EA%B8%B0)

> [https://velog.io/@pllap/Java에서의-비동기-프로그래밍](https://velog.io/@pllap/Java%EC%97%90%EC%84%9C%EC%9D%98-%EB%B9%84%EB%8F%99%EA%B8%B0-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D)
 
> [https://codechacha.com/ko/java-executors/](https://codechacha.com/ko/java-executors/)