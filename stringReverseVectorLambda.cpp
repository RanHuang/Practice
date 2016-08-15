#include<iostream>
#include<string>
#include<vector>
#include<algorithm>
using namespace std;

int getRev(const string &a);
struct RevString
{
    string mystr;
    int revnum;
    RevString(){}
    RevString(string str, int num) {
        mystr = str;
        revnum = num;
    }
}; 

int main(void)
{
    string str;
    vector<RevString> str_vec;

    cout << "ÊäÈëÈô¸ÉÖ»°üº¬26¸ö´óÐ´Ó¢ÎÄ×ÖÄ¸µÄ×Ö·û´®:" << endl;
    while (getline(cin, str)) {
        if (str.size() == 0) break;
        str_vec.push_back(RevString(str, getRev(str)));
    }
    //¸øÄæÐòÊýÅÅÐò
    sort(str_vec.begin(), str_vec.end(), [](const RevString &a, const RevString &b) 
    {return a.revnum < b.revnum; });

    cout << "Êä³ö×Ö·û´®\n";
    for (auto val : str_vec) {
        cout << val.mystr << " ÄæÐòÊý£º"<<val.revnum<<endl;
    }
    
    system("pause");
    return 0;
}

int getRev(const string &a) {
    decltype(a.size()) size = a.size();
    int cnt = 0;
    for (decltype(a.size()) index = 0; index < size-1; ++index) {
        for (decltype(a.size()) j = index + 1; j < size; ++j) {
            if (a[index] > a[j]) ++cnt;
        }
    }
    return cnt;
}