#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

//���ֺ��ӣ�Lʽ������ָ�롢��������
//Lambda���ʽ
void printVecInt(const vector<int> &vec) {
    for_each(vec.begin(), vec.end(), [](auto val) {cout << val << " "; });
    cout << endl;
}
//����ָ��
bool lessThan(int value) {
    return value < 6;
}
//��������
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
    vector<int> vecInt = { 1,2,3,4,5,6,7,8,9};
    vecInt.push_back(10);
    
    random_shuffle(vecInt.begin(), vecInt.end());
    cout << "���������vector��" << endl;
    for (const auto &item : vecInt)
        cout << item << " ";
    cout << endl<<endl;

    partition(vecInt.begin(), vecInt.end(), lessThan);
    cout << "��6Ϊ����飺" << endl;
    printVecInt(vecInt);
    cout << endl;

    random_shuffle(vecInt.begin(), vecInt.end());
    cout << "���������vector��" << endl;
    printVecInt(vecInt);
    cout << endl;

    partition(vecInt.begin(), vecInt.end(), [](auto value) {return value > 5; });
    cout << "��5Ϊ����飺" << endl;
    printVecInt(vecInt);
    cout << endl;

    vector<int> vec(10); //���ɺ���10��������vector��ֵ��ʼ��Ϊ0
    generate(vec.begin(), vec.end(), [] {return rand() % 100 + 1; });
    cout << "����10��[1,100]�����������" << endl;
    printVecInt(vec);
    cout << endl;

    int eventCnt = 0;
    generate(vec.begin(), vec.end(), MyRand(eventCnt));
    cout << "����10��[1,20]�����������" << endl;
    printVecInt(vec);
    cout << "����ż���ĸ���Ϊ��" << eventCnt << endl;

    system("pause");
    return 0;
}