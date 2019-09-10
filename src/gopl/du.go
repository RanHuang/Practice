/**
* 遍历目录统计磁盘使用空间，《The Go Programming Language》P194
* $ ./du $HOME
*/
package main
import(
    "fmt"
    "os"
    "flag"
    "io/ioutil"
    "path/filepath"
)

func main() {
    // 确定起始目录
    flag.Parse()
    roots := flag.Args()
    if len(roots) == 0 {
        roots = []string{"."}
    }
    
    // 遍历文件树，统计文件大小
    fileSize := make(chan int64)
    go func() {
        for _, root := range roots {
            walkDir(root, fileSize)
        }
        close(fileSize)
    }()

    //
    var nfiles, nbytes int64 
    for size := range fileSize {
        nfiles++
        nbytes += size
    }
    fmt.Printf("%d files  %.1f GB\n", nfiles, float64(nbytes)/1e9)
}
// 返回dir目录中条目
func direntrys(dir string) []os.FileInfo {
    entrys, err := ioutil.ReadDir(dir)
    if err != nil {
        fmt.Fprintf(os.Stderr, "diskusage: %v\n", err)
        return nil
    }
    return entrys
}

// 递归遍历以dir为目录的文件树，将访问到的文件大小发送到fileSize
func walkDir(dir string, fileSize chan<-int64) {
    for _, entry := range direntrys(dir) {
        if entry.IsDir() {
            subdir := filepath.Join(dir, entry.Name())
            walkDir(subdir, fileSize)
        } else {
            fileSize <- entry.Size()
        }
    }
}


