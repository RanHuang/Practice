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
	cout<<m<<"��"<<n<<"�����Լ����"<<b<<endl;
	cout<<m<<"��"<<n<<"����С��������"<<m*n/b<<endl; 
	
	return 0;
}
void swapInt(int &a, int &b){
	int t;
	t = a;
	a = b;
	b = t;
}
