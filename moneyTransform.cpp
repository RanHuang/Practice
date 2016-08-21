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
    map<long, string> TransMap = { { 0, "��" },
    { 1, "Ҽ" },{ 2, "��" },{ 3, "��" },{ 4, "��" },{ 5, "��" },
    { 6, "½" },{ 7, "��" },{ 8, "��" },{ 9, "��" },{ 10, "ʰ" },
    { 100, "��" },{ 1000, "Ǫ" },{ 10000, "��" },{ 100000000L, "��" }
    };
    double money = 1234001; //����ʹ��float���ͣ�����ʮ�򼶱𾫶ȴ������⣬eg��1234001

    while (true) {
        //Error: ����1234500.4�����ת��Ϊ1234500.39������
        cout << "�����";
        cin >> money;
        long long n = (long long)(money * 1000 + 5) / 10; //�������뾫ȷ����
        cout << "��������: " << n << endl;
        //cout << "*" << TransMap[powl(10, 8)] << endl; //������ڡ�

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
            if (rem == 0) {
                if (!zero) {
                    result = TransMap[0] + result;
                    zero = true;
                }
            }
            else // ������Ϊ0
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

        cout << "ת����Ľ��(��߾��Ȱ�����): " << result << endl;
    }
}
/*
�������е��㷨�������λ�����λ��λת�������ں���λ��ӡ��ۡ���Ǫ�����򡱡��ڡ�
TODO��
    1. ��ת�����֣�����ں���λ��ӱ�ʾλֵ�ú���
    2. ÿ��λһ����д���Ȼ���ں��ʵ�λ�ü��ϡ��򡱡��ڡ�
 */

//�����λ��ʼ��λת�������㷨�Ƚϸ��ӣ�����ת�����뻹��������
void TestTransformTwo() {
    map<int, string> NumeralToCharacter = { 
        { 0, "��" }, { 1, "Ҽ" },{ 2, "��" },{ 3, "��" },{ 4, "��" },
        { 5, "��" }, { 6, "½" },{ 7, "��" },{ 8, "��" },{ 9, "��" }
    };
    vector<string> AppendCharacter = { "��", "��", "Ԫ", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ" };

    double inMoney = .0;
    while (true) {
        cout << "���븡�������: ";
        cin >> inMoney;

        long long money = inMoney * 100 + 0.5;
        int digit = 0;
        long long temp = money;
        while (temp) {
            ++digit;
            temp /= 10;
        }
        cout << "��λ: " << digit << endl;
        
        if (digit > 13) {
            cout << "��ֵ̫�󣬾ܾ�����(�������Ʋ����㣬���ƺ�����������ô��Ǯ������������)\n";
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

        if (money % 1000 == 0) result += "Ԫ��";

        cout << "ת����Ľ��: " << result << endl;
    }
}