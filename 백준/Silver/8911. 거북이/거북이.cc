#include <stdio.h>
#include <iostream>
#include <algorithm>
#include <queue>
#include <math.h>
#include <vector>
using namespace std;

int main(void)
{

    int n;
    scanf("%d", &n);

    for (int i = 0; i < n; i++)
    {
        string s;
        cin >> s;
        int x = 0;
        int y = 0;
        int dir = 0;
        int x_min = 0, x_max = 0, y_min = 0, y_max = 0;

        for (int j = 0; j < s.length(); j++)
        {
            if (s[j] == 'F')
            { // 앞으로 가는 방향이면
                if (dir % 4 == 0)
                {
                    y++;
                }
                else if (dir % 4 == 1)
                {
                    x--;
                }
                else if (dir % 4 == 2)
                {
                    y--;
                }
                else if (dir % 4 == 3)
                {
                    x++;
                }
            }
            else if (s[j] == 'B')
            {
                if (dir % 4 == 0)
                {
                    y--;
                }
                else if (dir % 4 == 1)
                {
                    x++;
                }
                else if (dir % 4 == 2)
                {
                    y++;
                }
                else if (dir % 4 == 3)
                {
                    x--;
                }
            }
            else if (s[j] == 'L')
            {
                dir++;
                if (dir > 3)
                    dir = 0;
            }
            else if (s[j] == 'R')
            {
                dir--;
                if (dir < 0)
                    dir = 3;
            }

            x_min = min(x, x_min);
            x_max = max(x, x_max);
            y_min = min(y, y_min);
            y_max = max(y, y_max);
        }

        printf("%d\n", (x_max - x_min) * (y_max - y_min));
    }

    return 0;
}