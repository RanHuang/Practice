// 利用二叉树实现插入排序
package main
import (
    "fmt"
    "strconv"
    "os"
)

func main() {
    var values = []int{}
    for _,v := range os.Args[1:] {
        if i,err := strconv.Atoi(v); err==nil {
            values = append(values,i)
        }
    }
    fmt.Println("Before sort: %v", values)
    sorted := Sort(values)
    fmt.Println("After  sort: %v", *sorted)
}

type tree struct{
    value int
    left,right *tree
}

func Sort(values []int) *[]int {
    var root *tree
    for _,v := range values {
        root = add(root, v)
    }
    appendValues(values[:0], root)
    return &values
}

func appendValues(values []int, t *tree) []int {
    if t!=nil {
        values = appendValues(values, t.left)
        values = append(values, t.value)
        values = appendValues(values, t.right)
    }
    return values
}

func add(t *tree, value int) *tree {
    if t == nil {
        return &tree{value:value}
    }

    if value<t.value {
        t.left = add(t.left,value)
    } else {
        t.right = add(t.right,value)
    }

    return t
}
