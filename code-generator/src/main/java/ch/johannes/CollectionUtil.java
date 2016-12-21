package ch.johannes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CollectionUtil {

    public static <T> List<T> listOf(T ... elements) {
        return arrayListOf(elements);
    }

    public static <T> List<T> arrayListOf(T ... elements) {
        return (List<T>) fillCollection(new ArrayList<>(), elements);
    }

    public static <T> List<T> linkedListOf(T ... elements) {
        return (List<T>) fillCollection(new LinkedList<>(), elements);
    }

    public static <T> Set<T> setOf(T ... elements) {
        return hashSetOf(elements);
    }

    public static <T> Set<T> hashSetOf(T ... elements) {
        return (Set<T>) fillCollection(new HashSet<>(), elements);
    }

    public static <T> Set<T> linkedHashSetOf(T ... elements) {
        return (Set<T>) fillCollection(new LinkedHashSet<>(), elements);
    }


    private static <T> Collection<T> fillCollection(Collection<T> myList, T [] elements) {
        if(elements == null) {
            myList.add(null);
        } else {
            for(T element : elements) {
                myList.add(element);
            }
        }
        return myList;
    }
}
