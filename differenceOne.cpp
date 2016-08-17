#include<iostream>
using namespace std;

const int MAX_SIZE = 20;
int test(int n, int a[]);
int main(){
	int n = 5;
	int array[MAX_SIZE] = {4,6,7,2,5};
	cout<<test(n, array)<<endl;
	
	system("pause");
	return 0;
}
/* 输入n个整数，计算相差1的整数有多少对 
 * 可以先排序，然后一遍扫描，寻找相邻数组元素值差1
 * vector sort
 * 无序数组，暴力扫描 
 */
int test(int n, int a[]){
	int cnt = 0;
	for(int i=0; i<n-1; i++){
		for(int j=i+1; j<n; j++){
			if(abs(a[i] - a[j]) == 1){
				++cnt;
			}
		}
	}
	return cnt;
}
