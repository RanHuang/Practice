/*
*回声服务器，《The Go Programming Language》P174
* Server: $ ./reverb
*/
package main
import(
    "fmt"
    "strings"
    "net"
    "time"
    "log"
    "bufio"
)

func main() {
    listener, err := net.Listen("tcp", "localhost:8000")
    if err != nil {
        log.Fatal(err)
    }

    for {
        conn, err := listener.Accept()
        if err != nil {
            log.Print(err)  // 连接终止
            continue
        }
        // 并发处理链接
        go handleConnection(conn)
    }
}

func handleConnection(c net.Conn) {
    defer c.Close()
    input := bufio.NewScanner(c)
    for input.Scan() {
        go echo(c, input.Text(), 1*time.Second)
    }
}

func echo(c net.Conn, s string, delay time.Duration) {
    fmt.Fprintln(c, "\t", strings.ToUpper(s))
    time.Sleep(delay)
    fmt.Fprintln(c, "\t", s)
    time.Sleep(delay)
    fmt.Fprintln(c, "\t", strings.ToLower(s))
}
