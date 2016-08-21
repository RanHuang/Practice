#include<iostream>
using namespace std;
int main(void)
{
    auto c = 4;
    cout << ((c = 1) && (c = 3) && (c = 5)) << endl;
    cout << ((c = 1) || (c = 3) || (c = 5)) << endl;
    cout << "c = " << c << endl;

    system("pause");
    return 0;
}