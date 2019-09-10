/**
*使用Go的net.Dial接口访问聊天服务器, 《The Go Programming Language》P177
* $ ./netcatchat
*/
package main
import(
    "net"
    "os"
    "io"
    "log"
)

func main() {
    conn, err := net.Dial("tcp", "localhost:8000")
    if err != nil {
        log.Fatal(err)
    }

    done := make(chan struct{})
    go func() {
        io.Copy(os.Stdout, conn)
        log.Println("Done")
        done <- struct{}{}
    }()
    
    mustCopy(conn, os.Stdin)
    conn.Close()
    <-done
}
func mustCopy(dst io.Writer, src io.Reader) {
    if _, err := io.Copy(dst, src); err != nil {
        log.Fatal(err)
    }
}
