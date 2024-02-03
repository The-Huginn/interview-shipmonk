/**
 * The {@code com.thehuginn.collect.SortedLinkedList} is implemented using delegate
 *  from {@code com.google.common.collect.TreeMultiset}. This library provides ordered
 *  multiset, i.e. allows for duplicates. As the best practise it tries to reuse already
 *  existing and tested code rather than trying to implement this functionality from scratch.
 * <p>
 *  But it depends on the intention of the library and the expected usage. If I had to write
 *  almost everything from scratch, I would have used an internal ArrayList and binary insertion
 *  algo for inserting new elements. New methods should be fairly easy to add to the interface
 *  and adjust the corresponding implementation class.
 * <p>
 *  I have decided to utilize factory pattern to check for valid classes and then return my
 *  internal implementation {@code com.thehuginn.collect.impl.SortedLinkedListImpl}. It could
 *  have been done without the factory class for sure and have the static methods directly
 *  in the implementation class.
 *  <p>
 *  It was developed using Quarkus due to the continuous testing features provided by the framework.
 *  If the library was to be released then quarkus dependencies should be removed.`
 */
package com.thehuginn.collect;