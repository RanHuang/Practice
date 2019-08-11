package nowcoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * 链接：https://ac.nowcoder.com/acm/problem/21302
 * 来源：牛客网
 * <p>
 * 被3整除的子序列
 * 给你一个长度为50的数字串,问你有多少个子序列构成的数字可以被3整除
 * 答案对1e9+7取模
 * 输入描述: 输入一个字符串，由数字构成，长度小于等于50
 *
 * @author nick
 * @date 2019-07-25 星期四 17:06
 **/
public class SubSum {
    public int function(int[] vals) {
        if (null == vals || vals.length == 0) {
            return 0;
        }
        int length = vals.length;
        int maxSum = 50 * 9;

        int[][] sumTable = new int[length][maxSum + 1];
        sumTable[0][vals[0]] = 1;

        for (int i = 1; i < length; i++) {
            for (int j = 0; j < maxSum; j++) {
                // 不包含当前节点vals[i]
                sumTable[i][j] += sumTable[i - 1][j];
                if (j == vals[i]) {
                    sumTable[i][j] += 1;
                }
                // 包含当前节点
                if (j - vals[i] >= 0) {
                    sumTable[i][j] += sumTable[i - 1][j - vals[i]];
                }
            }
        }


        int count = 0;
        for (int j = 0; j < maxSum; j++) {
            if (j % 3 == 0) {
                count += sumTable[length - 1][j];
            }
        }
        return count;
    }

    @Test
    public void testNumberOfSubString() {
        Assert.assertEquals(3, this.function(new int[]{1, 3, 2}));
        Assert.assertEquals(7, this.function(new int[]{3, 3, 3}));
        Assert.assertEquals(3, this.function(new int[]{0, 0}));
        Assert.assertEquals(1, this.function(new int[]{9}));
        Assert.assertEquals(23, this.function(new int[]{1, 2, 3, 4, 5, 6}));
    }
}
