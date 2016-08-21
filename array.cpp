#include<iostream>
using namespace std;

int main()
{
	int arr[3][4] = {{1,2,3}, {4,5,6,7}, {10,11,12,15}};
	for(auto m : arr){
		for(int i=0; i<4; i++){
			cout<<'\t'<<m[i];
		}
		cout<<endl;
	}
	cout<<endl;
	
	for(auto &m : arr){
		for(auto n : m){
			cout<<'\t'<<n;
		}
		cout<<endl;
	}
	
	system("pause");
	return 0;
}
