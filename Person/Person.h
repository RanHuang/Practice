//Person.h
#include"date4.h"
#include<string>
class Person {
public:
	enum Sex {FEMALE, MALE };
private:
	string name;
	Sex gender;
	Date birthdate;
	string phonenum;
public:
	Person() {}
	Person(string name, Sex gender, int by, int bm, int bd, string phone)
		:name(name), gender(gender), birthdate(by, bm, bd), phonenum(phone) {}
	string getName() const{ return name; }
	int getAge() const{ return birthdate.getAge(); }
	Sex getGender() const{ return gender; }
	Date getBirthdate() const { return birthdate; }
	friend bool operator<(const Person & p1, const Person & p2);
	bool operator== (const Person & p) {
		return this->name == p.name;
	}
	void print()const { 
		cout << name << "," << getAge() << "," 
			<<(gender==Sex::FEMALE ? "F" : "M")<<","<<phonenum<<endl;
	}
	friend ostream & operator<<(ostream & os, const Person &p);
};
bool operator<(const Person & p1, const Person & p2) {
	return p1.getName() < p2.getName();
}
ostream & operator<<(ostream & os, const Person &p) {
	os << p.name << "," << p.birthdate << ", Age=" << p.getAge() << ","
		<< (p.gender == Person::Sex::FEMALE ? "F" : "M") << "," << p.phonenum << endl;
	return os;
}