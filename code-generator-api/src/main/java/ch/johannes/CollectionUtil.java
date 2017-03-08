package ch.johannes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtil {

    public static <T> List<T> listOf(Collection<T> collection, T ... elements) {
        return (List<T>) fillCollection(fillCollection(newList(), collection), elements);
    }

    public static <T> List<T> listOf(Collection<T> collectionA, Collection<T> collectionB) {
        return (List<T>) fillCollection(fillCollection(newList(), collectionA), collectionB);
    }

    public static <T> List<T> listOf(T ... elements) {
        return (List<T>) fillCollection(newList(), elements);
    }

    public static <T> Set<T> setOf(T ... elements) {
        return (Set<T>) fillCollection(newSet(), elements);
    }

    public static <T> Set<T> setOf(Collection<T> collection, T ... elements) {
        return (Set<T>) fillCollection(fillCollection(newSet(), collection), elements);
    }

    public static <T> Set<T> setOf(Collection<T> collectionA, Collection<T> collectionB) {
        return (Set<T>) fillCollection(fillCollection(newSet(), collectionA), collectionB);
    }

    private static <T> List<T> newList() {
        return new ArrayList<T>();
    }

    private static <T> Set<T> newSet() {
        return new LinkedHashSet<T>();
    }


    private static <T> Collection<T> fillCollection(Collection<T> myCollection, T [] elements) {
        if(elements != null) {
            for(T element : elements) {
                myCollection.add(element);
            }
        }
        return myCollection;
    }

    private static <T> Collection<T> fillCollection(Collection<T> myCollection, Collection<T> elements) {
        if(elements != null) {
            myCollection.addAll(elements);
        }
        return myCollection;
    }
}
