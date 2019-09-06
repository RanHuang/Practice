/**
*使用Go的net.Dial接口连接回声服务器, 《The Go Programming Language》P175
* $ ./netcatreverb
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
    defer conn.Close()

    go mustCopy(conn, os.Stdin)
    mustCopy(os.Stdout, conn)
}

func mustCopy(dst io.Writer, src io.Reader) {
    if _, err := io.Copy(dst, src); err != nil {
        log.Fatal(err)
    }
}
