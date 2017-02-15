package ch.johannes.annotations.examples;

import ch.johannes.annotations.MyDiscoveredAnnotation;

@MyDiscoveredAnnotation(type=String.class, id="myId")
public class MyClassWithAnnotation {

    public MyClassWithAnnotation() {

    }

    public String doReturn(String input) {
        return "halloe " + input;
    }

}
