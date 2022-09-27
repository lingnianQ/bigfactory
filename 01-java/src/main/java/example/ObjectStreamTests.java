package example;

import java.io.*;

/**
 * 序列化:将对象装换为字节或字符串(一般为json串)
 * 反序列化:将字节或字符串转换为对象
 */
class Point implements Serializable {
    private static final long serialVersionUID = -4279205237507458710L;
    private int x;
    private int y;
    public Point(){}
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
public class ObjectStreamTests {
    public static void main(String[] args) throws Exception{
        //1.对象序列化
        ObjectOutputStream out=
                new ObjectOutputStream(
                        new FileOutputStream("o1.txt"));
        out.writeObject(new Point(10,20));
        out.flush();
        out.close();
        //2.对象的反序列化
        ObjectInputStream in=
        new ObjectInputStream(new FileInputStream("o1.txt"));
        Object point=in.readObject();
        System.out.println(point);
        in.close();
    }
}
