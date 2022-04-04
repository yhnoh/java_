package singleton;

public class Singleton {

    private int num;

    private Singleton() {
    }

    private static class SingletonHolder {
        public static Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
