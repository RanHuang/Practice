#include<iostream>
#include<set>
#include<iomanip>
using namespace std;

double calcAvgScore(multiset<int> score);
void test();

int main(void)
{
//    test();

    int n;
    multiset<int> score;
    cout << "请输入裁判人数(大于2的整数): ";
    cin >> n;
    if (n < 3) {
        cout << "裁判人数不足..." << endl;
    }
    else
    {
        int i = 1;
        int input = 0;
        while ( i <= n ) {
            cout << "请输入裁判" << i << "的评分(0-10): ";
            cin >> input;
            score.insert(input);
            i++;
        }
        cout << "去掉最高分 " << *(score.begin()) << ", 去掉最低分 " << *(--score.end()) << endl;
        cout << "剩下" << n - 2 << "名裁判的平均分为" << setprecision(4) << calcAvgScore(score) << endl;
    }

    system("pause");
    return 0;
}

double calcAvgScore(const multiset<int> score) {
    double result = 0;

    auto begin = score.cbegin();
    auto end = score.cend();
    
    for (++begin, --end; begin != end; ++begin) {
        result += *begin;
    }
//    cout << "平均分" << setprecision(4) << result / (score.size() - 2) << endl;
    return  result / (score.size() - 2);
}


void test() {
    int n;
    multiset<int> score;

    n = 10;
    int arr[] = { 4, 6, 7, 9, 5, 8, 5, 4, 7, 9 };
    int result = 0;
    for (int i = 0; i < n; i++) {
        score.insert(arr[i]);
        result += arr[i];
    }
    result -= 13;

    cout << "平均分" << setprecision(4) << calcAvgScore(score) << endl;

    return;
}
