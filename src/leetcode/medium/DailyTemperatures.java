package leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 739. 每日温度
 * <p>
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-15 星期一 06:48
 **/
public class DailyTemperatures {
    /**
     * 暴力搜索法
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] output = new int[T.length];
        // Last one
        output[T.length - 1] = 0;
        for (int i = 0; i < T.length - 1; i++) {
            // 寻找后续比当前温度高的数字
            int j = i + 1;
            while (j < T.length) {
                if (T[j] > T[i]) {
                    break;
                }
                j++;
            }
            if (j < T.length) {
                output[i] = j - i;

            } else {
                output[i] = 0;
            }
        }
        return output;
    }

    @Test
    public void testDailyTemperatures() {
        Assert.assertTrue(Arrays.equals(new int[]{1, 1, 4, 2, 1, 1, 0, 0},
                dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        Assert.assertTrue(Arrays.equals(new int[]{3, 1, 1, 2, 1, 1, 0, 1, 1, 0},
                dailyTemperatures(new int[]{55, 38, 53, 81, 61, 93, 97, 32, 43, 78})));
    }

}
