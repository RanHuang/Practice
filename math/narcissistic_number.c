/****************************************************************
    > File Name: narcissistic_number.c
    > Author: Nick
    > mail: xjhznick@qq.com
    > Created Time: 2015年12月19日 星期六 09时55分48秒
    > Description:
        Calculate the narcissistic number
        a^3 + b^3 + c^3 = abc
  $gcc narcissistic_number.c -lm 
  $nohup time ./a.out >> log.txt &
****************************************************************/

#include <stdio.h>
#include <math.h>
#define MIN 1000
#define MAX 9999
#define NUMBER 10000

void narThreeLoop();
void narThreeTraverse();
void narThreeLoopImprove();
void narThreeLoopSpaceTimeImprove();
void test();

int main(int argc, char* argv[])
{
//    printf("使用三重循环求解水仙花树\n");
//    narThreeLoop();

    printf("使用优化后的三重循环求解水仙花树\n");
    narThreeLoopImprove();

//    printf("采用以空间换时间优化后的三重循环枚举法求解四位水仙花数\n");
//    narThreeLoopSpaceTimeImprove();

//    printf("采用暴力枚举法求解四位水仙花数\n");
//    narThreeTraverse();

    
//    test();
    return 0;
}
void test()
{
    long long a,b,c;
//    int a,b,c;
//    a b c若为int型，在强制类型转换为long long时会有计算错误,数据溢出
    long long sum_left,sum_right;

    a = 1420, b = 5170, c = 1000;
    sum_left = (long long)a*a*a + b*b*b + c*c*c;
    sum_right = (long long)a*NUMBER*NUMBER + b*NUMBER + c;
    printf("a^3+b^3+c^3=%lld\n", sum_left);
    printf("a*10^8+b*10^4+c=%lld\n", sum_right);
    if(sum_left == sum_right){
        printf("%lld = %lld^3 + %lld^3 + %lld^3\n",sum_right,a,b,c);
    }

    return;
}
void narThreeLoop()
{
    long long a,b,c;
    long long sum_left,sum_right;
    int num;
    num = 0;

    for(a=MIN; a<=MAX; a++){
        for(b=0; b<=MAX; b++){
            for(c=0; c<=MAX; c++){
                sum_left = a*a*a + b*b*b + c*c*c;
                sum_right = a*NUMBER*NUMBER + b*NUMBER + c;
                if(sum_left == sum_right){
                    num = num+1;
                    printf("%2d:%lld = %lld^3 + %lld^3 + %lld^3\n",num,sum_right,a,b,c);
                }
            }
        }
    }

    printf("The total number of the digital is: %d\n", num);
    
    return;
}
/*
 * 根据数值规律缩减b,c的遍历空间
 * 非常优化的算法
 */
void narThreeLoopImprove()
{
    long long a,amin,amax;
    long long b,bmin,bmax;
    long long c,cmin,cmax;
    long long sum_left,sum_right;
    long double temp;
    int num;
    num = 0;

    amin = MIN;
    amax = MAX;
    for(a=amin; a<=amax; a++){
        temp = (long double)(a+1)*NUMBER*NUMBER - a*a*a;
        bmax = (long long)powl(temp, 1.0/3);
        bmax = bmax<MAX? bmax:MAX;
        bmin = 0;
        for(b=bmin; b<=bmax; b++){
            temp = (long double)a*NUMBER*NUMBER + (b+1)*NUMBER - a*a*a - b*b*b;
            cmax = (long long)powl(temp, 1.0/3);
            cmax = cmax<MAX? cmax:MAX;
            temp = (long double)a*NUMBER*NUMBER + b*NUMBER - a*a*a - b*b*b;
            cmin = temp<0.1? 0:(long long)powl(temp,1.0/3);
            for(c=cmin; c<=cmax; c++){
                sum_left = a*a*a + b*b*b + c*c*c;
                sum_right = a*NUMBER*NUMBER + b*NUMBER + c;
                if(sum_left == sum_right){
                    num = num+1;
                    printf("%2d:%lld = %lld^3 + %lld^3 + %lld^3\n",num,sum_right,a,b,c);
                }
            }
        }
    }

    printf("The total number of the digital is: %d\n", num);
    
    return;
}
void narThreeLoopSpaceTimeImprove()
{
    long long powerThree[NUMBER] = {0};
    long long i,j,k;
    long long sum, temp;
    long long SQUARE_NUMBER;
    int num;

    SQUARE_NUMBER = NUMBER*NUMBER;
    for(i=0; i<=MAX; i++){
        powerThree[i] = i*i*i;
    }

    num = 0;
    for(i=MIN; i<=MAX; i++){
        for(j=0; j<=MAX; j++){
            for(k=0; k<=MAX; k++){
                sum = powerThree[i] + powerThree[j] + powerThree[k];

                temp = sum % NUMBER;
                if(temp == k){
                    temp = (sum/NUMBER)%NUMBER;
                    if(temp == j){
                        temp = sum/SQUARE_NUMBER;
                        if(temp == i){
                            num = num+1;
                            printf("%2d:%lld = %lld^3 + %lld^3 + %lld^3\n",num,sum,i,j,k);
                       }
                   }
               }
           }
       }
   }

    printf("The total number of the digital is: %d\n", num);

    return;
}
/*
 * 遍历四位水仙花数的结果区间[10^12, 3*MAX^3],拆分该结果为
 */
void narThreeTraverse()
{
    long long a,b,c;
    long long  start,end;
    /*
    start = (long long)MIN*NUMBER*NUMBER;
    end = (long long)MAX*NUMBER*NUMBER + MAX*NUMBER + MAX;
    printf("start = %lld\n", start);
    printf("end = %lld\n", end);
    */
    start = (long long)1000*10000*10000;
    end = (long long)9999*9999*9999*3;

    long long temp;
    int num;
    num = 0;
    while(start<=end){
        temp = start;
        c = temp%NUMBER;
        temp = temp/NUMBER; 
        b = temp%NUMBER;
        temp = temp/NUMBER; 
        a = temp%NUMBER;
        if(a*a*a+b*b*b+c*c*c==start){
            num = num+1;
            printf("%2d:%lld = %lld^3 + %lld^3 + %lld^3\n",num,start,a,b,c);
        }
        start = start+1;
    }
    printf("The total number of the digital is: %d\n", num);
    
    return;
}

