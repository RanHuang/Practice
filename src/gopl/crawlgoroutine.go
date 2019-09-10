/*
* 并发解析URL链接, 《The Go Programming Language》P188
* $ ./crawlgoroutine https://studygolang.com/ 
*/
package main
import(
    "fmt"
    "os"
    "log"
    "net/http"
    "golang.org/x/net/html"
)

func main() {
    worklist := make(chan []string)
    unseenList := make(chan string) // 去重后未访问的URL列表

    go func() { worklist <- os.Args[1:] } ()
    var routineNumber = 20
    for i:=0; i<routineNumber; i++ {
        go func() {
            for link := range unseenList {
                foundList := crawl(link)
                worklist <- foundList
            }
        }()
    }    

    // 主goroutine负责对URL列表进行去重，并将未访问过的链接发送给爬虫程序
    seen := make(map[string]bool)
    for list := range worklist {
        for _, link := range list {
            if !seen[link] {
                seen[link] = true
                unseenList <- link
            }
        }
    }
}

// 网络爬虫, 从网页中提取URL链接
func crawl(url string) []string {
    fmt.Println(url)

    list, err := extract(url)
    if err != nil {
        log.Print(err)
    }
    return list
}


// 解析HTML页面，提取URL
func extract(url string)([]string, error) {
    resp, err := http.Get(url)
    if err != nil {
        return nil, err
    }
    defer resp.Body.Close()

    if resp.StatusCode != http.StatusOK {
        return nil, fmt.Errorf("getting %s: %s", url, resp.StatusCode)
    }

    doc, err := html.Parse(resp.Body)
    if err != nil {
        return nil, fmt.Errorf("parsing %s: %s", url, err)
    }

    var links []string
    visitNode := func(n *html.Node) {
        if n.Type == html.ElementNode && n.Data == "a" {
            for _, a := range n.Attr {
                if a.Key != "href" {
                    continue
                }
                link, err := resp.Request.URL.Parse(a.Val)
                if err != nil {
                    continue
                }
                links = append(links, link.String())
            }
        }
    }
    
    forEachNode(doc, visitNode, nil)

    return links, nil
}

func forEachNode(n *html.Node, pre, post func(n *html.Node)) {
    if pre != nil {
        pre(n)
    }

    for c:= n.FirstChild; c != nil; c=c.NextSibling {
        forEachNode(c, pre, post)
    }
    
    if post != nil {
        post(n)
    }
}
