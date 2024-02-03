#include <stdio.h>
#include <iostream>
#include <algorithm>
#include <queue>
#include <math.h>
#include <vector>
using namespace std;

struct Bowling
{
    int first = 0;
    int second = 0;
    int third = 0;
};

int main(void)
{

    int n;
    scanf("%d", &n);

    for (int i = 0; i < n; i++)
    {
        scanf("\n");
        int sum = 0;
        int j = 0;

        Bowling bowling[10];

        for (int k = 0; k < 10; k++)
        { // 일단 다 받아오기
            scanf("%d", &bowling[k].first);
            if (k < 9 && bowling[k].first == 10)
                continue;
            scanf("%d", &bowling[k].second);
            if (k == 9 && bowling[k].first + bowling[k].second >= 10)
                scanf("%d", &bowling[k].third);
        }

        for (int k = 0; k < 10; k++)
        {
            sum += bowling[k].first + bowling[k].second + bowling[k].third;
						// 그냥 수를 세 개 다 더함, 없어도 0이므로 지장 X						

            if (k != 9 && bowling[k].first != 10 && bowling[k].first + bowling[k].second == 10)
            { // 맨 마지막 프레임 X && 스페어 O
                sum += bowling[k + 1].first; // 바로 다음 공만 보너스점수로 더하기
            }
            else if (k != 9 && bowling[k].first == 10)
            { // 맨 마지막 프레임 X && 스트라이크
				        sum += bowling[k + 1].first + bowling[k + 1].second; // 다음 공 두개 더하기

                if (k < 8 && bowling[k + 1].first == 10)
                { // 스트라이크가 연속으로 나오면
                    sum += bowling[k + 2].first;
										// 그 다음 프레임의 첫 번째 공까지 현재 프레임의 보너스점수가 됨.
                }
            }
        }
        printf("%d\n", sum);
    }
    return 0;
}