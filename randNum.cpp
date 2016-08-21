#include<iostream>
#include<ctime>
using namespace std;
int main(void)
{
    srand(time(NULL));
    cout << "Generate a random number [1-55]: "<<rand()%55 + 1 << endl;
    system("pause");
    return 0;
}