#include"Person.h"
#include<iostream>
#include<set>
#include<map>
#include<string>
#include<fstream>
#include<sstream>

using namespace std;
template <class T>
void print_collection(const T& t) {
	cout << t.size() << " elements: " << endl;
	for (const auto& p : t)
		print_elem(p);
	cout << endl;
}

template <class T>
void print_elem(const T & p) {
	cout << p;
}

template <typename T, typename S>
void print_elem(const pair<T, S> &p) {
    cout << p.first << ": " << p.second << endl;
}

template<typename T>
void MultisetToMap(const multiset<T> sour, map<T, int> &dest) {
    for (const auto& item : sour) {
        if (dest.find(item) == dest.end()) {
            dest[item] = 1;
        }
        else
        {
            ++dest[item];
        }
    }
}

void showPrompt();
bool readFile(const string &&fileName, set<Person> &data);
void calcNameSex(const set<Person> &data);
void calcGreaterTwenty(const set<Person> &data);
void calcSexNumber(const set<Person> &data);
void calcSameBirth(const set<Person> &data);
void calcSameBirth(const set<Person> &data);
void calcSexAvgAge(const set<Person> &data);
void calcSexMaxAge(const set<Person> &data);

int main() {	
    set<Person> roster;
    readFile("persons.txt", roster);

    do {
        showPrompt();
        int i = 0;
        cin >> i;
        cin.get(); //清楚换行符

        cout << "----------------Result--------------------\n";
        switch (i)
        {            
        case 1:
            cout << "\t显示从文件中读取的数据:\n";
            print_collection(roster);
            break;
        case 2:
            calcNameSex(roster);
            break;
        case 3:
            calcGreaterTwenty(roster);
            break;
        case 4:
            calcSexNumber(roster);
            break;
        case 5:
            calcSameBirth(roster);
            break;
        case 6:
            calcSexAvgAge(roster);
            break;
        case 7:
            calcSexMaxAge(roster);
            break;
        case 0:
            goto end; //结束while循环
        default:
            cout << "菜单选项输入有误，请重新输入\n";
            break;
        }
        cout << "Press Enter to continue: " << endl;
        cin.get();
    } while (true);

end:
	system("pause");
	return 0;
}

bool readFile(const string &&fileName, set<Person> &data)
{
    ifstream  infile(fileName, ios::in);
    if (!infile) {
        cout << "不能打开输入文件persons.txt!" << '\n';
        system("pause");
        return 0;
    }
    char buff[300];

    string name;
    Person::Sex gender;
    //Date bd;
    string phone;

    while (infile.getline(buff, 300)) {
        istringstream stream(buff);
        string field;
        stream >> name;			//1
        stream >> field;			//2
        if (field == "男") gender = Person::MALE;
        else if (field == "女") gender = Person::FEMALE;
        char bd[20];
        stream >> bd;				//yyyy/m/d  3
        int year, month, day;
        sscanf_s(bd, "%d/%d/%d", &year, &month, &day);
        stream >> field;			//4
        phone = field;
        data.insert(Person(name, gender, year, month, day, phone));
    }
    infile.close();
}

void showPrompt()
{
    cout<<"\t\t功能菜单\n"
        <<"1.显示从文件中读取的数据\n"
        <<"2.统计显示姓名与性别\n"
        <<"3.20岁以上的人员姓名及其生日\n"
        <<"4.统计各性别的人数，0为Male,1为Female\n"
        <<"5.统计生日相同的人\n"
        <<"6.统计按性别分组的人员的平均年龄\n"
        <<"7.统计各性别组中最大年龄的人信息\n"
        <<"0.Exit\n"
        <<"Input: ";
}

//2. 统计显示姓名与性别
void calcNameSex(const set<Person> &data) {
    cout << "姓名->性别，性别1为Male，0为Female：" << endl;
    map<string, Person::Sex> p_name_sex;
    for (const auto& per : data) {
        p_name_sex[per.getName()] = per.getGender();
    }
    print_collection(p_name_sex);
    cout << "Jane 的性别是：" << p_name_sex["Jane"] << endl << endl;
    cout << endl;
}
//3. 20岁以上的人员姓名及其生日
void calcGreaterTwenty(const set<Person> &data) {
    map<string, Date> p_name_birthday;
    for (const auto &per : data) {
        if (per.getAge() > 20) p_name_birthday[per.getName()] = per.getBirthdate();
    }
    print_collection(p_name_birthday);
    cout << endl;    
}

//4. 统计各性别的人数，0为Male,1为Female
void calcSexNumber(const set<Person> &data) {
    map<Person::Sex, int> p_sex_count;
    for (const auto &per : data) {
        if (p_sex_count.find(per.getGender()) == p_sex_count.end()) {
            p_sex_count[per.getGender()] = 1;
        }
        else
        {
            ++p_sex_count[per.getGender()];
        }
    }
    print_collection(p_sex_count);

    cout << "另一种方法计算各性别的计数：多集转换映射" << endl;
    p_sex_count.clear();
    multiset<Person::Sex> p_sex;
    for (const auto &item : data) {
        p_sex.insert(item.getGender());
    }
    MultisetToMap(p_sex, p_sex_count);
    print_collection(p_sex_count);
}

//5.统计生日相同的人
void calcSameBirth(const set<Person> &data) {
    multiset<Date> dates;
    map<Date, int> p_birthday_count; // map<string, Date>
    for (const auto &per : data) {
        dates.insert(per.getBirthdate());
    }
    MultisetToMap(dates, p_birthday_count);
    print_collection(p_birthday_count);
    for (const auto &item : p_birthday_count) {
        if (item.second > 1) {
            for (const auto &per : data) {
                if (per.getBirthdate() == item.first)
                    cout << per;
            }
        }
    }
    cout << endl;
}

//6.统计按性别分组的人员的平均年龄
void calcSexAvgAge(const set<Person> &data) {
    map<Person::Sex, int> p_sex_avgage;
    int malecnt = 0;
    for (const auto &per : data) {
        if (per.getGender() == Person::MALE) {
            ++malecnt;
            p_sex_avgage[Person::MALE] += per.getAge();
        }
        else
        {
            p_sex_avgage[Person::FEMALE] += per.getAge();
        }
    }
    p_sex_avgage[Person::MALE] /= malecnt;
    p_sex_avgage[Person::FEMALE] /= data.size() - malecnt;
    print_collection(p_sex_avgage);
}

//7.统计各性别组中最大年龄的人信息
void calcSexMaxAge(const set<Person> &data) {
    map<Person::Sex, const Person*> p_sex_maxage = { { Person::MALE, nullptr },{ Person::FEMALE, nullptr } };
    for (auto const &per : data) {
        if (p_sex_maxage[per.getGender()] == nullptr) {
            p_sex_maxage[per.getGender()] = &per;
        }
        else if (per.getBirthdate() < p_sex_maxage[per.getGender()]->getBirthdate())
        {
            //getBirthdate()值越小者，出生越早，年龄越大
            p_sex_maxage[per.getGender()] = &per;
        }
    }
    cout << "个性别组年龄最大者:\n";
    cout << *(p_sex_maxage[Person::FEMALE]) << *(p_sex_maxage[Person::MALE]) << endl;

}