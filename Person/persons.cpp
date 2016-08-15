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
        cin.get(); //������з�

        cout << "----------------Result--------------------\n";
        switch (i)
        {            
        case 1:
            cout << "\t��ʾ���ļ��ж�ȡ������:\n";
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
            goto end; //����whileѭ��
        default:
            cout << "�˵�ѡ��������������������\n";
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
        cout << "���ܴ������ļ�persons.txt!" << '\n';
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
        if (field == "��") gender = Person::MALE;
        else if (field == "Ů") gender = Person::FEMALE;
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
    cout<<"\t\t���ܲ˵�\n"
        <<"1.��ʾ���ļ��ж�ȡ������\n"
        <<"2.ͳ����ʾ�������Ա�\n"
        <<"3.20�����ϵ���Ա������������\n"
        <<"4.ͳ�Ƹ��Ա��������0ΪMale,1ΪFemale\n"
        <<"5.ͳ��������ͬ����\n"
        <<"6.ͳ�ư��Ա�������Ա��ƽ������\n"
        <<"7.ͳ�Ƹ��Ա�����������������Ϣ\n"
        <<"0.Exit\n"
        <<"Input: ";
}

//2. ͳ����ʾ�������Ա�
void calcNameSex(const set<Person> &data) {
    cout << "����->�Ա��Ա�1ΪMale��0ΪFemale��" << endl;
    map<string, Person::Sex> p_name_sex;
    for (const auto& per : data) {
        p_name_sex[per.getName()] = per.getGender();
    }
    print_collection(p_name_sex);
    cout << "Jane ���Ա��ǣ�" << p_name_sex["Jane"] << endl << endl;
    cout << endl;
}
//3. 20�����ϵ���Ա������������
void calcGreaterTwenty(const set<Person> &data) {
    map<string, Date> p_name_birthday;
    for (const auto &per : data) {
        if (per.getAge() > 20) p_name_birthday[per.getName()] = per.getBirthdate();
    }
    print_collection(p_name_birthday);
    cout << endl;    
}

//4. ͳ�Ƹ��Ա��������0ΪMale,1ΪFemale
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

    cout << "��һ�ַ���������Ա�ļ������༯ת��ӳ��" << endl;
    p_sex_count.clear();
    multiset<Person::Sex> p_sex;
    for (const auto &item : data) {
        p_sex.insert(item.getGender());
    }
    MultisetToMap(p_sex, p_sex_count);
    print_collection(p_sex_count);
}

//5.ͳ��������ͬ����
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

//6.ͳ�ư��Ա�������Ա��ƽ������
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

//7.ͳ�Ƹ��Ա�����������������Ϣ
void calcSexMaxAge(const set<Person> &data) {
    map<Person::Sex, const Person*> p_sex_maxage = { { Person::MALE, nullptr },{ Person::FEMALE, nullptr } };
    for (auto const &per : data) {
        if (p_sex_maxage[per.getGender()] == nullptr) {
            p_sex_maxage[per.getGender()] = &per;
        }
        else if (per.getBirthdate() < p_sex_maxage[per.getGender()]->getBirthdate())
        {
            //getBirthdate()ֵԽС�ߣ�����Խ�磬����Խ��
            p_sex_maxage[per.getGender()] = &per;
        }
    }
    cout << "���Ա������������:\n";
    cout << *(p_sex_maxage[Person::FEMALE]) << *(p_sex_maxage[Person::MALE]) << endl;

}