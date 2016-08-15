#include<iostream>
using namespace std;
int main(void)
{
    double d1 = 3.3333, d2 = 4.4444;
    auto sum = d1 + d2;
    if (sum == 7.7777) {
        cout << "相等" << endl;
    }
    else {
        cout << "不相等" << endl;
    }
    cout << d1<<" + "<<d2<<" = "<<sum << endl;

    if (fabs(d1+d2-sum) <= 1e-6) {
        cout << "相等" << endl;
    }
    else {
        cout << "不相等" << endl;
    }

    system("pause");
    return 0;
}