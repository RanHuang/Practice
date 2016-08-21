#include<iostream>
#include<cmath>
using namespace std;
bool isPrime(unsigned n);
int main(void)
{
    int n = 0;
    while(true){
        cout<<"请输入一个大于2的偶数(0-exit)：";
        cin>>n;
        if(n==0) break;
        if(n<2) continue;
        
        for(unsigned i=2; i<=n/2; i++){
            if(isPrime(i) && isPrime(n-i)){
                cout<<n<<" = "<<i<<" + "<<n-i<<endl;
            }
        } 
    }

    cout<<"Result: "<<endl;
    return 0;
}
bool isPrime(unsigned n)
{   
    if(n==2 || n==3 || n==5){
        return true;
    }
    for(unsigned i=2; i <= unsigned(sqrt(n)); ++i){
        if(n%i==0) return false;
    }
    return true;
}
