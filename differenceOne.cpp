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
/* ����n���������������1�������ж��ٶ� 
 * ����������Ȼ��һ��ɨ�裬Ѱ����������Ԫ��ֵ��1
 * vector sort
 * �������飬����ɨ�� 
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
