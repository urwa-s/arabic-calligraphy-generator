class CustomHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry<K, V> head;
    private int size;

    public CustomHashMap() {
        head = null;
        size = 0;
    }

    public K defaultFont(){
        if(head!=null)
        return head.key;
        return null;
    }
    public void put(K key, V value) {
        Entry<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = head;
        head = newEntry;
        size++;
    }

    public V get(K key) {
        Entry<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value; // Return the value if found
            }
            current = current.next;
        }
        return null;
    }

    public K[] keySet(){
        K[] keys = (K[]) new Object[size];
        Entry<K, V> current = head;
        int index = 0;

        while (current != null) {
            keys[index++] = current.key;
            current = current.next;
        }
        return keys;
    }
}