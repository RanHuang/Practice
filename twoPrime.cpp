#include<iostream>
using namespace std;

bool isPrime(int n);
int findPrime(int n);

void test(int n) {
    if (n < 1) {
        cout << "������������!" << endl;
        return;
    }
    int result = findPrime(n);

    cout << result << " " << result + 2 << endl;
    //cout << "���ڻ����" << n << "����һ������������(" << result << "," << result + 2 << ")" << endl;
}

int main(void)
{
   
    cout << "������һ��������: ";
    int num;
    cin >> num;
    test(num);
    
    /*
    test(-1);
    test(3);
    test(8);
    */
    system("pause");
    return 0;
}

bool isPrime(int n) {
    if (n < 2) return false;
    if (n == 2 || n == 3 || n == 5) return true;

    if (n % 2 == 0) return false;
    int squareroot = (int)sqrt(n);
    for (int i = 3; i <= squareroot; i += 2) {
        if (n%i == 0) return false;
    }
    return true;
}

int findPrime(int n) {
    if (n % 2 == 0) ++n;
    while (!isPrime(n) || !isPrime(n + 2)) {
        n += 2;
    }

    return n;
}
