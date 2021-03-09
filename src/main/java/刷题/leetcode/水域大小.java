//package 刷题.leetcode;
//
//import java.util.BitSet;
//import java.util.LinkedList;
//
//public class 水域大小 {
//    public int[] pondSizes(int[][] land) {
//        if (land == null) {
//            return new int[0];
//        }
//
//        int len1 = land.length;
//        int len2 = land[0].length;
//
//        BitSet visited = new BitSet(len1 * len2);
//        for (int i = 0; i < len1; i++) {
//            for (int j = 0; j < len2; j++) {
//                if (land[i][j] != 0) {
//                    continue;
//                }
//                int index = i * len1 + len2;
//                if (visited.get(index)) {
//                    continue;
//                }
//                visited.set(index);
//
//                int size = bfs(land, i, j);
//            }
//        }
//    }
//
//    private int bfs(int[][] land, int i, int j) {
//
//    }
//}
