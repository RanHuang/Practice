#include<iostream>
#include"Fraction.h"
using namespace std;

void testCalc(int a_fenzi, int a_fenmu, int b_fenzi, int b_fenmu);
void testEqual(int a_fenzi, int a_fenmu, int b_fenzi, int b_fenmu);

int main(void) {
    //���Է�ĸΪ0�쳣
    try {
        Fraction f1(2, 0);
    }
    catch (runtime_error ex) {
        cout << "�쳣: " << ex.what() << endl;
    }
    cout << "====================================\n";

    //����˫�κ͵��ι��캯��
    cout << Fraction(2, -3) << " " << Fraction(2) << endl;
    cout << "====================================\n";

    // ������������غ����� < �� ==
    testEqual(4, 8, 1, 2);
    testEqual(4, 3, 3, 2);
    cout << "====================================\n";

    //��������ת������
    double val = Fraction(8,3);
    cout << Fraction(8, 3) << " ==>> "<< val << endl;
    cout << "====================================\n";

    //���ԼӼ��˳���������
    testCalc(5, 6, 3, 8);
    testCalc(5, -6, 3, 8);
    cout << "====================================\n";

    //�����������
    Fraction(4, 8).show();

    system("pause");
    return 0;
}

void testCalc(int a_fenzi, int a_fenmu, int b_fenzi, int b_fenmu) {
    int a_num = a_fenzi, a_den = a_fenmu, b_num = b_fenzi, b_den = b_fenmu; //����--numerator�� ��ĸ--Denominator

    Fraction a(a_num, a_den), b(b_num, b_den);

    cout << a << " + " << b << " = " << a + b << " = " << (a + b).getSim() << endl;
    cout << a << " - " << b << " = " << a - b << " = " << (a - b).getSim() << endl;
    cout << a << " * " << b << " = " << a * b << " = " << (a * b).getSim() << endl;
    cout << a << " / " << b << " = " << a / b << " = " << (a / b).getSim() << endl;
}

void testEqual(int a_fenzi, int a_fenmu, int b_fenzi, int b_fenmu) {
    int a_num = a_fenzi, a_den = a_fenmu, b_num = b_fenzi, b_den = b_fenmu;
    Fraction c(a_num, a_den), d(b_num, b_den);

    cout << c << " < " << d << " ? " << (c < d) << endl;
    cout << c << " == " << d << " ? " << (c == d) << endl;
}
