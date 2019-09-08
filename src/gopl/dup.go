/*
 输出标准输入中重复行(出现次数大于1的行, 结果： 行数 字符串), 《The Go Programming Language》P6
 Example:
    $ ./dup
    输入字符串，Ctrl+D结束
    $ ./dup data1.txt data2.txt
*/
package main
import(
    "fmt"
    "os"
    "bufio"
)

func main() {
    counts := make(map[string]int)
    files := os.Args[1:]
    if len(files) == 0 {
        // 从命令行读取输入
        countLines(os.Stdin, counts) 
    } else {
        // 从文件中读取数据
        for _, arg := range files {
            f,err := os.Open(arg)
            if err != nil {
                fmt.Fprintf(os.Stderr, "dup: %v\n", err)
                continue
            }
            countLines(f, counts)
            f.Close()
        }
    }

    for line, n := range counts {
	    if n>1 {
	        fmt.Printf("%d\t%s\n", n, line)
	    }
    }
}

func countLines(f *os.File, counts map[string]int) {
        input := bufio.NewScanner(f)
        for input.Scan() {
	        counts[input.Text()]++
        }
}
