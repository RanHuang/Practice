#pragma once
#ifndef _FRACTION_H_
#define _FRACTION_H_

#include<stdexcept>
#include<iostream>

class Fraction {
private:
    int fenzi;
    int fenmu;
public:
    Fraction() :fenzi(0), fenmu(1) {} //缺省构造函数，分子为0，分母为1
    Fraction(int num, int den) {
        //分母为0则异常
        if (den == 0) throw std::runtime_error("分母不能为0!");
        //如果分子或分母为负数，则确保分母非负
        if (den < 0) {
            fenmu = -den;
            fenzi = -num;
        }
        else
        {
            fenmu = den;
            fenzi = num;
        }
    }
    Fraction(int num) { //分母=1,构造函数
        fenzi = num;
        fenmu = 1;
    }

    Fraction &getSim(); //化简分子分母，而且使分母>0
    bool operator==(const Fraction &b);
    bool operator<(const Fraction &b);
    operator double(); //类型转换到double

    friend Fraction operator +(const Fraction &a, const Fraction &b);
    friend Fraction operator -(const Fraction &a, const Fraction &b);
    friend Fraction operator *(const Fraction &a, const Fraction &b);
    friend Fraction operator /(const Fraction &a, const Fraction &b);
    void show() {
        std::cout << fenzi << "/" << fenmu << ";double = " << (double)(*this) << std::endl;
    }

    friend std::ostream& operator<<(std::ostream &os, const Fraction &item);
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
    //交换两个整数
    void swapInt(int &a, int &b) {
        int t;
        t = a;
        a = b;
        b = t;
    }
};
//化简分母
Fraction& Fraction::getSim() {
    int div = getGreatestCommonDivisor(fenmu, abs(fenzi));
    fenzi /= div;
    fenmu /= div;

    return *this;
}

bool Fraction::operator==(const Fraction &b) {
    return (fenzi*b.fenmu == fenmu*b.fenzi);
}
bool Fraction::operator<(const Fraction &b) {
    return (b.fenzi*fenmu - fenzi*b.fenmu);
}
Fraction::operator double() {
    return static_cast<double>(fenzi) / static_cast<double>(fenmu);
}

Fraction operator +(const Fraction &a, const Fraction &b) {
    return Fraction(a.fenzi*b.fenmu + b.fenzi*a.fenmu, a.fenmu*b.fenmu);
}
Fraction operator -(const Fraction &a, const Fraction &b) {
    return Fraction(a.fenzi*b.fenmu - b.fenzi*a.fenmu, a.fenmu*b.fenmu);
}
Fraction operator *(const Fraction &a, const Fraction &b) {
    return Fraction(a.fenzi*b.fenzi, a.fenmu*b.fenmu);
}
Fraction operator /(const Fraction &a, const Fraction &b) {
    int den = a.fenmu*b.fenzi;
    int num = a.fenzi*b.fenmu;
    //确保分母非负
    if (den < 0) {
        den = -den;
        num = -num;
    }
    return Fraction(num, den);
}

//重载输出运算符<<
std::ostream& operator<<(std::ostream &os, const Fraction &item) {
    os << item.fenzi << "/" << item.fenmu;
    return os;
}
#endif