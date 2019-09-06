/*
*时钟服务器，每秒一次的频率向客户端发送当前时间, 《The Go Programming Language》P171
* Server: $ ./clock
* Client: $ nc localhost 8000
*/
package main
import(
    "net"
    "time"
    "io"
    "log"
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
    for {
        _, err := io.WriteString(c, time.Now().Format("15:04:05\n"))
        if err != nil {
            // 写入失败时结束循环，可能由于客户端断开链接
            return
        }
        // 间隔1秒
        time.Sleep(1 * time.Second)
    }
}
