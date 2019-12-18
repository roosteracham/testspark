package hadoop.排序;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class IntPair implements WritableComparable<IntPair> {

    private int first;

    private int second;

    public int getFirst() {
        return first;
    }

    public IntPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public int compareTo(IntPair o) {
//        int cmp = first.compareTo(o.getFirst());
//        if (cmp != 0) {
//            return cmp;
//        }
//        return second.compareTo(o.getSecond());
        if (first > o.getFirst()) {
            return 2;
        } else if (first < o.getFirst()) {
            return -2;
        } else if (second > o.getSecond()) {
            return 1;
        } else if (second < o.getSecond()) {
            return -1;
        }
        return 0;
    }

    public static int compare(int a, int b) {
        if (a > b) {
            return 1;
        } else if (a < b) {
            return -1;
        }
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
//        first.write(dataOutput);
//        second.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
//        first.readFields(dataInput);
//        second.readFields(dataInput);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntPair intPair = (IntPair) o;
        return Objects.equals(first, intPair.first) &&
                Objects.equals(second, intPair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "IntPair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
