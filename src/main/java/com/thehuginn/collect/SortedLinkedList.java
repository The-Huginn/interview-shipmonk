package com.thehuginn.collect;

/**
 * Note: Order of duplicates is not guaranteed to have preserved order of insertion order
 */
public interface SortedLinkedList<T extends Comparable<T>> extends Iterable<T> {

    /**
     * Adds value to the LinkedList and preserves respective order of elements
     */
    void add(T value);

    int size();

    boolean isEmpty();
}
