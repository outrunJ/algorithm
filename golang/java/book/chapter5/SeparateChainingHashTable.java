package chapter5;

import org.omg.CORBA.Any;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by outrun on 5/16/16.
 */
public class SeparateChainingHashTable <AnyType>{

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public  SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];

        for(int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<AnyType>();
        }
    }

    public void insert(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);
            if (++currentSize > theLists.length) {
                rehash();
            }
        }
    }

    public void remove(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    public boolean contains (AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    public  void makeEmpty () {
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
    }

    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<AnyType>[] theLists;
    private int currentSize;

    private void rehash() {
        List<AnyType>[] oldLists = theLists;

        theLists = new List[nextPrime(2 * theLists.length)];
        for (int j = 0; j < theLists.length; j++) {
            theLists[j] = new LinkedList<AnyType>();
        }
    }

    private int myhash(AnyType x) {
        int hashVal = x.hashCode();

        hashVal %= theLists.length;
        if (hashVal < 0) {
            hashVal += theLists.length;
        }

        return hashVal;
    }

    private static int nextPrime(int n) {
        if (n % 2 == 0) {
            n++;
        }

        for (; !isPrime(n); n += 2);

        return n;
    }

    private static boolean isPrime(int n) {
        if (n == 2 || n == 3) {
            return true;
        }

        if (n == 1 || n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= n; i+= 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
