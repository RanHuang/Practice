//date4.h
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <time.h>
#include<iostream>
using namespace std;
class Date{
	int year, month, day;
public:
	Date(int y, int m, int d){				//构造函数1
		year = y;	
		month = m; 
		day = d;
	}
	Date(){									//缺省构造函数
		time_t ltime = time(NULL);
		tm * today = localtime(&ltime);	
		year = today->tm_year + 1900;	
		month = today->tm_mon + 1;	
		day = today->tm_mday;	
	}
	int getYear()const{return year;}
	int getMonth()const{return month;}
	int getDay()const{return day;}
	int getInt()const { return year * 10000 + month * 100 + day; }
	bool isLeapYear()const{
		return year%400 == 0 || year%4 == 0 && year%100 != 0;
	}
	int getAge()const{
		time_t ltime = time(NULL);			//取得当前时间
		tm * today = localtime(&ltime);		//转换为本地时间
		int ctyear = today->tm_year + 1900;	//取得当前年份
		int cmonth = today->tm_mon + 1;
		int cday = today->tm_mday;
		if (cmonth > month ||
			cmonth == month && cday >= day)
			return ctyear - year;
		else
			return ctyear - year - 1;
	}
	void print()const{
		cout<<year<<"."<<month<<"."<<day;
	}
	friend ostream & operator<<(ostream & os, const Date &d);
	friend bool operator<(const Date & d1, const Date & d2);
	bool operator== (const Date & d) {
		return this->getInt() == d.getInt();
	}
};
ostream & operator<<(ostream & os, const Date &d) {
	os << d.year << "-" << d.month << "-" << d.day;
	return os;
}
bool operator<(const Date & d1, const Date & d2) {
	return d1.getInt() < d2.getInt();
}

