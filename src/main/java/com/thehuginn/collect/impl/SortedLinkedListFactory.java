package com.thehuginn.collect.impl;

import com.thehuginn.collect.SortedLinkedList;

import java.util.Collection;

/**
 * Factory for creating new instances for {@code com.thehuginn.collect.SortedLinkedList}
 *  from supported classes, which are currently {@code String} & {@code Integer}.
 *  Furthermore, a convenience method {@link #createFrom(Collection)} for
 *  converting existing Collections.
 */
public class SortedLinkedListFactory {

    private final static Class<?>[] allowedClasses = {String.class, Integer.class};

    /**
     * Creates SortedLinkedList for allowed types
     * @param type one of the supported class types
     */
    public static <T extends Comparable<T>> SortedLinkedList<T> create(Class<T> type) throws RuntimeException{
        checkType(type);
        return new SortedLinkedListImpl<>();
    }

    public static <T extends Comparable<T>> SortedLinkedList<T> createFrom(Collection<T> collection, Class<T> type) throws RuntimeException{
        checkType(type);
        return new SortedLinkedListImpl<>(collection);
    }

    private static <T extends Comparable<T>> void checkType(Class<T> type) throws RuntimeException {
        boolean allowedClass = false;
        for (Class<?> cls : allowedClasses) {
            if (type.equals(cls)) {
                allowedClass = true;
                break;
            }
        }

        if (!allowedClass) {
            throw new RuntimeException("Only String and Integer is allowed for creating instance of SortedLinkedList");
        }
    }
}
