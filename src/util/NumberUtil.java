package util;

/**
 * @author nick
 * @date 2019-07-17 星期三 11:58
 **/
public class NumberUtil {
    /**
     * 交换整数数组中两个数的位置
     *
     * @param a
     * @param i
     * @param b
     * @param j
     */
    public static void swap(int[] a, int i, int[] b, int j) {
        a[i] = a[i] ^ b[j];
        b[j] = a[i] ^ b[j];
        a[i] = a[i] ^ b[j];
    }
}
