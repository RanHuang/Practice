#include<iostream>
#include<map>
#include<string>
using namespace std;
int main()
{
    map<long, string> TransMap = { {0, "��"},
        {1, "Ҽ"}, {2, "��"}, {3, "��"}, {4, "��"},{5, "��"},
        {6, "½"}, {7, "��"}, {8, "��"}, {9, "��"}, {10, "ʰ"},
        {100, "��"}, {1000, "Ǫ"}, {10000, "��"},{100000000L, "��"}
    };
    double money = 1234001; //����ʹ��float���ͣ����ȴ������⣬eg��1234001
    cout << "�����" ;
    cin >> money;
    long long n = (long long)(money * 100);
    cout << "��������: " << n << endl;
    cout << "*" << TransMap[powl(10, 8)] << endl;
    
  //  n = 10000000000; //long���ʹ洢��ֵ����10^9����ǧ�ڼ���ʱ���
  //  cout << "n= " << n << endl;

    //����С��
    string result = " ";
    int rem = n % 100;
    if (rem) {
        result = TransMap[n % 10] + "��" + result;
        n /= 10;
        result = TransMap[n % 10] + "��" + result;
        n /= 10;
        result = "Ԫ" + result;
    }
    else
    {
        result = "Ԫ��" + result;
        n /= 100;
    }
    //��������
    int digit = 0; //����λ��

    while (n % 10 == 0) {
        n /= 10;
        ++digit;
    }
    if (digit >= 8) {
        result = TransMap[powl(10, 8)] + result;
        digit -= 8;  //��Ϊһ��ѭ��
    }
    else if (digit >= 4) {
        result = TransMap[powl(10, 4)] + result;
    }

    bool zero = false;
    while (n) {        
        rem = n % 10;        
        if (rem == 0 ) {
            if (!zero) {
                result = TransMap[0] + result;
                zero = true;
            }              
        }
        else // ������Ϊ0
        {
            zero = false;
            switch (digit%4)
            {
            case 0:
                if (digit == 0) break;
                result = TransMap[powl(10, digit)] + result;
                break;
            case 1:
                result = TransMap[10] + result;
                break;
            case 2:
                result = TransMap[100] + result;
                break;
            case 3:
                result = TransMap[1000] + result;
                break;
            default:
                break;
            }
            result = TransMap[rem] + result;
        }
        ++digit;
        n /= 10;
    }

    cout << "ת����Ľ��: " << result<< endl;

    system("pause");
    return 0;
}