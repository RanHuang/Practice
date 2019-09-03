/*
获取指定URL的网页内容, 《The Go Programming Language》P12
Example: $ ./fetch http://www.gopl.io
*/
package main
import (
    "fmt"
    "os"
    "io/ioutil"
    "net/http"
)

func main(){
    for _,url := range os.Args[1:] {
        resp, err := http.Get(url)
        if err != nil {
            fmt.Fprintf(os.Stderr, "fetch: %v\n", err)
            os.Exit(1)
        }
        body, err := ioutil.ReadAll(resp.Body)
        resp.Body.Close()

        if err != nil {
            fmt.Fprintf(os.Stderr, "fetch: reading %s: %v\n", url, err)
            os.Exit(1)
        }
        fmt.Printf("%s", body)
    }
}
