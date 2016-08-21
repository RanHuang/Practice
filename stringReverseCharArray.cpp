#include<iostream>
#include<cstring>
using namespace std;

int getRev(char a[]);
void swapInt(int &a, int &b);
const int StrMaxLen = 10;
const int StrMaxNum = 10;

int main(void)
{
    char str[StrMaxLen + 1];
    char strArray[StrMaxNum][StrMaxLen + 1];
    int result[2][StrMaxNum];

    int cnt = 0;
    char ch;
    while (cnt < StrMaxNum) {
        cout << "����һ��ֻ����26����дӢ����ĸ���ַ��� No:" <<cnt<< endl;
        /* ��ջ�������״̬λ��λ��*/
        //cin.clear();
        //cin.ignore();
        /*
        һ�ֽ�������ǣ�ʹ����ʱ����һ���Զ������������ַ���Ȼ���ȡ����������StrMaxLen�������ַ�
        */
        cin.getline(str, StrMaxLen); //�����ַ���������StrMaxLen����Ϊ�쳣        
        if (strlen(str) == 0) break;
        strcpy_s(strArray[cnt], str); //�ƺ��ַ�����ȫ��������_s��VC����Ч���������������޷�ʶ�� 
        result[0][cnt] = cnt;
        result[1][cnt] = getRev(strArray[cnt]);
        ++cnt;       
    }

    for (int i = 0; i < cnt-1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < cnt; j++) {
            if (result[1][minIndex] > result[1][j]) {
                minIndex = j;
            }
        }
        swapInt(result[1][i], result[1][minIndex]);
        swapInt(result[0][i], result[0][minIndex]);
    }

    for (int i = 0; i < cnt; i++) {
        cout << strArray[ result[0][i] ] << " ��������Ϊ��" << result[1][i] << endl;
    }
    
    system("pause");
    return 0;
}

int getRev(char a[])
{
    int cnt = 0;
    int len = strnlen_s(a, StrMaxLen);
    for (int i = 0; i < len - 1; i++) {
        for (int j = i + 1; j < len; j++) {
            if (a[i] > a[j]) {
                ++cnt;
            }
        }
    }
    return cnt;
}

void swapInt(int &a, int &b) {
    int t;
    t = a;
    a = b;
    b = t;
}
