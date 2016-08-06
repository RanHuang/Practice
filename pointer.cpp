#include <iostream>
int main(){
    int i, &ri = i;
    i = 5;
    ri = 10;
    std::cout<<i<<" "<<ri<<std::endl;

    int *pi = &i;
    int *pri = &ri;//引用不是对象，该指针指向引用绑定的对象
    *pri = 100;
    std::cout<<*pri<<" "<<ri<<std::endl;
    
    return 0;
}
