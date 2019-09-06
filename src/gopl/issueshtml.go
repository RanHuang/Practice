/*
以Html模板输出issues, 《The Go Programming Language》P89
Example: $ ./issueshtml repo:golang/go is:open json decoder > issues.html
*/
package main
import(
    "os"
    "log"
    "html/template"
    "github"
)

const temp = `
<h1>{{.TotalCount}} issues</h1>
<table>
<tr style='text-align: left'>
    <th>#</th>
    <th>State</th>
    <th>User</th>
    <th>Title</th>
</tr>
{{range .Items}}
<tr>
    <td><a href='{{.HTMLURL}}'>{{.Number}}</a></td>
    <td>{{.State}}</td>
    <td><a href='{{.User.HTMLURL}}'>{{.User.Login}}</a></td>
    <td><a href='{{.HTMLURL}}'>{{.Title}}</a></td>
</tr>
{{end}}
</table>
`

// 解析模板
var report = template.Must(template.New("issuelist").Parse(temp))

func main() {
    result, err := github.SearchIssues(os.Args[1:])
    if err != nil {
        log.Fatal("Query Issues from GitHub failed.", err)
    }

    if err := report.Execute(os.Stdout, result); err != nil {
        log.Fatal(err)
    }
}
