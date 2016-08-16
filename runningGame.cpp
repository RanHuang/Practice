#include<iostream>
#include<vector>
using namespace std;
/*  
 * 4-100人参加百米赛跑，8个跑道，问怎样分组，比赛的组数目最少，每组人数相差最少（最大相差1）
 * 分析：对于每个输入，一定有一个唯一解
 */
int main() 
{
    cout << "输入参加百米短跑人数[4,100]: ";
    int num = 0;
    vector<int> num_session;
    cin >> num;

    //最少比赛场次
    int session = num / 8 + (num % 8 ? 1 : 0);
    cout << "最少比赛场次: " << session << endl;
    int average = num / session; //平均每组人数
    int remainder = num - average*session; //剩余不能分组的人数
    for (int i = 1; i <= remainder; i++) {
        num_session.push_back(average + 1);
    }
    for (int i = 1; i <= session - remainder; i++) {
        num_session.push_back(average);
    }

    cout << "每组比赛人数：\n\t";
    for (const auto &item : num_session) {
        cout << item << " ";
    }
    cout << endl;

    system("pause");
    return 0;
}