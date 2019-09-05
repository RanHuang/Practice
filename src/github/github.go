/*
使用JSON展示查询GitHub issue接口获取的数据, 《The Go Programming Language》 P85
Example: $ ./issues repo:golang/go is:open json decoder

GitHub的Web服务接口: https://developer.github.com/v3/
*/
package github
import (
    "fmt"
    "time"
    "strings"
    "encoding/json"
    "net/http"
    "net/url"
)

const IssuesURL = "https://api.github.com/search/issues"
type IssueSearchResult struct {
    TotalCount int `json:"total_count"`
    Items []*Issue
}
type Issue struct {
    Number int
    HTMLURL string `json:"html_url"`
    Title string
    State string
    User *User
    CreatedAt time.Time `json:"created_at"`
    Body string // MarkDown格式
}
type User struct {
    Login string
    HTMLURL string `json:"html_url"`
}

func SearchIssues(terms []string) (*IssueSearchResult, error) {
    q := url.QueryEscape(strings.Join(terms, " "))
    resp, err := http.Get(IssuesURL + "?q=" + q)
    defer resp.Body.Close()

    if err != nil {
        return nil, err
    }
    if resp.StatusCode != http.StatusOK{
        return nil, fmt.Errorf("Search query failed: %s", resp.Status)
    }

    var result IssueSearchResult
    if err := json.NewDecoder(resp.Body).Decode(&result); err != nil {
        return nil, err
    }

    return &result, nil
}
