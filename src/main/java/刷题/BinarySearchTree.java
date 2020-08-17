package 刷题;

import java.lang.reflect.Array;

/**
 * @author rooster
 */
public class BinarySearchTree<T> {

    T [] elements;

    int size;
    int capacity;
    int adjustTime;

    public void initTree(int capacity, Class<T> tClass) {
        this.capacity = capacity;
        elements = (T[]) Array.newInstance(tClass, capacity);
    }

    public void addElement(T element) {

    }
}
