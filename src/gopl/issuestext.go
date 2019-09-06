/*
以Text模板输出issues, 《The Go Programming Language》P87
Example: $ ./issuestext repo:golang/go is:open json decoder
*/
package main
import(
    "time"
    "os"
    "log"
    "text/template"
    "github"
)

const temp = `{{.TotalCount}} issues:
{{range .Items}}----------------------------------
Number: {{.Number}}
User: {{.User.Login}}
Title: {{.Title | printf "%.64s"}}
Age: {{.CreatedAt | daysAgo}} days
{{end}}`

func daysAgo(t time.Time) int {
    return int(time.Since(t).Hours() / 24)
}
// 解析模板
var report = template.Must(template.New("issuelist").
    Funcs(template.FuncMap{"daysAgo": daysAgo}).
    Parse(temp))

func main() {
    result, err := github.SearchIssues(os.Args[1:])
    if err != nil {
        log.Fatal("Query Issues from GitHub failed.", err)
    }
    
    if err := report.Execute(os.Stdout, result); err != nil {
        log.Fatal(err)
    }
}
