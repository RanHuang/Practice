package leetcode.medium.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * @author nick
 * @date 2019-08-04 星期日 13:15
 **/
public class IpAddresses {
    @Test
    public void testRestoreIpAddresses() {
        Assert.assertEquals(2, this.restoreIpAddresses("010010").size());
        Assert.assertEquals(2, this.restoreIpAddresses("25525511135").size());
        Assert.assertEquals(1, this.restoreIpAddresses("0000").size());
    }

    /**
     * [0,i)--第1个数字串, 1<=i<4
     * [i,k)--第2个数字串, i+1<=k
     * [k,j)--第3个数字串, k<j, 2<=j-k<=6
     * [j,s.length)--第4个数字串, s.length-4<j<=s.length-1
     * 在符合以上条件后，由字符串转成的整数符合[0,255]
     * 过滤掉长度大于1且以0开头的数字串,e.g:00,02,025...
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) {
            return new ArrayList<>();
        }

        List<String> lstIp = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            for (int j = s.length() - 1; j > s.length() - 4; j--) {
                if (j - i > 6) {
                    continue;
                }
                for (int k = i + 1; k < j; k++) {
                    String first = s.substring(0, i);
                    String second = s.substring(i, k);
                    String third = s.substring(k, j);
                    String forth = s.substring(j);
                    if (first.length() > 3 || second.length() > 3 || third.length() > 3 || forth.length() > 3) {
                        continue;
                    }
                    if (first.length() > 1 && first.startsWith("0")) {
                        continue;
                    }
                    if (second.length() > 1 && second.startsWith("0")) {
                        continue;
                    }
                    if (third.length() > 1 && third.startsWith("0")) {
                        continue;
                    }
                    if (forth.length() > 1 && forth.startsWith("0")) {
                        continue;
                    }
                    int a = Integer.parseInt(first);
                    int b = Integer.parseInt(second);
                    int c = Integer.parseInt(third);
                    int d = Integer.parseInt(forth);
                    if (a > 255 || b > 255 || c > 255 || d > 255) {
                        continue;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(first).append(".").append(second).append(".").append(third).append(".").append(forth);
                    lstIp.add(sb.toString());
                }
            }
        }

        return lstIp;
    }


}
