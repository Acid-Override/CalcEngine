package com.playground.GroupBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GroupBy {

    /**
     * Groups elements of a collection based on a specified key function.
     *
     * @param <T> the type of elements in the collection
     * @param <K> the type of the key used for grouping
     * @param collection the list of elements to be grouped
     * @param keyFunction a function to determine the key for each element
     * @return a map where each key is associated with a list of elements from the collection
     *         that share the same key
     */
     public static <T, K> Map<K, List<T>> groupBy(List<T> collection, Function<T, K> keyFunction) {
         Map<K, List<T>> result = new HashMap<>();

         collection.forEach(item -> {
             K key = keyFunction.apply(item);

             List<T> group = result.getOrDefault(key, new ArrayList<>());
             group.add(item);
             result.put(key, group);
         });
         return result;
     }
}
