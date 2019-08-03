package interview.bytedance;

/**
 * 统计直播间最大同时在线人数
 * 直播间会打印日志，日志记录人员的进入和离开的uid和时间，格式为
 * zhangsan:in:10:11:11
 * lisi:in:10:11:12
 * zhangsan:out:10:11:15
 * lisi:out:10:11:18
 * 日志保存以天为单位，按时间顺序记录。在一天的结束，需要根据以上日志统计直播间一天的最大在线人数，该怎么做？
 * 输入描述,日志格式
 * zhangsan:in:10:11:11
 * lisi:in:10:11:12
 * zhangsan:out:10:11:15
 * lisi:out:10:11:18
 * 输出:
 * 整数：直播间一天最大的同时在线人数
 *
 * @author nick
 * @date 2019-08-02 星期五 13:15
 **/
public class RoundFirst {
    /**
     * 算法： 定义用户变化数组change[n],顺序扫描，对于时刻i，用户上线则change[i]++，用户下线则change[i]--
     * online[i]为时刻i的在线用户数量，初始online[0]=0， 则有递推关系online[i]=online[i-1] + change[i]
     * 可通过2次顺序遍历，1st构建change[n],2nd构建online[i]并记录max
     * max即为最大同时在线人数
     */

    /**
     * 输入输出:
     * 'zhangsan:in:10:11:11\nlisi:in:10:11:12\nzhangsan:out:10:11:15\nlisi:out:10:11:18'
     * 2
     */
}
