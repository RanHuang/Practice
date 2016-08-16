#include<iostream>
using namespace std;
const int NUM = 100;
int main()
{
    int josephCircle[NUM];
    for (int i = 0; i < NUM; i++) {
        josephCircle[i] = i + 1;
    }

    int num = NUM;
    int n = 0;
    int p = 0;
    while (num > 1) {
        ++n;
        if (n % 5 == 0) { //报到5的倍数出局，在数组中被后面的编号覆盖
            for (int i = p; i < num-1; i++) {
                josephCircle[i] = josephCircle[i + 1];
            }
            --num; //参加游戏人数减少
        }
        else
        {
            ++p; //如未出局，则指示下一个将要报号的人
        }
        if (p%num == 0) p = 0; //确保构成环
    }

    cout << "NUM人的约瑟夫环游戏，最后留下的人的编号为: "<< josephCircle[0] << endl;
    cout << "所有人出局时报的数字为：" << n + 5 << endl;

    system("pause");
    return 0;
}