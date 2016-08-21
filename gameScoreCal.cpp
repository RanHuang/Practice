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
    cout << "�������������(����2������): ";
    cin >> n;
    if (n < 3) {
        cout << "������������..." << endl;
    }
    else
    {
        int i = 1;
        int input = 0;
        while ( i <= n ) {
            cout << "���������" << i << "������(0-10): ";
            cin >> input;
            score.insert(input);
            i++;
        }
        cout << "ȥ����߷� " << *(score.begin()) << ", ȥ����ͷ� " << *(--score.end()) << endl;
        cout << "ʣ��" << n - 2 << "�����е�ƽ����Ϊ" << setprecision(4) << calcAvgScore(score) << endl;
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
//    cout << "ƽ����" << setprecision(4) << result / (score.size() - 2) << endl;
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

    cout << "ƽ����" << setprecision(4) << calcAvgScore(score) << endl;

    return;
}
