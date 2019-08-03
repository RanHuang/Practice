package leetcode.medium.rand;

import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 470. 用 Rand7() 实现 Rand10()
 * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
 * 不要使用系统的 Math.random() 方法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-08-03 星期六 22:56
 **/
public class Rand {
    private Random random = new Random(System.currentTimeMillis());

    public int rand7() {
        return random.nextInt(7) + 1;
    }

    @Test
    public void testRand7() {
        int size = 10000;
        Map<Integer, Long> numGroup = IntStream.rangeClosed(1, size)
                .map(item -> rand7())
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        numGroup.forEach((integer, aLong) -> {
            double p = aLong * 1.0 / size;
            System.out.println(integer + ":" + p);

        });

    }

    public int rand10() {
        while (true) {
            int a = this.rand7();
            if (a < 7) {
                int b = this.rand7();
                while (b > 5) {
                    b = this.rand7();
                }
                if (a % 2 == 0) {
                    return a;
                } else {
                    return a + 5;
                }
            }
        }
    }

}
