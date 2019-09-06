/*
  使用底效的递归法计算斐波拉契数列，启动并发执行体提供程序运行状态的可见提示
  《The Go Programming Language》 P170
*/
package main
import(
    "time"
    "fmt"
)

func main() {
    go spinner(100 * time.Millisecond)

    const n = 45
    fibN := fib(n)
    fmt.Printf("\rFibonacci(%d) = %d\n", n, fibN)
}

func spinner(delay time.Duration) {
    for {
        for _, r := range `——\|/` {
            fmt.Printf("\r%c", r)
            time.Sleep(delay)
        }
    }
}

func fib(n int) int {
    if n < 2 { return n }
    return fib(n-1) + fib(n-2)
}