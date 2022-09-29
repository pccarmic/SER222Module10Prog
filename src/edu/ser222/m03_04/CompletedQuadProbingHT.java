package edu.ser222.m03_04;

/**
 * A symbol table implemented using a hashtable with quadratic probing.
 * 
 * @author Pual Carmichael, Acuna
 */
public class CompletedQuadProbingHT<Key, Value> extends CompletedLinearProbingHT<Key, Value> {

    //any constructors must be made public
    private int M;
    public CompletedQuadProbingHT(){
        this(997);
    }
    public CompletedQuadProbingHT(int size){
        super(size);
        this.M = size;
    }
    public int hash(Key key, int i) {
        return ((key.hashCode() & 0x7fffffff) + i * i) % M;
    }
}