/**
* 拓扑排序, 《The Go Programming Language》P106
*/
package main
import(
    "fmt"
    "sort"
)

var prereqs = map[string][]string {
    "算法" : {"数据结构"},
    "微积分" : {"线性代数"},
    "编译原理" : {"数据结构", "形式语言", "计算机组成原理"},
    "数据结构" : {"具体数学"},
    "数据库原理" : {"数据结构"},
    "具体数学" : {"程序设计导论"},
    "形式语言" : {"具体数学"},
    "网络原理" : {"操作系统原理"},
    "操作系统原理" : {"数据结构", "计算机组成原理"},
    "程序设计语言" : {"数据结构", "计算机组成原理"},
}

func main() {
    for i,course := range topoSort(prereqs) {
        fmt.Printf("%d:\t%s\n", i+1, course)
    }
}

func topoSort(m map[string][]string) []string {
    var order []string
    // 访问标志，避免重复访问
    seen := make(map[string]bool)
    // 声明函数变量 
    var visitAll func(items []string)
    // 将匿名函数赋值给函数变量，匿名函数可以捕获visitAll变量，从而实现递归调用
    visitAll = func(items []string) {
        for _, item := range items {
            if !seen[item] {
                seen[item] = true
                visitAll(m[item])
                order = append(order, item)
            }
        }
    }

    var keys []string
    for key := range m {
        keys = append(keys, key)
    }

    sort.Strings(keys)
    visitAll(keys)

    return order
}
