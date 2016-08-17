#include<iostream>
#include<map>
#include<vector>
#include<algorithm>
#include<set>
#include<fstream>
using namespace std;

//n����������Ƶ��ͳ�� 
void InputCountSort() { 
    const int MAX_SIZE = 20;
    struct IntCount {
        int num;
        size_t cnt;
        IntCount(int n, size_t c) {
            num = n;
            cnt = c;
        }
    };
    int n = 6;
    int array[MAX_SIZE] = { 2,3,3,5,3,5 };

    map<int, size_t> integer_count;
    vector<IntCount> count;
    for (int i = 0; i<n; ++i) {
        ++integer_count[array[i]];
    }

    cout << "��Ƶͳ�ƽ��: " << endl;
    for (const auto &item : integer_count) {
        cout << item.first << " " << item.second << endl;
        count.push_back(IntCount(item.first, item.second));
    }

    sort(count.begin(), count.end(), [](const IntCount &a, const IntCount &b) { return a.cnt > b.cnt; });

    cout << "������Ƶ������: " << endl;
    for (const auto &item : count) {
        cout << item.num << " " << item.cnt << endl;
    }
}
//�༯ת��Ϊӳ��--һ������ͳ�ƶ༯��Ԫ�ظ���
template<typename T>
void Multiset2Map(multiset<T> &from, map<T, int> &dest) {
    for (const auto &item : from) {
        ++dest[item];
    }
}
//�������������Ԫ�ص�ͨ�ú���
template<typename T>
void print_collection(const T &collection) {
    cout << "������Ԫ�ظ�����" << collection.size() << endl;
    for (const auto &item : collection) {
        print_element(item);
    }
    cout << endl;
}
//�������mapԪ��ֵ
template<typename T, typename S>
void print_element(const pair<T, S> &data) {
    cout << data.first << " " << data.second<<endl;
}

//���ļ��ж�ȡ����
void readFromFile(const string &&fileName, multiset<int> &collection) {
    ifstream input(fileName);
    if (input) {
        int num;
        while (input >> num) {
            collection.insert(num);
        }
        input.close();
    }
    else
    {
        throw runtime_error("�޷����ļ���" + fileName);
    }    
}

//����map��multimap���׵�Ĭ��������������
void test() {
    multiset<int> data;
    readFromFile("someIntData.txt", data);

    map<int, int> count;
    Multiset2Map(data, count);

    cout << "ͳ�ƺ�Ľ��: \n";
    print_collection(count);

    multimap<int, int> sorted_count;
    for (auto item : count) {
        sorted_count.emplace(item.second, item.first);
    }
    cout << "ת��Ϊ�༯�Ľ��: \n";
    print_collection(sorted_count);

    cout << "�������еĽ��: \n";
    //multisetĬ�������������
    for (auto iter = sorted_count.crbegin(); iter != sorted_count.crend(); ++iter) {
        cout << iter->second << " " << iter->first << endl;
    }    
}

int main(){
    cout << "*********Method 1*************" << endl;
    InputCountSort();
    cout << "*********Method 2*************" << endl;
    test();

	system("pause");
	return 0;
}