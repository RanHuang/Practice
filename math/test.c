#include <stdio.h>
int main()
{
    long long start, end;
    int MIN = 1000;
    int MAX = 9999;
    int NUMBER = 10000;
    printf("hello\n");
    start = (long long)MIN*NUMBER*NUMBER + MIN*NUMBER + MIN;
    end = (long long)MAX*NUMBER*NUMBER + MAX*NUMBER + MAX;
    printf("start = %lld\n", start);
    printf("end = %lld\n", end);

    return 0;
}
