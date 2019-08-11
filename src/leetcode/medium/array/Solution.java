package leetcode.medium.array;

/**
 * 398. 随机数索引
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-pick-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-08-11 星期日 22:10
 **/
public class Solution {
    private int[] vals;
    private int index;

    public Solution(int[] nums) {
        this.vals = nums;
        this.index = 0;
    }

    public int pick(int target) {
        int ret = -1;
        while (target != this.vals[this.index]) {
            this.index++;
            if (this.index >= this.vals.length) {
                this.index = this.index % this.vals.length;
            }
        }
        // 记录匹配到的索引值
        ret = this.index;

        // 将索引指向下一个位置
        this.index++;
        if (this.index >= this.vals.length) {
            this.index = this.index % this.vals.length;
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 3, 3};
        Solution solution = new Solution(nums);
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(1));
    }
}
