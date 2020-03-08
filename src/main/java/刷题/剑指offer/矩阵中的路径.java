package 刷题.剑指offer;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * <p>
 * [['a','b','c','e'],
 * ['s','f','c','s'],
 * ['a','d','e','e']]
 * <p>
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'ABCCED'
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：board = [['a','b'],['c','d']], word = 'abcd'
 * 输出：false
 * 提示：
 * <p>
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 */
public class 矩阵中的路径 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new 矩阵中的路径().exist(new char[][]{{'A','B'},{'C','D'}},
        "ACDB"));
        long time = System.currentTimeMillis() - start;
        System.out.println(time);
    }

    // dfs 深度优先搜索
//    public boolean exist(char[][] board, String word) {
//
//    }
    int l1 = 0;
    int l2 = 0;
    int j = 0;
    int k = 0;

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.isEmpty()) {
            return false;
        }


        // 先找word第一个字符，然后在第一个位置的上下左右找第二个字符，一次类推
        l1 = board.length;
        l2 = board[0].length;
        if (l2 == 1) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < l1; i++) {
                builder.append(board[i][0]);
            }
            return builder.toString().contains(word) || builder.reverse().toString().contains(word);
        }
        if (l1 == 1) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < l2; i++) {
                builder.append(board[0][i]);
            }
            return builder.toString().contains(word) || builder.reverse().toString().contains(word);
        }

        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            j = 0;
            boolean skip = false;
            for (; j < l1; j++) {
                k = 0;
                for (; k < l2; k++) {
                    if (word.charAt(0) == board[j][k]) {
                        char tmp = board[j][k];
                        board[j][k] = '%';
                        if (exist(board, word, 1, i, k)) {
                            return true;
                        }
                        board[j][k] = tmp;
                    }
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board, String word, int curSearchIndex, int j, int k) {
        //  找第一个字符在矩阵中的坐标
//        boolean[] visited = new boolean[l1 * l2];
//        visited[j * l1 + k] = true;
        if (curSearchIndex >= word.length()) {
            return true;
        }
        char curChar = word.charAt(curSearchIndex);
        // j 矩阵行 k 矩阵列
        if (j < l1 - 1  && board[j + 1][k] == curChar) {
            char tmp = board[++j][k];
            board[j][k] = '%';
            if (exist(board, word, curSearchIndex + 1, j, k)) {
                return true;
            }
            board[j][k] = tmp;
        }
        if (k < l2 - 1 && board[j][k + 1] == curChar) {
            char tmp = board[j][k + 1];
            board[j][k + 1] = '%';
            if (exist(board, word, curSearchIndex + 1, j, k + 1)) {
                return true;
            }
            board[j][k + 1] = tmp;
        }
        if (j > 0 && board[j -1][k] == curChar) {
            char tmp = board[--j][k];
            board[j][k] = '%';
            if (exist(board, word, curSearchIndex + 1, j, k)) {
                return true;
            }
            board[j][k] = tmp;

        }
        if (k > 0 &&  board[j][k -1] == curChar) {
            char tmp = board[j][--k];
            board[j][k] = '%';
            if (exist(board, word, curSearchIndex + 1, j, k)) {
                return true;
            }
            board[j][k] = tmp;
        }
        return false;
    }

}