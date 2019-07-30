package leetcode.medium.matrix;

import org.junit.Test;

import java.util.*;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-30 星期二 19:36
 **/
public class MergeInterval {
    @Test
    public void mergeMergeInterval() {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] result = this.merge(intervals);
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }

    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }

        // 将区间按照起始数值排序
        List<Tuple<Integer, Integer>> lstInterval = new ArrayList<>(intervals.length);
        for (int i = 0; i < intervals.length; i++) {
            Tuple<Integer, Integer> interval = new Tuple<>(intervals[i][0], intervals[i][1]);
            lstInterval.add(interval);
        }
        Collections.sort(lstInterval, Comparator.comparing(Tuple::getFirst));
        // 使用堆栈保存合并后的区间
        Deque<Tuple<Integer, Integer>> intervalDeque = new LinkedList<>();
        intervalDeque.push(lstInterval.get(0));

        for (int i = 1; i < lstInterval.size(); i++) {
            Tuple<Integer, Integer> top = intervalDeque.peekFirst();
            Tuple<Integer, Integer> current = lstInterval.get(i);
            if (top.second >= current.first) {
                top.second = Math.max(top.second, current.second);
            } else {
                intervalDeque.push(current);
            }
        }

        int[][] result = new int[intervalDeque.size()][2];
        int index = 0;
        while (!intervalDeque.isEmpty()) {
            Tuple<Integer, Integer> interval = intervalDeque.pollLast();
            result[index][0] = interval.first;
            result[index][1] = interval.second;
            index++;
        }
        return result;
    }

    private static class Tuple<T, K> {
        T first;
        K second;

        public Tuple(T first, K second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public K getSecond() {
            return second;
        }
    }
}
