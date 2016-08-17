#include<iostream>
using namespace std;
int main(void)
{
    int a = 2;
    int b = ++a + a++;
    cout<<"a = "<<a<<", b = "<<b<<endl;
    return 0;
}
/*
    int a = 2;
    int b = ++a + ++a;
    cout<<"a = "<<a<<", b = "<<b<<endl;
*/  
/*
    int a = 2;
    int b = ++a + a++;
    cout<<"a = "<<a<<", b = "<<b<<endl;
*/
/*
    int a = 2;
    int b = a++ + ++a;
    cout<<"a = "<<a<<", b = "<<b<<endl;
*/
