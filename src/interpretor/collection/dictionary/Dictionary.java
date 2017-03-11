package interpretor.collection.dictionary;

import interpretor.exceptions.runtime.NullPointerException;

import java.util.*;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class Dictionary<K, V> implements IDictionary<K, V> {

    private int size;
    private final int CAPACITY;
    private IEntry<K, V>[] entries;

    public static class Entry<K, V> implements IDictionary.IEntry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry<K, V> entry = (Entry<K, V>) o;

            if (key != null ? !key.equals(entry.key) : entry.key != null) return false;
            return value != null ? value.equals(entry.value) : entry.value == null;

        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return key + " --> " + value;
        }
    }

    public Dictionary() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public Dictionary(int capacity) {
        this.CAPACITY = capacity;
        entries = new Entry[capacity];
    }

    private void ensureCapacity() {
        if (size == entries.length)
            entries = Arrays.copyOf(entries, entries.length * 2);
    }

    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i] != null) {
                if (entries[i].getKey().equals(key)) {
                    return entries[i].getValue();
                }
            }
        }
        return null;
    }

    public void put(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)) {
                entries[i].setValue(value);
                return;
            }
        }
        ensureCapacity();
        entries[size++] = new Entry<>(key, value);
    }

    public int size() {
        return size;
    }

    private void reinitializeArray(int position) {
        for (int i = position; i < size; i++)
            entries[i] = entries[i + 1];
    }

    public void remove(K key) {
        for (int i = 0; i < size; i++)
            if (entries[i].getKey().equals(key)) {
                entries[i] = null;
                size--;
                reinitializeArray(i);
            }
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<>();

        for (int i = 0; i < size; i++) {
            set.add(entries[i].getKey());
        }

        return set;
    }

    public Set<IEntry<K, V>> entrySet() {
        Set<IEntry<K, V>> set = new HashSet<>();

        for (int i = 0; i < size; i++) {
            set.add(entries[i]);
        }

        return set;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(K key) throws NullPointerException {
        if (key == null)
            throw new NullPointerException("Null pointer exception. Key cannot be null.");
        for (int i = 0; i < size; i++)
            if (entries[i].getKey().equals(key))
                return true;
        return false;
    }

    public boolean containsValue(V value) throws NullPointerException {
        if (value == null)
            throw new NullPointerException("Value cannot pe null.");
        for (int i = 0; i < size; i++)
            if (entries[i].getValue().equals(value))
                return true;
        return false;
    }

    public void setContent(Set<IDictionary.IEntry<K, V>> set) {
        for (IEntry<K,V> entry : set) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    public Collection<V> values() {
        Collection<V> collection = new ArrayList<V>();

        for (int i = 0; i < size; i++) {
            collection.add(entries[i].getValue());
        }

        return collection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dictionary<?, ?> that = (Dictionary<?, ?>) o;

        if (size != that.size) return false;
        if (CAPACITY != that.CAPACITY) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(entries, that.entries);

    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + CAPACITY;
        result = 31 * result + Arrays.hashCode(entries);
        return result;
    }

    @Override
    public String toString() {
        String  result = "";

        for (int i = 0; i < size; i++) {
            result += entries[i] + "\n";
        }

        return result;
    }
}
