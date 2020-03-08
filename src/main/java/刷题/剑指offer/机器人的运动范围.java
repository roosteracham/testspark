package 刷题.剑指offer;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0]
 * 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格
 * [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 1：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 *
 */
public class 机器人的运动范围 {

    public static void main(String[] args) {
        System.out.println(new 机器人的运动范围().movingCount(38, 15, 9));
    }

    int res = 0;
    public int movingCount(int m, int n, int k) {
        if (m < 1 || n < 1 || k < 0) {
            return 0;
        }
        boolean[] visites = new boolean[m * n];
        movingCount(m, n, 0, 0, k, visites);
        return res;
    }

    public int movingCount(int m, int n, int i, int j, int k, boolean[] visites) {
        if (i >= m || j >= n || visites[i * n + j] || (loopSum(i) + loopSum(j) > k)) {
            return 0;
        } else if (!visites[i * n + j]){
            visites[i * n + j] = true;
            res += 1;
        }
        return movingCount(m, n, i, j + 1, k, visites) + movingCount(m, n, i + 1, j, k, visites);
    }

    //. 下面的方法不行的原因是： k值得限制，并不是一条斜线就可以分隔可以访问和不可以访问的格子

//    public int movingCount(int m, int n, int k) {
//        if ( m < 1 || n < 1 || k < 0) {
//            return 0;
//        } else if(k == 0) {
//            return 1;
//        }
//        int j = 0;
//        int i = 0;
//        boolean skip = false;
//        if (m > n) {
//            m -= n;
//            n = n + m;
//            m = n -m;
//        }
//        for (; i < m; i++) {
//            j = 0;
//            for (; j < n; j++) {
//                int sum = loopSum(i) + loopSum(j);
//                if (sum > k) {
//                    skip = true;
//                    break;
//                }
//            }
//            if (skip) {
//                break;
//            }
//        }
//        if (!skip) {
//            return m * n;
//        }
//        int sum = 0;
//        for (int l = 0; l < m; l++) {
//            if (j < 0) {
//                j = 0;
//            }
//            sum += j--;
//        }
//        return sum;
//    }

    int loopSum(int a) {
        int sum = 0;
        while (a != 0) {
            sum = sum + a % 10;
            a /= 10;
        }
        return sum;
    }
}
