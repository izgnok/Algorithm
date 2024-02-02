#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <utility>
#include <math.h>
#include <string>
#include <vector>
using namespace std;

typedef struct water_tank
{
    int check_vert; // 0->false
    int first;
    int second;
} water_tank;

vector<water_tank> arr[1001];
bool visit[1002][1002] = {
    0,
};
int horizontal[1001][1001] = {
    0,
};
int vertical[1001][1001] = {
    0,
};
int water_tank_arr[1001][1001] = {
    0,
};
int remain;

void reset()
{ // 함수 초기화
    for (int j = 0; j < 1001; j++)
    {
        arr[j].clear();
        for (int k = 0; k < 1001; k++)
        {
            visit[j + 1][k + 1] = false;
            horizontal[j][k] = 0;
            vertical[j][k] = 0;
            water_tank_arr[j][k] = 0;
        }
    }
}

void set_horizontal(int n, int m, int h)
{
    int temph;
    water_tank temp;

    temp.check_vert = 0; // vertical 아님, horizontal O
    for (int j = 0; j < n + 1; j++)
    {
        for (int k = 0; k < m; k++)
        {
            cin >> temph;
            if (temph != -1)
            {
                temp.first = j;
                temp.second = k;
                arr[temph].push_back(temp);
            }
            else
            {
                temph = h;
            }
            horizontal[j][k] = temph;
        }
    }
}

void set_vertical(int n, int m, int h)
{
    int temph;
    water_tank temp;

    temp.check_vert = 1; // vertical O, horizontal 아님
    for (int j = 0; j < n; j++)
    {
        for (int k = 0; k < m + 1; k++)
        {
            cin >> temph;
            if (temph != -1)
            {
                temp.first = j;
                temp.second = k;
                arr[temph].push_back(temp);
            }
            else
            {
                temph = h;
            }
            vertical[j][k] = temph;
        }
    }
}

void set_water_tank_arr(int n, int m, int h)
{
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            visit[i][j] = true;
            water_tank_arr[i - 1][j - 1] = h;
        }
    }
}

void check_dir(int tempx, int tempy, int j)
{ // 방향확인 (4방향연결)
    if (visit[tempx + 2][tempy + 1])
    { // down
        if (horizontal[tempx + 1][tempy] <= j && water_tank_arr[tempx + 1][tempy] >= water_tank_arr[tempx][tempy])
        {

            visit[tempx + 2][tempy + 1] = false;
            water_tank_arr[tempx + 1][tempy] = j;
            remain--;

            check_dir(tempx + 1, tempy, j);
        }
    }
    if (visit[tempx + 1][tempy + 2])
    { // right
        if (vertical[tempx][tempy + 1] <= j && water_tank_arr[tempx][tempy + 1] >= water_tank_arr[tempx][tempy])
        {

            visit[tempx + 1][tempy + 2] = false;
            water_tank_arr[tempx][tempy + 1] = j;
            remain--;

            check_dir(tempx, tempy + 1, j);
        }
    }
    if (visit[tempx][tempy + 1])
    { // up
        if (horizontal[tempx][tempy] <= j && water_tank_arr[tempx - 1][tempy] >= water_tank_arr[tempx][tempy])
        {

            visit[tempx][tempy + 1] = false;
            water_tank_arr[tempx - 1][tempy] = j;
            remain--;

            check_dir(tempx - 1, tempy, j);
        }
    }
    if (visit[tempx + 1][tempy])
    { // left
        if (vertical[tempx][tempy] <= j && water_tank_arr[tempx][tempy - 1] >= water_tank_arr[tempx][tempy])
        {

            visit[tempx + 1][tempy] = false;
            water_tank_arr[tempx][tempy - 1] = j;
            remain--;

            check_dir(tempx, tempy - 1, j);
        }
    }
}

int main(void)
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int n, m, h;


        reset();
        cin >> n >> m >> h;

        set_horizontal(n, m, h);
        set_vertical(n, m, h);
        set_water_tank_arr(n, m, h);

        int result = 0;

        int tempx, tempy;
        remain = n * m;

        for (int j = 0; j < 1001; j++)
        {
            if (arr[j].empty())
                continue;

            for (int k = 0; k < arr[j].size(); k++)
            {
                tempx = arr[j][k].first;
                tempy = arr[j][k].second;
                if (arr[j][k].check_vert == 1)
                {
                    if (visit[tempx + 1][tempy] == visit[tempx + 1][tempy + 1])
                    {
                        continue;
                    }
                    if (visit[tempx + 1][tempy])
                    {
                        tempy--;
                    }

                    visit[tempx + 1][tempy + 1] = false;
                    water_tank_arr[tempx][tempy] = j;
                    remain--;

                    check_dir(tempx, tempy, j);
                }
                else
                {
                    if (visit[tempx][tempy + 1] == visit[tempx + 1][tempy + 1])
                    {
                        continue;
                    }
                    if (visit[tempx][tempy + 1])
                    {
                        tempx--;
                    }

                    visit[tempx + 1][tempy + 1] = false;
                    water_tank_arr[tempx][tempy] = j;
                    remain--;

                    check_dir(tempx, tempy, j);
                }
            }
            if (remain == 0)
                break;
        }

        for (int j = 0; j < n; j++)
        {
            for (int k = 0; k < m; k++)
            {
                result += water_tank_arr[j][k];
            }
        }
        cout << result;

    return 0;
}