// 回显输出的参数，以逗号分隔
package main
import (
    "fmt"
    "os"
)

func main() {
    s, sep := "", ""
    for _,arg := range os.Args[1:] {
	s += sep + arg
	sep = ","
    }
    fmt.Println(s)
}