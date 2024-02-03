package com.thehuginn.collect.impl;

import com.google.common.collect.TreeMultiset;
import com.thehuginn.collect.SortedLinkedList;

import java.util.Collection;
import java.util.Iterator;

class SortedLinkedListImpl<T extends Comparable<T>> implements SortedLinkedList<T> {
    TreeMultiset<T> delegate;

    SortedLinkedListImpl() {
        delegate = TreeMultiset.create();
    }

    SortedLinkedListImpl(Collection<T> collection) {
        delegate = TreeMultiset.create(collection);
    }

    @Override
    public void add(T value) {
        delegate.add(value);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
    }
}
