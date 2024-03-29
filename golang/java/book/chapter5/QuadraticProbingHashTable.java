package chapter5;

import com.sun.xml.internal.bind.AnyTypeAdapter;
import org.omg.CORBA.Any;

/**
 * Created by outrun on 5/18/16.
 */
public class QuadraticProbingHashTable<AnyType> {

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        doClear();
    }

    public void makeEmpty() {
        doClear();
    }

    public boolean contains(AnyType x) {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    public boolean insert(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            return false;
        }

        if (array[currentPos] == null) {
            ++occupied;
        }

        array[currentPos] = new HashEntry<AnyType>(x, true);
        theSize++;

        if (occupied > array.length / 2) {
            rehash();
        }

        return true;
    }

    public boolean remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            array[currentPos].isActive = false;
            theSize--;
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return theSize;
    }

    public int capacity() {
        return array.length;
    }

    private static class HashEntry<AnyType> {
        public AnyType element;
        public boolean isActive;

        public HashEntry(AnyType e) {
            this(e, true);
        }

        public HashEntry(AnyType e, boolean i) {
            element = e;
            isActive = i;
        }

        ;
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private HashEntry<AnyType>[] array;
    private int occupied;
    private int theSize;

    private void doClear() {
        occupied = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }

    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    private int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myhash(x);

        while (array[currentPos] != null &&
                !array[currentPos].element.equals(x)) {
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length) {
                currentPos -= array.length;
            }
        }

        return currentPos;
    }

    private void rehash() {
        HashEntry<AnyType>[] oldArray = array;

        allocateArray(2 * oldArray.length);
        occupied = 0;
        theSize = 0;

        for (HashEntry<AnyType> entry : oldArray) {
            if (entry != null && entry.isActive) {
                insert(entry.element);
            }
        }
    }

    private int myhash(AnyType x) {
        int hashVal = x.hashCode();

        hashVal %= array.length;
        if (hashVal < 0) {
            hashVal += array.length;
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
            return  true;
        }

        if (n == 1 || n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= n; i +=2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
