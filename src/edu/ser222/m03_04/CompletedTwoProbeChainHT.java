package edu.ser222.m03_04;

/**
 * A symbol table implemented using a hashtable with chaining.
 * Does not support load balancing or resizing.
 * 
 * @author Pual Carmichael, Sedgewick and Wayne, Acuna
 */
import java.util.LinkedList;
import java.util.Queue;

public class CompletedTwoProbeChainHT<Key, Value> implements TwoProbeChainHT<Key, Value> {

    //any constructors must be made public
    private int N;
    private int M;
    private LinkedList<Entry>[] entries;

    public static class Entry<Key, Value>{
        private Key key;
        private Value val;

        public Entry(Key key, Value val){
            this.key = key;
            this.val = val;
        }
    }
    public CompletedTwoProbeChainHT(){
        this(997);
    }
    public CompletedTwoProbeChainHT(int size){
        this.M = size;
        this.N = 0;
        entries = new LinkedList[M];
        for(int i = 0; i < M; i++){
            entries[i] = new LinkedList<>();
        }
    }

    @Override
    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff)% M;
    }

    @Override
    public int hash2(Key key) {
        return (hash(key) * 31) % M;
    }

    @Override
    public void put(Key key, Value val) {
        int hash_key = hash(key);
        int hash_key2 = hash2(key);
        for(int i = 0; i < entries[hash_key].size(); i++){
            if(entries[hash_key].get(i).key.equals(key)){
                entries[hash_key].get(i).val = val;
                return;
            }
        }
        for(int i = 0; i < entries[hash_key2].size(); i++){
            if(entries[hash_key2].get(i).key.equals(key)){
                entries[hash_key2].get(i).val = val;
                return;
            }
        }
        if(entries[hash_key].size() < entries[hash_key2].size()){
            entries[hash_key].add(new Entry(key, val));
        } else {
            entries[hash_key2].add(new Entry(key, val));
        }
        N++;
    }

    @Override
    public Value get(Key key) {
        int hash_key = hash(key);
        int hash_key2 = hash2(key);
        for(int i = 0; i < entries[hash_key].size(); i++){
            if(entries[hash_key].get(i).key.equals(key)){
                return (Value)entries[hash_key].get(i).val;
            }
        }
        for(int i = 0; i < entries[hash_key2].size(); i++){
            if(entries[hash_key2].get(i).key.equals(key)){
                return (Value)entries[hash_key2].get(i).val;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        int hash_key = hash(key);
        int hash_key2 = hash2(key);
        for(int i = 0; i < entries[hash_key].size(); i++){
            if(entries[hash_key].get(i).key.equals(key)){
                entries[hash_key].remove(i);
                N--;
            }
        }
        for(int i = 0; i < entries[hash_key2].size(); i++){
            if(entries[hash_key2].get(i).key.equals(key)){
                entries[hash_key2].remove(i);
                N--;
            }
        }
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
        LinkedList<Key> keys = new LinkedList<Key>();
        for(int i = 0; i < M; i++){
            for(int j = 0; j < entries[i].size(); j++){
                keys.add((Key)entries[i].get(j).key);
            }
        }
        return keys;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE TWOPROBECHAINHT INTERFACE.

    @Override
    public int getM() {
        return M;
    }

    @Override
    public int getChainSize(int i) {
        return entries[i].size();
    }
}