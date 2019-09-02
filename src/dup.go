// 输出标准输入中重复行(出现次数大于1的行, 结果： 行数 字符串)
package main
import(
    "fmt"
    "os"
    "bufio"
)

func main() {
    counts := make(map[string]int)
    input := bufio.NewScanner(os.Stdin)
    for input.Scan() {
	counts[input.Text()]++
    }

    for line, n := range counts {
	if n>1 {
	    fmt.Printf("%d\t%s\n", n, line)
	}
    }
}
