#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

//三种函子：L式、函数指针、函数对象
//Lambda表达式
void printVecInt(const vector<int> &vec) {
    for_each(vec.begin(), vec.end(), [](auto val) {cout << val << " "; });
    cout << endl;
}
//函数指针
bool lessThan(int value) {
    return value < 6;
}
//函数对象
class MyRand {
    int &count;
public:
    MyRand(int &cnt):count(cnt){}
    int operator()() {
        int val = rand() % 20 + 1;
        if (val % 2 == 0) ++count;
        return val;
    }
};
int main()
{
    vector<int> arrInt = { 1,2,3,4,5,6,7,8,9};
    arrInt.push_back(10);
    
    random_shuffle(arrInt.begin(), arrInt.end());
    cout << "随机排序后的vector：" << endl;
    for (const auto &item : arrInt)
        cout << item << " ";
    cout << endl<<endl;

    partition(arrInt.begin(), arrInt.end(), lessThan);
    cout << "以6为界分组：" << endl;
    printVecInt(arrInt);
    cout << endl;

    random_shuffle(arrInt.begin(), arrInt.end());
    cout << "随机排序后的vector：" << endl;
    printVecInt(arrInt);
    cout << endl;

    partition(arrInt.begin(), arrInt.end(), [](auto value) {return value > 5; });
    cout << "以5为界分组：" << endl;
    printVecInt(arrInt);
    cout << endl;

    vector<int> vec(10); //生成含有10个整数的vector，值初始化为0
    generate(vec.begin(), vec.end(), [] {return rand() % 100 + 1; });
    cout << "生成10个[1,100]的随机整数：" << endl;
    printVecInt(vec);
    cout << endl;

    int eventCnt = 0;
    generate(vec.begin(), vec.end(), MyRand(eventCnt));
    cout << "生成10个[1,20]的随机整数：" << endl;
    printVecInt(vec);
    cout << "其中偶数的个数为：" << eventCnt << endl;

    system("pause");
    return 0;
}