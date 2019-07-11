package leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author nick
 * @date 2019-07-11 星期四 22:20
 **/
public class GroupAnagrams {

    @Test
    public void testGroupAnagrams() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> groups = groupAnagrams(strs);
        Assert.assertEquals(3, groups.size());
    }

    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 所有输入均为小写字母;不考虑答案输出的顺序。
     * <p>
     * 方法：排序数组分类，将每个字符串中字符按字典序排序作为Key，使用映射表记录Key相同的字符串
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> group = new HashMap<>();
        for (String str : strs) {
            char[] strChars = str.toCharArray();
            Arrays.sort(strChars);
            String key = String.valueOf(strChars);
            if (!group.containsKey(key)) {
                group.put(key, new ArrayList<>());
            }
            List<String> data = group.get(key);
            data.add(str);
        }
        return group.values().stream().collect(Collectors.toList());
    }
}
