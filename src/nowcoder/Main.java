package nowcoder;

import java.util.Scanner;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        // 输入
        Scanner scanner = new Scanner(System.in);
        //获取输入的字符串
        String str = scanner.nextLine();
        String[] strings = str.split(",");
        //转为整数数组
        int[] vals = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            vals[i] = Integer.parseInt(strings[i]);
        }

        // 调用实现方法
        Main main = new Main();
        int count = main.function(vals);

        // 输出
        System.out.println(count);
    }

    private int function(int[] vals) {
        return vals[0];
    }
}
