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
        cout << "输入一个只包含26个大写英文字母的字符串 No:" <<cnt<< endl;
        /* 清空缓冲区，状态位复位；*/
        //cin.clear();
        //cin.ignore();
        /*
        一种解决方案是，使用临时变量一次性读入所有输入字符，然后截取拷贝不超过StrMaxLen数量的字符
        */
        cin.getline(str, StrMaxLen); //输入字符个数超过StrMaxLen，行为异常        
        if (strlen(str) == 0) break;
        strcpy_s(strArray[cnt], str); //似乎字符串安全操作函数_s在VC中有效，但是其它环境无法识别 
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
        cout << strArray[ result[0][i] ] << " 的逆序数为：" << result[1][i] << endl;
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
