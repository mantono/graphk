package com.mantono

import java.util.NoSuchElementException

/**
 * All data structures that implements the [Iterable] interface allows the
 * retrieval of a random element in the data structure. For example;
 *
 * <pre>
 * `final SecureRandom random = new SecureRandom();
 * final int elementIndex = random.nextInt(setOfElements.size());
 * int i = 0;
 * Iterator<Object> iterator = setOfElements.iterator();
 * while(iterator.hasNext())
 * {
 * if(i++ == elementIndex)
 * return iterator.next();
 * iterator.next();
 * }
` *
</pre> *
 *
 * would retrieve a random element from the collection
 * `setOfElements`, but it would be done with a time complexity of
 * `O(n)`.
 *
 * This interface is for data structures that supports retrieval of a random
 * element within the data structure by a dedicated method. This is preferably
 * implemented with a time complexity of `O(log n)` or
 * `O(1)` as opposed to using an iterator for random access of data
 * element as with the example above.
 *
 * Unlike the default [java.util.RandomAccess] found in the Java Standard
 * Library, this interface can be applied to for examples a [Set] and not
 * only a [List] implementations. Additionally, this interface has one
 * declared method, unlike the previously mentioned interface which is only a
 * maker interface.
 *
 * @author Anton sterberg
 *
 * @param <T> the type for the elements in the data structure.
</T> */

interface RandomAccess<T>
{
	/**
	 * Get a random element, but do not remove it.
	 *
	 * @return a random element in the data structure
	 *
	 * @throws NoSuchElementException if this data structure is empty
	 */
	val randomElement: T
}