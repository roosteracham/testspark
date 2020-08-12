package 刷题;

import java.lang.reflect.Array;

public class MinHeap<T extends Comparable> {
    T[] elements;
    int adjustTime;
    int capacity;
    int size;

    public void initHeap(int capacity, Class<T> tClass) {
        this.capacity = capacity;
        size = 0;
        elements = (T[]) Array.newInstance(tClass, capacity);
    }

    public int addElement(T element) {
        elements[++size] = element;
        adjustTime = 0;
        adjustHeap(size);
        return adjustTime;
    }

    private void adjustHeap(int size) {
        if (size == 1) {
            return;
        }
        int parentIndex = size / 2;
        if (elements[parentIndex].compareTo(elements[size]) <= 0) {
            return;
        }
        T tmp = elements[parentIndex];
        elements[parentIndex] = elements[size];
        elements[size] = tmp;
        adjustTime++;
        adjustHeap(parentIndex);
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>();
        minHeap.initHeap(10, Integer.class);
        minHeap.addElement(3);
        minHeap.addElement(1);
        minHeap.addElement(2);
    }

}
