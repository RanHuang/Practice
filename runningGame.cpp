#include<iostream>
#include<vector>
using namespace std;
/*  
 * 4-100�˲μӰ������ܣ�8���ܵ������������飬����������Ŀ���٣�ÿ������������٣�������1��
 * ����������ÿ�����룬һ����һ��Ψһ��
 */
int main() 
{
    cout << "����μӰ��׶�������[4,100]: ";
    int num = 0;
    vector<int> num_session;
    cin >> num;

    //���ٱ�������
    int session = num / 8 + (num % 8 ? 1 : 0);
    cout << "���ٱ�������: " << session << endl;
    int average = num / session; //ƽ��ÿ������
    int remainder = num - average*session; //ʣ�಻�ܷ��������
    for (int i = 1; i <= remainder; i++) {
        num_session.push_back(average + 1);
    }
    for (int i = 1; i <= session - remainder; i++) {
        num_session.push_back(average);
    }

    cout << "ÿ�����������\n\t";
    for (const auto &item : num_session) {
        cout << item << " ";
    }
    cout << endl;

    system("pause");
    return 0;
}