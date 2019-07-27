package leetcode.exam;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 * <p>
 * 为字符匹配问题
 *
 * @author nick
 * @date 2019-07-28 星期日 00:20
 **/
public class NumJewelsInStones {
    @Test
    public void testNumJewelsInStones() {
        Assert.assertEquals(3, this.numJewelsInStones("aA", "aAAbbbb"));
        Assert.assertEquals(0, this.numJewelsInStones("z", "ZZ"));
    }

    /**
     * S 和 J 最多含有50个字母
     *  J 中的字符不重复
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        char[] jChars = J.toCharArray();
        char[] sChars = S.toCharArray();
        int cnt = 0;
        for (int j = 0; j < jChars.length; j++) {
            for (int s = 0; s < sChars.length; s++) {
                if (jChars[j] == sChars[s]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
