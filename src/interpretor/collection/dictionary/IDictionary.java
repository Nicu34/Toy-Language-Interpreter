package interpretor.collection.dictionary;

import java.io.Serializable;
import java.util.*;

/**
 * Created by MuresanN on 11/20/2016.
 */
public interface IDictionary<K, V> extends Serializable{

    interface IEntry<K, V> {
        boolean equals(Object o);

        K getKey();

        V getValue();

        int hashCode();

        void setValue(V value);
    }

    V get(K key);

    void put(K key, V value);

    int size();

    void remove(K key);

    Set<K> keySet();

    Collection<V> values();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    int hashCode();

    boolean equals(Object o);

    Set<IEntry<K, V>> entrySet();

    void setContent(Set<IDictionary.IEntry<K, V>> map);
}
