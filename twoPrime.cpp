#include<iostream>
#include<cmath>
using namespace std;

bool isPrime(int n);
int findPrime(int n);
void printPrimes(int n);
//Ѱ������������ֵ���2���������� 
int main(){
	int n = 11;
	int result = findPrime(n);
	cout<<"����"<<n<<"����������Ϊ��("<<result<<", "<<result+2<<")\n";
	
	printPrimes(100);
	
	system("pause");
	return 0;
}

bool isPrime(int n){
	if(n < 2) return false;
	if(n==2 || n==3 || n==5) return true;
	
	if(n%2 ==0) return false;
	int squareroot = (int)sqrt(n);
	for(int i=3; i<=squareroot; i+=2){
		if(n%i == 0) return false;
	}
	return true;
}

int findPrime(int n){
	if(n<0) return -1;
	if(n%2 == 0) ++n;
	while(!isPrime(n) || !isPrime(n+2)){
		n += 2;
	}
	
	return n;
}

void printPrimes(int n){
	cout<<"���1-"<<n<<"����������:\n"; 
	int num = 0;
	for(int i=1; i<n; ++i){
		if(isPrime(i)){
			cout<<i<< " ";
			++num;
			if(num%7 == 0) cout<<endl;
		}			
	}
	cout<<"\n����"<<num <<"������"<<endl;
}
