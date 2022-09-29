package edu.ser222.m03_04;

/**
 * A symbol table implemented using a hashtable with linear probing.
 *
 * @author Pual Carmichael, Sedgewick and Wayne, Acuna
 */
import java.util.LinkedList;
import java.util.Queue;

public class  CompletedLinearProbingHT<Key, Value> implements ProbingHT<Key, Value> {

    //any constructors must be made public
    private int N;
    private final int M;
    private final Entry<Key, Value>[] entries;

    public static class Entry<Key, Value>{
        private final Key key;
        private Value val;

        public Entry(Key key, Value val){
            this.key = key;
            this.val = val;
        }
    }

    public CompletedLinearProbingHT() {
        this(997);
    }

    public CompletedLinearProbingHT(int size) {
        this.N = 0;
        this.M = size;
        this.entries = new Entry[M];
    }

    @Override
    public int hash(Key key, int i) {
        return ((key.hashCode() & 0x7fffffff) + i) % M;
    }

    @Override
    public void put(Key key, Value val){
        int i = 0;
        for(i = hash(key, i); entries[i] != null; i = (i + 1) % M){
            if(entries[i].key.equals(key)){
                entries[i].val = val;
                return;
            }
        }
        entries[i] = new Entry(key, val);
        N++;
    }

    @Override
    public Value get(Key key) {
        int i = 0;
        for (i = hash(key,i); entries[i] != null; i = (i + 1) % M) {
            if (entries[i].key.equals(key)) {
                return entries[i].val;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        int i = 0;
        for (i = hash(key, i); entries[i] != null; i = (i + 1) % M) {
            if (entries[i].key.equals(key)){
                while(entries[i] != null){
                    entries[i] = entries[(i + 1) % M];
                    i = (i + 1) % M;
                }
            }
        }
        entries[i] = null;
        N--;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        LinkedList<Key> keys = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            if(entries[i] != null){
                keys.add(entries[i].key);
            }
        }
        return keys;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE PROBINGHT INTERFACE.

    @Override
    public int getM() {
        return M;
    }

    @Override
    public Object getTableEntry(int i) {
        return entries[i];
    }
}