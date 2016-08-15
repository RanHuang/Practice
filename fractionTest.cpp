#include<iostream>
#include"Fraction.h"
using namespace std;

int main(void) {
    int a_num=5, a_den=6, b_num=3, b_den=8; //分子--numerator； 分母--Denominator
    /*
    cout << "输入两个分数\n";
    cout << "输入分子1：";
    cin >> a_num;
    cout << "输入分母1：";
    cin >> a_den;
    cout << "输入分子2：";
    cin >> b_num;
    cout << "输入分母2：";
    cin >> b_den;
    */
    a_den = -a_den;    
   
    Fraction a(a_num, a_den), b(b_num, b_den);
    cout << a << " + " << b << " = " << a+b <<endl;
    cout << a << " - " << b << " = " << a - b << endl;
    cout << a << " * " << b << " = " << a * b << endl;
    cout << a << " / " << b << " = " << a / b << endl;

    Fraction c(4, 8), d(1, 2);
    cout << c << " < " << d << " ? " << (c < d) << endl;
    cout << c << " == " << d << " ? " << (c == d) << endl;

    system("pause");
    return 0;
}
