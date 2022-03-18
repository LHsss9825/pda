/**
 * @author ：lh
 * @date ：Created in 2022/3/17 10:43
 */
public class Test {
    public static void main(String[] args) {
        A ab= new B();
        System.out.println();
        ab=new B();
    }
}
class A{
    static {
        System.out.println("1");
    }
    public A(){
        System.out.println("2");
    }
}
class B extends A{
    static {
        System.out.println("a");
    }
    public B(){
        System.out.println("b");

    }
    public class Hello{

    }
}
