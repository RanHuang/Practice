#include<iostream>
#include<map>
#include<vector>
#include<string>

using namespace std;

void TestTransformOne();
void TestTransformTwo();
int main()
{
//    TestTransformOne();

    TestTransformTwo();

    system("pause");
    return 0;
}

void TestTransformOne() {
    map<long, string> TransMap = { { 0, "零" },
    { 1, "壹" },{ 2, "贰" },{ 3, "叁" },{ 4, "肆" },{ 5, "伍" },
    { 6, "陆" },{ 7, "柒" },{ 8, "捌" },{ 9, "玖" },{ 10, "拾" },
    { 100, "佰" },{ 1000, "仟" },{ 10000, "万" },{ 100000000L, "亿" }
    };
    double money = 1234001; //不能使用float类型，超过十万级别精度存在问题，eg：1234001

    while (true) {
        //Error: 输入1234500.4，输出转换为1234500.39，有误
        cout << "输入金额：";
        cin >> money;
        long long n = (long long)(money * 1000 + 5) / 10; //四舍五入精确到分
        cout << "整数数额: " << n << endl;
        //cout << "*" << TransMap[powl(10, 8)] << endl; //输出“亿”

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
            if (rem == 0) {
                if (!zero) {
                    result = TransMap[0] + result;
                    zero = true;
                }
            }
            else // 余数不为0
            {
                zero = false;
                switch (digit % 4)
                {
                case 0:
                    if (digit == 0) break;
                    if (digit % 8 == 0)
                        result = TransMap[powl(10, 8)] + result;
                    else
                        result = TransMap[powl(10, 4)] + result;
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

        cout << "转换后的结果(最高精度百万亿): " << result << endl;
    }
}
/*
函数体中的算法：从最低位向最高位逐位转换，并在合适位添加“佰”“仟”“万”“亿”
TODO：
    1. 先转换数字，最后在合适位添加表示位值得汉字
    2. 每四位一组进行处理，然后在合适的位置加上“万”“亿”
 */

//从最高位开始逐位转换，该算法比较复杂，以下转换代码还存在问题
void TestTransformTwo() {
    map<int, string> NumeralToCharacter = { 
        { 0, "零" }, { 1, "壹" },{ 2, "贰" },{ 3, "叁" },{ 4, "肆" },
        { 5, "伍" }, { 6, "陆" },{ 7, "柒" },{ 8, "捌" },{ 9, "玖" }
    };
    vector<string> AppendCharacter = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟" };

    double inMoney = .0;
    while (true) {
        cout << "输入浮点数金额: ";
        cin >> inMoney;

        long long money = inMoney * 100 + 0.5;
        int digit = 0;
        long long temp = money;
        while (temp) {
            ++digit;
            temp /= 10;
        }
        cout << "数位: " << digit << endl;
        
        if (digit > 13) {
            cout << "数值太大，拒绝处理！(不是我瞧不起你，你似乎不可能有那么多钱，请重新输入)\n";
            continue;
        }

        string result = "";
        temp = money;
        long long div = powl(10, digit-1);

        bool continuedZero = false;
        while (digit) {
            int index = temp / div;
            if (index == 0) {
                continuedZero = true;
                if ((digit - 3 >= 0) && ((digit - 3) % 4 == 0)) {
                    result += AppendCharacter[digit - 1];
                }
            }
            else
            {
                if (continuedZero) {
                    result += NumeralToCharacter[0];
                    continuedZero = false;
                }
                result += NumeralToCharacter[index] + AppendCharacter[digit - 1];                
            }            
            temp %= div;
            div /= 10;
            --digit;
        }

        if (money % 1000 == 0) result += "元整";

        cout << "转换后的结果: " << result << endl;
    }
}