/**
* 迷你回声服务器，返回请求路径, 《The Go Programming Language》P15 
* $ ./miniserver
* $ ./fetch http://localhost:8000/hello/nick
* $ ./fetch http://localhost:8000/count
*/
package main
import(
    "fmt"
    "log"
    "net/http"
    "sync"
)

var mutex sync.Mutex
var count int

func main() {
    http.HandleFunc("/", handler)
    http.HandleFunc("/count", counter)
    log.Fatal(http.ListenAndServe("localhost:8000", nil))
}

func handler(w http.ResponseWriter, req *http.Request) {
    mutex.Lock()
    count++
    mutex.Unlock()
    
    fmt.Fprintf(w, "----- URL.Path=%q -----\n", req.URL.Path)
    fmt.Fprintf(w, "%s %s %s\n", req.Method, req.URL, req.Proto)
    for key,value := range req.Header {
        fmt.Fprintf(w, "Header[%q] = %q\n", key, value)
    }
    fmt.Fprintf(w, "Host = %q\n", req.Host)
    fmt.Fprintf(w, "RemotAddr = %q\n", req.RemoteAddr)
    if err := req.ParseForm(); err != nil {
        log.Print(err)
    }
    
    for k,v := range req.Form {
        fmt.Fprintf(w, "Form[%q] = %q\n", k, v)
    }
}

func counter(w http.ResponseWriter, req *http.Request) {
    mutex.Lock()
    fmt.Fprintf(w, "Count %d\n", count)
    mutex.Unlock()
}
