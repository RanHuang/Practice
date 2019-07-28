package leetcode.exam;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 * 所有原子的第一个字母为大写，剩余字母都是小写。
 * formula的长度在[1, 1000]之间。
 * formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式
 *
 * 思路：通过递归方式简化化学式(化学式分为带括号的复杂化学式和不带括号的简单化学式)
 * 首先逐层去除复杂化学式的括号。如果为复杂化学式，则将化学是分为ABC三个部分，A为简单化学式，
 * B为带括号的复杂化学式去除最外层括号后的化学式(可能简单可能复杂)，
 * C可能为简单化学式也可能为复杂化学式，对A直接统计化学式原子个数，对B和C进行递归调用。
 * @author nick
 * @date 2019-07-28 星期日 00:27
 **/
public class CountOfAtoms {
    @Test
    public void testCountOfAtoms() {
        Assert.assertEquals("H2O", countOfAtoms("H2O"));
        Assert.assertEquals("H2MgO2", this.countOfAtoms("Mg(OH)2"));
        Assert.assertEquals("K4N2O14S4", this.countOfAtoms("K4(ON(SO3)2)2"));
    }

    public String countOfAtoms(String formula) {
        Map<String, Integer> result = this.atomsCount(formula);
        // 排序并组织输出结果
        List<Entry> lstEntry = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            lstEntry.add(new Entry(entry.getKey(), entry.getValue()));
        }
        // 按照原子的字典序排序
        Collections.sort(lstEntry, Comparator.comparing(entry -> entry.key));
        StringBuilder sb = new StringBuilder();
        for (Entry entry : lstEntry) {
            sb.append(entry.key);
            if (entry.num > 1) {
                sb.append(entry.num);
            }
        }
        return sb.toString();
    }

    static class Entry{
        public String key;
        public int num;

        public Entry(String key, int num) {
            this.key = key;
            this.num = num;
        }
    }

    @Test
    public void testAtomsCount() {
        System.out.println("H2ONAuMg3Na" + "===" + this.atomsCount("H2ONAuMg3Na"));
        System.out.println("OH" + "===" + this.atomsCount("OH"));
        System.out.println("SO3" + "===" + this.atomsCount("SO3"));
        System.out.println("K4(ON(SO3)2)2" + "===" + this.atomsCount("K4(ON(SO3)2)2"));
    }

    /**
     * 第归求解
     * 先寻找是否包含带有括号的子化学式
     * 否则直接统计不包含嵌套子化学式的简单化学式所含原子个数
     *
     * @param formula
     * @return
     */
    private Map<String, Integer> atomsCount(String formula) {
        Map<String, Integer> result = new HashMap<>();
        // 先处理含'()'的化学式，通过逐层第归去除括号
        int startOfSub = 0;
        while (startOfSub < formula.length()) {
            char current = formula.charAt(startOfSub);
            if (current == '(') {
                break;
            } else {
                startOfSub++;
            }
        }

        if (startOfSub < formula.length()) {
            // 带有括号的子化学式前的化学式
            String subformula = formula.substring(0, startOfSub);
            Map<String, Integer> subResult = this.atomsCount(subformula);
            for (String item : subResult.keySet()) {
                result.put(item, result.getOrDefault(item, 0) + subResult.get(item));
            }
            // 带有括号的化学式
            startOfSub += 1;
            int endOfSub = startOfSub + 1;
            int cnt = 1;
            while (cnt > 0) {
                endOfSub++;
                if (formula.charAt(endOfSub) == '(') {
                    cnt++;
                } else if (formula.charAt(endOfSub) == ')') {
                    cnt--;
                }
            }
            subformula = formula.substring(startOfSub, endOfSub);
            int startOfNum = endOfSub + 1;
            int endOfNum = startOfNum + 1;
            while (endOfNum < formula.length() && this.isDigit(formula.charAt(endOfNum))) {
                endOfNum++;
            }
            int factor = Integer.valueOf(formula.substring(startOfNum, endOfNum));
            subResult = this.atomsCount(subformula);
            for (String item : subResult.keySet()) {
                result.put(item, result.getOrDefault(item, 0) + subResult.get(item) * factor);
            }

            startOfSub = endOfNum;
            if (startOfSub < formula.length()) {
                subformula = formula.substring(startOfSub);
                subResult = this.atomsCount(subformula);
                for (String item : subResult.keySet()) {
                    result.put(item, result.getOrDefault(item, 0) + subResult.get(item));
                }
            }
        } else {
            // 不含有括号的简单化学式
            String subFormala = formula;
            Deque<String> atomStack = new LinkedList<>();
            Deque<String> numStack = new LinkedList<>();
            int end = subFormala.length();
            int start = end - 1;
            boolean isNumber = true;
            while (start >= 0) {
                char current = formula.charAt(start);
                if (isNumber) {
                    if (this.isDigit(current)) {
                        start--;
                    } else {
                        if (start == end - 1) {
                            numStack.push("1");
                        } else {
                            numStack.push(formula.substring(start + 1, end));
                            end = start + 1;
                        }
                        isNumber = false;
                    }
                } else {
                    if (this.isLowerCase(current)) {
                        start--;
                    } else {
                        // 大写字母
                        atomStack.push(formula.substring(start, end));
                        end = start;
                        start--;
                        isNumber = true;
                    }
                }
            }
            // 统计简单化学式各个原子的数量
            while (!atomStack.isEmpty()) {
                String atom = atomStack.pop();
                int num = Integer.valueOf(numStack.pop());
                result.put(atom, result.getOrDefault(atom, 0) + num);
            }
        }

        return result;
    }


    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

}
