#include<iostream>
using namespace std;

void swapInt(int &a, int &b);
int main(void)
{
	cout<<"Input two integer: ";
	int m=0, n=0;
	cin>>m>>n;
	
	int a = m, b = n;
	if(a<b) swapInt(a,b);
	
	int c = 0;
	while(c = a%b){
		a = b;
		b = c;
	}
	cout<<m<<"和"<<n<<"的最大公约数是"<<b<<endl;
	cout<<m<<"和"<<n<<"的最小公倍数是"<<m*n/b<<endl; 
	
	return 0;
}
void swapInt(int &a, int &b){
	int t;
	t = a;
	a = b;
	b = t;
}
