/**
* 使用无缓冲通道实现管道(Pipline) 《The Go Programming Language》 P178
* 产生自然数序列，计算平方值，输出
*/
package main
import "fmt"

func main() {
    naturals := make(chan int)
    squares := make(chan int)
    // 产生自然数序列 
    go func() {
        for x:=0; x<20; x++ {
            naturals <- x
        }
        close(naturals)
    }()
    
    // 计算平方值
    go func() {
        for x:= range naturals {
            squares <- x*x
        }
        close(squares)
    }()

    // Print Out
    for x:= range squares {
        fmt.Println(x)   
    }
}
