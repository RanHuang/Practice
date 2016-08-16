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
        if (n % 5 == 0) { //����5�ı������֣��������б�����ı�Ÿ���
            for (int i = p; i < num-1; i++) {
                josephCircle[i] = josephCircle[i + 1];
            }
            --num; //�μ���Ϸ��������
        }
        else
        {
            ++p; //��δ���֣���ָʾ��һ����Ҫ���ŵ���
        }
        if (p%num == 0) p = 0; //ȷ�����ɻ�
    }

    cout << "NUM�˵�Լɪ����Ϸ��������µ��˵ı��Ϊ: "<< josephCircle[0] << endl;
    cout << "�����˳���ʱ��������Ϊ��" << n + 5 << endl;

    system("pause");
    return 0;
}