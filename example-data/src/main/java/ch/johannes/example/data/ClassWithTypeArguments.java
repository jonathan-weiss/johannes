package ch.johannes.example.data;

import java.util.List;

public class ClassWithTypeArguments extends ClassWithTypeParameters<String, Integer, Float> {

    public ClassWithTypeArguments(Integer i, String toPrint, String mySpecialFieldParam, List<Float> myList) {
        super(i, toPrint, mySpecialFieldParam, myList);
    }
}
