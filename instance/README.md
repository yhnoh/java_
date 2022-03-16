# instance

## obj instanceof class vs class.isInstance(obj) vs class.isAssignableFrom(class)

### 1. obj instanceof class

- `B_Obj instanceof A`는 **인스턴스화(new)한 B객체(하위객체, 자기 자신 객체)가 A클래스(상위클래스, 자기 자신 클래스)로 인스턴스화**가 가능하냐를 묻는 것

```java
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
		}
}
```

### 2. class.isInstance(obj)

- `A.class.isInstance.(B_Obj)`는 **인스턴스화(new)한 B객체(하위객체)가 A클래스(상위클래스)로 인스턴스화**가 가능하냐를 묻는 것

```java
public class InstanceOf {
		public static class HighClass {
    }

    public static class LowClass extends HighClass {
    }

		public static void main(String[] args) {
		        HighClass highClass = new HighClass();  //상위
		        LowClass lowClass = new LowClass();     //하위
		
						System.out.println(HighClass.class.isInstance(lowClass));   //true
		        System.out.println(LowClass.class.isInstance(lowClass));    //true
		        System.out.println(LowClass.class.isInstance(highClass));   //false
		        System.out.println(HighClass.class.isInstance(highClass));  //true
		        System.out.println("====================================");
		        System.out.println(highClass.getClass().isInstance(lowClass));  //true
		        System.out.println(lowClass.getClass().isInstance(lowClass));   //true
		        System.out.println(lowClass.getClass().isInstance(highClass));  //false
		        System.out.println(highClass.getClass().isInstance(highClass)); //true
		}
}
```

### 3. class.isAssignableFrom(class)

`A.class.isAssignableFrom.(B.class)`는 **B클래스(하위클래스)가 A클래스(상위클래스)로 할당이 가능하냐**를 묻는 것

```java
public class InstanceOf {
		public static class HighClass {
    }

    public static class LowClass extends HighClass {
    }

		public static void main(String[] args) {
		        HighClass highClass = new HighClass();  //상위
		        LowClass lowClass = new LowClass();     //하위
		
						System.out.println(HighClass.class.isAssignableFrom(LowClass.class));   //true
		        System.out.println(LowClass.class.isAssignableFrom(LowClass.class));    //true
		        System.out.println(LowClass.class.isAssignableFrom(HighClass.class));   //false
		        System.out.println(HighClass.class.isAssignableFrom(HighClass.class));  //true
		        System.out.println("====================================");
		        System.out.println(highClass.getClass().isAssignableFrom(LowClass.class));  //true
		        System.out.println(lowClass.getClass().isAssignableFrom(LowClass.class));   //true
		        System.out.println(lowClass.getClass().isAssignableFrom(HighClass.class));  //false
		        System.out.println(highClass.getClass().isAssignableFrom(HighClass.class)); //true
		}
}
```