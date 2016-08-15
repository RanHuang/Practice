#include <iostream>
using namespace std;
int main(void)
{
	float r, area;
	cout << "输入半径：r=";
	cin >> r;
	area = 3.1415926 * r * r;
	cout << "半径：" << r << endl;
	cout << "面积：" << area << '\n';
	system("pause");
	return 0;
}