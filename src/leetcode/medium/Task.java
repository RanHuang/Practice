package leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 621. 任务调度器
 *
 * @author nick
 * @date 2019-07-12 星期五 22:38
 **/
public class Task {
    /**
     * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
     * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，
     * 或者在待命状态。然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内
     * CPU 在执行不同的任务，或者在待命状态。你需要计算完成所有任务所需要的最短时间。
     * 注：
     * 任务的总个数为 [1, 10000]。
     * n 的取值范围为 [0, 100]。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/task-scheduler
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        // 任务类型总共只有26种
        int[] taskCount = new int[26];
        // 对各种类型任务数量进行统计
        for (int i = 0; i < tasks.length; i++) {
            int index = tasks[i] - 'A';
            taskCount[index]++;
        }
        Arrays.sort(taskCount);

        // 为满足时间间隔n，首先数量最多的任务数-1 * (n+1)
        int interval = (taskCount[taskCount.length - 1] - 1) * (n + 1);
        int i = taskCount.length - 1;
        // 数量最多的任务数量不唯一，则追加
        while (i >= 0 && taskCount[i--] == taskCount[taskCount.length - 1]) {
            interval++;
        }

        // 经过上述算法统计的任务数量可能小于总任务数，e.g:无需待命填充时间
        interval = Math.max(interval, tasks.length);
        return interval;
    }

    /**
     * 输入: tasks = ["A","A","A","B","B","B"], n = 2
     * 输出: 8
     * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
     */
    @Test
    public void testLeastInterval_1() {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;

        int leastInterval = leastInterval(tasks, n);
        Assert.assertEquals(8, leastInterval);
    }

    @Test
    public void testLeastInterval_2() {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 0;

        int leastInterval = leastInterval(tasks, n);
        Assert.assertEquals(6, leastInterval);
    }

    @Test
    public void testLeastInterval_3() {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 1;

        int leastInterval = leastInterval(tasks, n);
        Assert.assertEquals(6, leastInterval);
    }

    @Test
    public void testTaskCount() {
        String[] strings = {"G", "C", "A", "H", "A", "G", "G", "F", "G", "J", "H", "C", "A", "G", "E", "A", "H", "E", "F",
                "D", "B", "D", "H", "H", "E", "G", "F", "B", "C", "G", "F", "H", "J", "F", "A", "C", "G", "D", "I", "J",
                "A", "G", "D", "F", "B", "F", "H", "I", "G", "J", "G", "H", "F", "E", "H", "J", "C", "E", "H", "F", "C",
                "E", "F", "H", "H", "I", "G", "A", "G", "D", "C", "B", "I", "D", "B", "C", "J", "I", "B", "G", "C", "H",
                "D", "I", "A", "B", "A", "J", "C", "E", "B", "F", "B", "J", "J", "D", "D", "H", "I", "I", "B", "A", "E",
                "H", "J", "J", "A", "J", "E", "H", "G", "B", "F", "C", "H", "C", "B", "J", "B", "A", "H", "B", "D", "I",
                "F", "A", "E", "J", "H", "C", "E", "G", "F", "G", "B", "G", "C", "G", "A", "H", "E", "F", "H", "F", "C",
                "G", "B", "I", "E", "B", "J", "D", "B", "B", "G", "C", "A", "J", "B", "J", "J", "F", "J", "C", "A", "G",
                "J", "E", "G", "J", "C", "D", "D", "A", "I", "A", "J", "F", "H", "J", "D", "D", "D", "C", "E", "D", "D",
                "F", "B", "A", "J", "D", "I", "H", "B", "A", "F", "E", "B", "J", "A", "H", "D", "E", "I", "B", "H", "C",
                "C", "C", "G", "C", "B", "E", "A", "G", "H", "H", "A", "I", "A", "B", "A", "D", "A", "I", "E", "C", "C",
                "D", "A", "B", "H", "D", "E", "C", "A", "H", "B", "I", "A", "B", "E", "H", "C", "B", "A", "D", "H", "E",
                "J", "B", "J", "A", "B", "G", "J", "J", "F", "F", "H", "I", "A", "H", "F", "C", "H", "D", "H", "C", "C",
                "E", "I", "G", "J", "H", "D", "E", "I", "J", "C", "C", "H", "J", "C", "G", "I", "E", "D", "E", "H", "J",
                "A", "H", "D", "A", "B", "F", "I", "F", "J", "J", "H", "D", "I", "C", "G", "J", "C", "C", "D", "B", "E",
                "B", "E", "B", "G", "B", "A", "C", "F", "E", "H", "B", "D", "C", "H", "F", "A", "I", "A", "E", "J", "F",
                "A", "E", "B", "I", "G", "H", "D", "B", "F", "D", "B", "I", "B", "E", "D", "I", "D", "F", "A", "E", "H",
                "B", "I", "G", "F", "D", "E", "B", "E", "C", "C", "C", "J", "J", "C", "H", "I", "B", "H", "F", "H", "F",
                "D", "J", "D", "D", "H", "H", "C", "D", "A", "J", "D", "F", "D", "G", "B", "I", "F", "J", "J", "C", "C",
                "I", "F", "G", "F", "C", "E", "G", "E", "F", "D", "A", "I", "I", "H", "G", "H", "H", "A", "J", "D", "J",
                "G", "F", "G", "E", "E", "A", "H", "B", "G", "A", "J", "J", "E", "I", "H", "A", "G", "E", "C", "D", "I",
                "B", "E", "A", "G", "A", "C", "E", "B", "J", "C", "B", "A", "D", "J", "E", "J", "I", "F", "F", "C", "B",
                "I", "H", "C", "F", "B", "C", "G", "D", "A", "A", "B", "F", "C", "D", "B", "I", "I", "H", "H", "J", "A",
                "F", "J", "F", "J", "F", "H", "G", "F", "D", "J", "G", "I", "E", "B", "C", "G", "I", "F", "F", "J", "H",
                "H", "G", "A", "A", "J", "C", "G", "F", "B", "A", "A", "E", "E", "A", "E", "I", "G", "F", "D", "B", "I",
                "F", "A", "B", "J", "F", "F", "J", "B", "F", "J", "F", "J", "F", "I", "E", "J", "H", "D", "G", "G", "D",
                "F", "G", "B", "J", "F", "J", "A", "J", "E", "G", "H", "I", "E", "G", "D", "I", "B", "D", "J", "A", "A",
                "G", "A", "I", "I", "A", "A", "I", "I", "H", "E", "C", "A", "G", "I", "F", "F", "C", "D", "J", "J", "I",
                "A", "A", "F", "C", "J", "G", "C", "C", "H", "E", "A", "H", "F", "B", "J", "G", "I", "A", "A", "H", "G",
                "B", "E", "G", "D", "I", "C", "G", "J", "C", "C", "I", "H", "B", "D", "J", "H", "B", "J", "H", "B", "F",
                "J", "E", "J", "A", "G", "H", "B", "E", "H", "B", "F", "F", "H", "E", "B", "E", "G", "H", "J", "G", "J",
                "B", "H", "C", "H", "A", "A", "B", "E", "I", "H", "B", "I", "D", "J", "J", "C", "D", "G", "I", "J", "G",
                "J", "D", "F", "J", "E", "F", "D", "E", "B", "D", "B", "C", "B", "B", "C", "C", "I", "F", "D", "E", "I",
                "G", "G", "I", "B", "H", "G", "J", "A", "A", "H", "I", "I", "H", "A", "I", "F", "C", "D", "A", "C", "G",
                "E", "G", "E", "E", "H", "D", "C", "G", "D", "I", "A", "G", "G", "D", "A", "H", "H", "I", "F", "E", "I",
                "A", "D", "H", "B", "B", "G", "I", "C", "G", "B", "I", "I", "D", "F", "F", "C", "C", "A", "I", "E", "A",
                "E", "J", "A", "H", "C", "D", "A", "C", "B", "G", "H", "G", "J", "G", "I", "H", "B", "A", "C", "H", "I",
                "D", "D", "C", "F", "G", "B", "H", "E", "B", "B", "H", "C", "B", "G", "G", "C", "F", "B", "E", "J", "B",
                "B", "I", "D", "H", "D", "I", "I", "A", "A", "H", "G", "F", "B", "J", "F", "D", "E", "G", "F", "A", "G",
                "G", "D", "A", "B", "B", "B", "J", "A", "F", "H", "H", "D", "C", "J", "I", "A", "H", "G", "C", "J", "I",
                "F", "J", "C", "A", "E", "C", "H", "J", "H", "H", "F", "G", "E", "A", "C", "F", "J", "H", "D", "G", "G",
                "D", "D", "C", "B", "H", "B", "C", "E", "F", "B", "D", "J", "H", "J", "J", "J", "A", "F", "F", "D", "E",
                "F", "C", "I", "B", "H", "H", "D", "E", "A", "I", "A", "B", "F", "G", "F", "F", "I", "E", "E", "G", "A",
                "I", "D", "F", "C", "H", "E", "C", "G", "H", "F", "F", "H", "J", "H", "G", "A", "E", "H", "B", "G", "G",
                "D", "D", "D", "F", "I", "A", "F", "F", "D", "E", "H", "J", "E", "D", "D", "A", "J", "F", "E", "E", "E",
                "F", "I", "D", "A", "F", "F", "J", "E", "I", "J", "D", "D", "G", "A", "C", "G", "G", "I", "E", "G", "E",
                "H", "E", "D", "E", "J", "B", "G", "I", "J", "C", "H", "C", "C", "A", "A", "B", "C", "G", "B", "D", "I",
                "D", "E", "H", "J", "J", "B", "F", "E", "J", "H", "H", "I", "G", "B", "D"};
        char[] tasks = new char[strings.length];
        for (int i = 0; i < strings.length; i++) {
            tasks[i] = strings[i].charAt(0);
        }

        int[] taskCount = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            int index = tasks[i] - 'A';
            taskCount[index]++;
        }

        int total = 0;
        for (int i = 0; i < taskCount.length; i++) {
            total += taskCount[i];
        }
        Assert.assertEquals(strings.length, total);


        int n = 1;
        int leastInterval = leastInterval(tasks, n);
        Assert.assertEquals(1000, leastInterval);
    }


}
