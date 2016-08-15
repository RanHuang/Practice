#pragma once
#ifndef _FRACTION_H_
#define _FRACTION_H_

#include<stdexcept>
#include<iostream>
using namespace std;

class Fraction {
private:
    int numerator;
    int denominator;
public:
    Fraction() = default;
    Fraction(int num, int den) {
        if (den == 0) throw runtime_error("分母不能为0！分子为：" + num); 
        if (den < 0) {
            denominator = -den;
            numerator = -num;
        }
        else
        {
            denominator = den;
            numerator = num;
        }
    }

    friend ostream& operator<<(ostream &os, const Fraction &item);
    bool operator==(const Fraction &b);
    friend bool operator<(const Fraction &a, const Fraction &b);
    Fraction operator +(const Fraction &b);
    Fraction operator -(const Fraction &b);
    Fraction operator *(const Fraction &b);
    Fraction operator /(const Fraction &b);
    operator double();
private:
    //计算最大公约数
    int getGreatestCommonDivisor(int a, int b) {
        if (a<b) swapInt(a, b);

        int c = 0;
        while (c = a%b) {
            a = b;
            b = c;
        }

        return b;
    }
    //计算最小公倍数
    int getLeastCommonMultiple(int a, int b) {
        return a*b / getGreatestCommonDivisor(a, b);
    }

    void swapInt(int &a, int &b) {
        int t;
        t = a;
        a = b;
        b = t;
    }

};

ostream& operator<<(ostream &os, const Fraction &item) {
    os << item.numerator << "/" << item.denominator;
    return os;
}

bool Fraction::operator==(const Fraction &b) {
    return (numerator*b.denominator == denominator*b.numerator);
}
bool operator<(const Fraction &a, const Fraction &b) {
    return (b.numerator*a.denominator - a.numerator*b.denominator);
}
Fraction Fraction::operator +(const Fraction &b) {
    int den = getLeastCommonMultiple(denominator, b.denominator);
    int num = numerator*den / denominator + b.numerator*den/b.denominator;

    int div = getGreatestCommonDivisor(den, abs(num));
    return Fraction(num / div, den / div);
}
Fraction Fraction::operator -(const Fraction &b) {
    int den = getLeastCommonMultiple(denominator, b.denominator);
    int num = numerator*den / denominator - b.numerator*den / b.denominator;

    int div = getGreatestCommonDivisor(den, abs(num));
    return Fraction(num / div, den / div);
}
Fraction Fraction::operator *(const Fraction &b) {
    int den = denominator*b.denominator;
    int num = numerator*b.numerator;

    int div = getGreatestCommonDivisor(den, abs(num));
    return Fraction(num / div, den / div);
}
Fraction Fraction::operator /(const Fraction &b) {
    int den = denominator*b.numerator;
    int num = numerator*b.denominator;

    if (den < 0) {
        den = -den;
        num = -num;
    }
    int div = getGreatestCommonDivisor(den, abs(num));
    return Fraction(num / div, den / div);
}
Fraction::operator double() {
    return static_cast<double>(numerator) / static_cast<double>(denominator);
}

#endif // !_FRACTION_H_

