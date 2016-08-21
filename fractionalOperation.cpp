#include<iostream>
#include<cmath>
using namespace std;

void swapInt(int &a, int &b);
int getLeastCommonMultiple(int a, int b);
int getGreatestCommonDivisor(int a, int b);
void addFraction(int a_n, int a_d, int b_n, int b_d);
void decFraction(int a_n, int a_d, int b_n, int b_d);
void mulFraction(int a_n, int a_d, int b_n, int b_d);
void divFraction(int a_n, int a_d, int b_n, int b_d);
void printResult(int a_n, int a_d, int b_n, int b_d, int num, int den, char op);

int main(void) {
    int a_num=5, a_den=6, b_num=3, b_den=8; //����--numerator�� ��ĸ--Denominator
    /*
    cout << "������������\n";
    cout << "�������1��";
    cin >> a_num;
    cout << "�����ĸ1��";
    cin >> a_den;
    cout << "�������2��";
    cin >> b_num;
    cout << "�����ĸ2��";
    cin >> b_den;
    */
    addFraction(a_num, a_den, b_num, b_den);
    decFraction(a_num, a_den, b_num, b_den);
    mulFraction(a_num, a_den, b_num, b_den);
    divFraction(a_num, a_den, b_num, b_den);

    system("pause");
    return 0;
}

void addFraction(int a_n, int a_d, int b_n, int b_d) {
    int den = getLeastCommonMultiple(a_d, b_d);
    int num = a_n*den / a_d + b_n*den / b_d;
    printResult(a_n, a_d, b_n, b_d, num, den, '+');  
}

void decFraction(int a_n, int a_d, int b_n, int b_d) {
    int den = getLeastCommonMultiple(a_d, b_d);
    int num = a_n*den / a_d - b_n*den / b_d;
    printResult(a_n, a_d, b_n, b_d, num, den, '-');
}

void mulFraction(int a_n, int a_d, int b_n, int b_d) {
    int den = a_d * b_d;
    int num = a_n * b_n;
    printResult(a_n, a_d, b_n, b_d, num, den, '*');
}

void divFraction(int a_n, int a_d, int b_n, int b_d) {
    int den = a_d * b_n;
    int num = a_n * b_d;
    printResult(a_n, a_d, b_n, b_d, num, den, '/');
}

void printResult(int a_n, int a_d, int b_n, int b_d, int num, int den, char op) {
    cout << a_n << "/" << a_d << " " <<op << " " << b_n << "/" << b_d << " = " << num << "/" << den;

    int divisor = getGreatestCommonDivisor(abs(num), den);
    if (divisor > 1) {
        cout << " = " << num / divisor << "/" << den / divisor << endl;
    }
    else
    {
        cout << endl;
    }
}

int getGreatestCommonDivisor(int a, int b) {
    if (a<b) swapInt(a, b);

    int c = 0;
    while (c = a%b) {
        a = b;
        b = c;
    }

    return b;
}

int getLeastCommonMultiple(int a, int b) {
    return a*b/ getGreatestCommonDivisor(a,b);
}

void swapInt(int &a, int &b) {
    int t;
    t = a;
    a = b;
    b = t;
}