#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <utility>
#include <math.h>
#include <string>
#include <vector>
using namespace std;

typedef struct Block
{
    int left_down_x; //왼쪽 아래 x좌표
    int left_down_y; //왼쪽 아래 y좌표
    int right_up_x;  //오른쪽 위 x좌표
    int right_up_y;  //오른쪽 위 y좌표
} Block;

vector<Block> arr(10001);

bool check;
int X, Y;

bool cmp(const Block &a, const Block &b)
{
    if (a.left_down_y == b.left_down_y)
        return a.left_down_x < b.left_down_x;
    else
        return a.left_down_y < b.left_down_y;
}

bool cmp1(const Block &a, const Block &b)
{
    if (a.left_down_x == b.left_down_x)
        return a.left_down_y < b.left_down_y;
    else
        return a.left_down_x < b.left_down_x;
}

int main(void)
{
    int n;
    cin >> n;

    for (int i = 0; i < n; i++)
    {
        int tcase;
        cin >> tcase;

        for (int j = 0; j < tcase; j++)
            cin >> arr[j].left_down_x >> arr[j].left_down_y >> arr[j].right_up_x >> arr[j].right_up_y;

        if (tcase == 10000)
        {
            for (int k = 0; k < 4; k++)
            {
                X = 0;
                Y = 0;

                sort(arr.begin(), arr.begin() + tcase, cmp);

                for (int i = 0; i < tcase; i++)
                {
                    int height = 0;
                    for (int j = i - 1; j > -1; j--)
                    {
                        if (arr[j].left_down_x < arr[i].right_up_x && arr[j].right_up_x > arr[i].left_down_x)
                        {
                            if (height < arr[j].right_up_y)
                                height = arr[j].right_up_y;
                            if (height == arr[i].left_down_y)
                                break;
                        }
                    }

                    if (height != arr[i].left_down_y)
                    {
                        check = true;
                        int temp = arr[i].left_down_y - height;
                        arr[i].left_down_y -= temp;
                        arr[i].right_up_y -= temp;
                    }
                }
                check = false;

                sort(arr.begin(), arr.begin() + tcase, cmp1);

                for (int i = 0; i < tcase; i++)
                {

                    if (arr[i].right_up_x > X)
                        X = arr[i].right_up_x;
                    if (arr[i].right_up_y > Y)
                        Y = arr[i].right_up_y;

                    int height = 0;
                    for (int j = i - 1; j > -1; j--)
                    {
                        if (arr[j].right_up_y > arr[i].left_down_y && arr[j].left_down_y < arr[i].right_up_y)
                        {
                            if (height < arr[j].right_up_y)
                                height = arr[j].right_up_y;
                            if (height == arr[i].left_down_x)
                                break;
                        }
                    }

                    if (height != arr[i].left_down_x)
                    {
                        check = true;
                        int temp = arr[i].left_down_x - height;
                        arr[i].left_down_x -= temp;
                        arr[i].right_up_x -= temp;
                    }
                }
                if (!check)
                    break;
                check = false;
            }
        }
        else
        {
            for (int k = 0; k < 8; k++)
            {

                X = 0;
                Y = 0;
                sort(arr.begin(), arr.begin() + tcase, cmp);

                for (int i = 0; i < tcase; i++)
                {

                    int height = 0;

                    for (int j = i - 1; j > -1; j--)
                    {
                        if (arr[j].left_down_x < arr[i].right_up_x && arr[j].right_up_x > arr[i].left_down_x) //현재 기준 도형의 아래에 있는 도형의 최고 높이를 구한다.
                        {
                            if (height < arr[j].right_up_y)
                                height = arr[j].right_up_y;
                            if (height == arr[i].left_down_y)
                                break;
                        }
                    }

                    if (height != arr[i].left_down_y)
                    {
                        check = true;
                        int temp = arr[i].left_down_y - height;
                        arr[i].left_down_y -= temp;
                        arr[i].right_up_y -= temp;
                    }
                }
                check = false;

                sort(arr.begin(), arr.begin() + tcase, cmp1);

                for (int i = 0; i < tcase; i++)
                {

                    if (arr[i].right_up_x > X)
                        X = arr[i].right_up_x;
                    if (arr[i].right_up_y > Y)
                        Y = arr[i].right_up_y;

                    int height = 0;
                    for (int j = i - 1; j > -1; j--)
                    {
                        if (arr[j].right_up_y > arr[i].left_down_y && arr[j].left_down_y < arr[i].right_up_y) //현재 기준 도형의 아래에 있는 도형의 최고 높이를 구한다.
                        {
                            if (height < arr[j].right_up_x)
                                height = arr[j].right_up_x;
                            if (height == arr[i].left_down_x)
                                break;
                        }
                    }
                    if (height != arr[i].left_down_x)
                    {
                        check = true;
                        int temp = arr[i].left_down_x - height;
                        arr[i].left_down_x -= temp;
                        arr[i].right_up_x -= temp;
                    }
                }
                if (!check)
                    break;
                check = false;
            } // tcase != 10000
        }

        cout << X << " " << Y << "\n";
        check = false;
    }

    return 0;
}