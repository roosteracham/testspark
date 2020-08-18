package 刷题;

import java.lang.reflect.Array;

public class MaxHeap<T extends Comparable> {
    T[] elements;
    int adjustTime;
    int capacity;
    int size;

    public T[] getElements() {
        return elements;
    }

    public void initHeap(int capacity, Class<T> tClass) {
        this.capacity = capacity + 1;
        size = 0;
        elements = (T[]) Array.newInstance(tClass, this.capacity);
    }

    public int addElement(T element) {
        if (++size >= this.capacity) {
            size = --size;
        }
        elements[size] = element;
        adjustTime = 0;
        adjustHeap(size);
        return adjustTime;
    }

    private void adjustHeap(int size) {
        if (size == 1) {
            return;
        }
        int parentIndex = size / 2;
        if (elements[parentIndex].compareTo(elements[size]) >= 0) {
            return;
        }
        T tmp = elements[parentIndex];
        elements[parentIndex] = elements[size];
        elements[size] = tmp;
        adjustTime++;
        adjustHeap(parentIndex);
    }

    public T pop() {
        T res = elements[1];
        elements[1] = elements[size];
        size--;
        adjustHeap(size);
        return res;
    }

    public static void main(String[] args) {
        MaxHeap<Integer> minHeap = new MaxHeap<>();
        minHeap.initHeap(2, Integer.class);
        minHeap.addElement(3);
        minHeap.addElement(1);
        minHeap.addElement(2);
    }

}
