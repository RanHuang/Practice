/**
* 使用并行处理获取指定URL的网页内容的大小, 《The Go Programming Language》P14
* Example: $ ./fetchall https://golang.google.cn/  https://studygolang.com/  http://www.gopl.io/
0.47s   80443 https://studygolang.com/
0.73s    7130 https://golang.google.cn/
0.76s    4154 http://www.gopl.io/
0.76s elapse

*/
package main
import(
    "fmt"
    "os"
    "time"
    "net/http"
    "io"
    "io/ioutil"
)

func main() {
    start := time.Now()
    ch := make(chan string)
    
    for _, url := range os.Args[1:] {
        go fetch(url, ch)
    }

    for range os.Args[1:] {
        fmt.Println(<-ch)
    }
    fmt.Printf("%.2fs elapse\n", time.Since(start).Seconds())
}

func fetch(url string, ch chan<-string) {
    start := time.Now()
    resp, err := http.Get(url)
    if err != nil {
        ch <- fmt.Sprint(err)
        return
    }
    
    nbytes, err := io.Copy(ioutil.Discard, resp.Body)
    resp.Body.Close()
    if err != nil {
        ch <- fmt.Sprintf("while reading %s: %v", url, err)
        return
    }
    
    secs := time.Since(start).Seconds()
    ch <- fmt.Sprintf("%.2fs %7d %s", secs, nbytes, url)
}
