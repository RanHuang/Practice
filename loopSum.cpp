#include<iostream>
using namespace std;

long long test(int a, int n);
int main(){
	
	cout<<test(1, 1)<<endl;
	cout<<test(1, 2)<<endl;
	cout<<test(2, 3)<<endl;
	
	system("pause");
	return 0;
}
// sum = a + aa + aaa; //n=3
long long test(int a, int n){
	long long sum = 0;
	int add = a;
	for(int i=0; i<n; ++i){
		sum += add;
		a *= 10;
		add += a;
	}
	
	return sum;
}
