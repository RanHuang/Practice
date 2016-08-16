#include<iostream>
#include<map>
#include<string>
using namespace std;
int main()
{
    map<long, string> TransMap = { {0, "零"},
        {1, "壹"}, {2, "贰"}, {3, "叁"}, {4, "肆"},{5, "伍"},
        {6, "陆"}, {7, "柒"}, {8, "捌"}, {9, "玖"}, {10, "拾"},
        {100, "佰"}, {1000, "仟"}, {10000, "万"},{100000000L, "亿"}
    };
    double money = 1234001; //不能使用float类型，精度存在问题，eg：1234001
    cout << "输入金额：" ;
    cin >> money;
    long long n = (long long)(money * 100);
    cout << "整数数额: " << n << endl;
    cout << "*" << TransMap[powl(10, 8)] << endl;
    
  //  n = 10000000000; //long类型存储数值超过10^9，在千亿级别时溢出
  //  cout << "n= " << n << endl;

    //处理小数
    string result = " ";
    int rem = n % 100;
    if (rem) {
        result = TransMap[n % 10] + "分" + result;
        n /= 10;
        result = TransMap[n % 10] + "角" + result;
        n /= 10;
        result = "元" + result;
    }
    else
    {
        result = "元整" + result;
        n /= 100;
    }
    //处理整数
    int digit = 0; //处理位数

    while (n % 10 == 0) {
        n /= 10;
        ++digit;
    }
    if (digit >= 8) {
        result = TransMap[powl(10, 8)] + result;
        digit -= 8;  //亿为一个循环
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
        else // 余数不为0
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

    cout << "转换后的结果: " << result<< endl;

    system("pause");
    return 0;
}