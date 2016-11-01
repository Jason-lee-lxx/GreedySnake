/**
 * Created by  johnj on 10/31/16.
 */

/*
Java 中的 enum 其实是继承自 Enum 的真是存在的类, 被 static final 修饰. 构造方法被 private 修饰, 防止在外部创建实例.
枚举实例都是都是真实的 enum类的实例, 也都用 static final 修饰, 是 enum类的静态常量.
 */

/**
 * 枚举类有一个name()方法，和toString()返回一样的值，所不同的是toString()可以被重载，而name()方法是final的，不能被重载
 * 枚举类还有一个valueOf()方法，这个方法和toString方法是相对应的，调用valueOf("UP")将返回Direction.UP。因此在重写toString()方法时，一般也要相应重写valueOf()方法。
 * ordinal()：返回枚举值在枚举类种的顺序，这个顺序根据枚举值声明的顺序而定，这里Direction.RIGHT.ordinal()返回1。
 * 枚举可以实现接口，但是不能继承，原因在于任何枚举已经继承自java.lang.Enum，而Java是不支持多继承的。
 */

public enum Direction {
    //调用构造函数创建了三个实例
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);

    //成员变量
    private final int directionCode;

    public int directionCode() {
        return directionCode;
    }

    //构造函数
    Direction(int directionCode) {
        this.directionCode = directionCode;
    }

    public boolean compatibleWith(Direction d) {
        int gap = Math.abs(directionCode - d.directionCode());
        return gap == 1 || gap == 3;
    }
}
