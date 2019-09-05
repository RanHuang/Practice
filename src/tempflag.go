/*
 解析参数标志 -temp 指定华氏/摄氏温度 《The Go Programming Language》P140
 Example:
    $ ./tempflag -temp 38C
*/
package main
import(
    "fmt"
    "flag"
    "tempconv"
)

var temp = tempconv.CelsiusFlag("temp", 24.0, "the temperature")

func main() {
    flag.Parse()
    fmt.Println(*temp)

    fmt.Printf("绝对零度: %v\n", tempconv.AbsoluteZeroC)
    fmt.Printf("水的沸点: %v - %v\n", tempconv.BoilingC, tempconv.CToF(tempconv.BoilingC))
}
