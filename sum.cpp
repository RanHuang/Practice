#include<iostream>
using namespace std;
int main(void)
{
    int a = 2;
    int b = ++a + ++a;
    cout << "b = " << b << ", a = " << a << endl;

    a = 2;
    b = a++ + ++a;
    cout << "b = " << b << ", a = " << a << endl;

    a = 2;
    b = ++a + a++;
    cout << "b = " << b << ", a = " << a << endl;

    system("pause");
    return 0;
}