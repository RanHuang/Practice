#include<iostream>
#include<map>
#include<vector>
#include<algorithm>
#include<set>
#include<fstream>
using namespace std;

//n个整数出现频次统计 
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

    cout << "词频统计结果: " << endl;
    for (const auto &item : integer_count) {
        cout << item.first << " " << item.second << endl;
        count.push_back(IntCount(item.first, item.second));
    }

    sort(count.begin(), count.end(), [](const IntCount &a, const IntCount &b) { return a.cnt > b.cnt; });

    cout << "按照数频排序结果: " << endl;
    for (const auto &item : count) {
        cout << item.num << " " << item.cnt << endl;
    }
}
//多集转换为映射--一般用于统计多集中元素个数
template<typename T>
void Multiset2Map(multiset<T> &from, map<T, int> &dest) {
    for (const auto &item : from) {
        ++dest[item];
    }
}
//输出容器集合中元素的通用函数
template<typename T>
void print_collection(const T &collection) {
    cout << "集合中元素个数：" << collection.size() << endl;
    for (const auto &item : collection) {
        print_element(item);
    }
    cout << endl;
}
//用于输出map元素值
template<typename T, typename S>
void print_element(const pair<T, S> &data) {
    cout << data.first << " " << data.second<<endl;
}

//从文件中读取数据
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
        throw runtime_error("无法打开文件：" + fileName);
    }    
}

//利用map和multimap容易的默认有序（升序）性质
void test() {
    multiset<int> data;
    readFromFile("someIntData.txt", data);

    map<int, int> count;
    Multiset2Map(data, count);

    cout << "统计后的结果: \n";
    print_collection(count);

    multimap<int, int> sorted_count;
    for (auto item : count) {
        sorted_count.emplace(item.second, item.first);
    }
    cout << "转换为多集的结果: \n";
    print_collection(sorted_count);

    cout << "降序排列的结果: \n";
    //multiset默认升序，逆序输出
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