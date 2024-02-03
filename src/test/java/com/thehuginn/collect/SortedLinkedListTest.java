package com.thehuginn.collect;

import com.thehuginn.collect.impl.SortedLinkedListFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@QuarkusTest
class SortedLinkedListTest {

    @Test
    void testCreatingFromValidClass() {
        Class<?>[] validClasses = {Integer.class, String.class};
        try {
            for (Class cls : validClasses) {
                SortedLinkedListFactory.create(cls);
            }
        } catch (RuntimeException ignored) {
            fail("No Exception was expected");
        }
    }

    @Test
    void testCreatingFromInvalidClass() {
        try {
            SortedLinkedListFactory.create(Double.class);
            fail("Exception was expected as Double is not allowed");
        } catch (RuntimeException ignored) {
        }
    }

    @Test
    void testAddingSortedElements() {
        SortedLinkedList<Integer> linkedList = SortedLinkedListFactory.create(Integer.class);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        checkSortedLinkedList(linkedList);
    }

    @Test
    void testAddingUnsortedElements() {
        SortedLinkedList<Integer> linkedList = SortedLinkedListFactory.create(Integer.class);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(1);
        checkSortedLinkedList(linkedList);
    }

    @Test
    void testSortingStringList() {
        SortedLinkedList<String> linkedList = SortedLinkedListFactory.create(String.class);
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("aa");
        checkSortedLinkedList(linkedList);
    }

    @Test
    void testAddingDuplicates() {
        SortedLinkedList<Integer> linkedList = SortedLinkedListFactory.create(Integer.class);
        linkedList.add(1);
        linkedList.add(1);
        linkedList.add(1);
        assertEquals(3, linkedList.size());
        Iterator<Integer> iterator = linkedList.iterator();
        for (int i = 0; i <linkedList.size(); i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
    }

    @Test
    void testCreateFromExistingCollectionOfIntegers() {
        SortedLinkedList<Integer> linkedList = SortedLinkedListFactory.createFrom(Arrays.asList(1, 2, 3), Integer.class);
        checkSortedLinkedList(linkedList);
    }

    @Test
    void testCreateFromExistingCollectionOfStrings() {
        SortedLinkedList<String> linkedList = SortedLinkedListFactory.createFrom(Arrays.asList("first", "second", "third"), String.class);
        checkSortedLinkedList(linkedList);
    }

    <T extends Comparable<T>> void checkSortedLinkedList(SortedLinkedList<T> linkedList) {
        if (linkedList.isEmpty() || linkedList.size() == 1) {
            return;
        }

        Iterator<T> iterator = linkedList.iterator();
        T current, previous = iterator.next();
        while (iterator.hasNext()) {
            current = iterator.next();
            if (previous.compareTo(current) > 0) {
                fail("SortedLinkedList was supposed to be sorted");
            }
            previous = current;
        }
    }
}