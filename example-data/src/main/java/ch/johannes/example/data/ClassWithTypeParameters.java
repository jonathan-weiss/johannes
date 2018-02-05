package ch.johannes.example.data;

import java.util.List;

public class ClassWithTypeParameters<Y, Q extends Integer & Comparable<Integer>, Z> {

    private List<? super Float> myFloatingSuperField;

    private Y mySpecialField;

    public <T> ClassWithTypeParameters(Integer i, T toPrint, Y mySpecialFieldParam, List<Z> myList) {
        this.mySpecialField = mySpecialFieldParam;
        print(toPrint);

    }

    public <A> A doSomething(int i, Integer ix, A aix) {
        return aix;
    }

    public <T> String print(T toPrint) {
        System.out.println(toPrint);
        System.out.println(this.mySpecialField);
        return toPrint.toString();
    }

    public static void start() {
        ClassWithTypeParameters tc = new ClassWithTypeParameters(2, "hallo", 23, null);
        tc.print("hallo");
    }

    public static void main(String [] args) {
        start();
    }
}
