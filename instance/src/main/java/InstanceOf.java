public class InstanceOf {

    public static class HighClass {
    }

    public static class LowClass extends HighClass {
    }

    public static void main(String[] args) {
        HighClass highClass = new HighClass();  //상위
        LowClass lowClass = new LowClass();     //하위

        System.out.println(lowClass instanceof HighClass);  //true
        System.out.println(lowClass instanceof LowClass);   //true
        System.out.println(highClass instanceof LowClass);  //false
        System.out.println(highClass instanceof HighClass); //true
        System.out.println("====================================");
        System.out.println(HighClass.class.isInstance(lowClass));   //true
        System.out.println(LowClass.class.isInstance(lowClass));    //true
        System.out.println(LowClass.class.isInstance(highClass));   //false
        System.out.println(HighClass.class.isInstance(highClass));  //true
        System.out.println("====================================");
        System.out.println(highClass.getClass().isInstance(lowClass));  //true
        System.out.println(lowClass.getClass().isInstance(lowClass));   //true
        System.out.println(lowClass.getClass().isInstance(highClass));  //false
        System.out.println(highClass.getClass().isInstance(highClass)); //true
        System.out.println("====================================");
        System.out.println(HighClass.class.isAssignableFrom(LowClass.class));   //true
        System.out.println(LowClass.class.isAssignableFrom(LowClass.class));    //true
        System.out.println(LowClass.class.isAssignableFrom(HighClass.class));   //false
        System.out.println(HighClass.class.isAssignableFrom(HighClass.class));  //true
        System.out.println("====================================");
        System.out.println(highClass.getClass().isAssignableFrom(LowClass.class));  //true
        System.out.println(lowClass.getClass().isAssignableFrom(LowClass.class));   //true
        System.out.println(lowClass.getClass().isAssignableFrom(HighClass.class));  //false
        System.out.println(highClass.getClass().isAssignableFrom(HighClass.class)); //true
        System.out.println("====================================");

    }
}
