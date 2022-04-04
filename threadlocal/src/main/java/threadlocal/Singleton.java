package threadlocal;

public class Singleton {

    private static final ThreadLocal<Integer> num = new ThreadLocal<>();

    private Singleton(){}

    private static class SingletonHolder{
        public static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void setNum(int num){
        this.num.set(num);
    }

    public int getNum(){
        return num.get();
    }

    public void clearThreadLocal(){
        num.remove();
    }


}
