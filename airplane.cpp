#include<iostream>
using namespace std;
int main(void)
{
    //根据起飞降落时间判断飞机飞行时间
    int start = 0, end = 0;
    cout<<"Input the Start time and end time: "<<endl;
    cout<<"start = ";
    cin>>start;
    cout<<"end = ";
    cin>>end;
    //判断是否过零点，最大仅限于24小时
    if(start > end){
	end += 2400;
    } 

    int result = 0;
    result = end/100 - start/100; //计算小时
    //计算分钟
    end %= 100;
    start %= 100;  
    if(end < start){
	result = (result-1)*100 + end+60-start;
    }else{
	result = result*100 + end-start;
    } 
    
    cout<<"Duration: "<<result/100<<":"<<result%100<<endl;
    return 0;
}
