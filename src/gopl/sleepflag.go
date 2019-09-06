/*
使用标准接口flag.Value定义命令行标志, 《The Go Programming Language》P139
默认休眠时间1s，可用 -period 控制休眠时间
Example: 
    $ ./sleepflag -period 2s500ms
    $ ./sleepflag -period 2m500ms
*/
package main
import(
    "fmt"
    "flag"
    "time"
)

var period = flag.Duration("period", 1*time.Second, "sleep period")

func main() {
    flag.Parse()
    fmt.Printf("Sleeping for %v...\n", *period)
    time.Sleep(*period)
    fmt.Println("*End*")
}
