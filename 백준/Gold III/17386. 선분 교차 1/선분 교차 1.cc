#define ll long long
#include <iostream>
using namespace std;
ll ccw(ll x1, ll y1, ll x2, ll y2, ll x3, ll y3) {
    ll cross_product = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
    if (cross_product > 0) {
        return 1; // 왼쪽
    }
    else if (cross_product < 0) {
        return -1; // 오른쪽
    }
    else {
        return 0; // 일직선
    }
}

// (x1, y1)에서 (x2, y2)로 그려지는 직선과 (rx1, ry1)-(rx2, ry2)로 그려진 직사각형이 교차하는지 판별하는 함수
bool isIntersected(ll x1, ll y1, ll x2, ll y2, ll x3, ll y3, ll x4, ll y4) {
    ll minx12 = min(x1, x2), maxx12 = max(x1, x2);
    ll minx34 = min(x3, x4), maxx34 = max(x3, x4);
    ll miny12 = min(y1, y2), maxy12 = max(y1, y2);
    ll miny34 = min(y3, y4), maxy34 = max(y3, y4);
    if (maxx12 < minx34 || maxx34 < minx12 || maxy12 < miny34 || maxy34 < miny12) {
        return false; // 두 선분이 x, y 좌표 모두에서 겹치는 영역이 없는 경우
    }

    ll ccw123 = ccw(x1, y1, x2, y2, x3, y3);
    ll ccw124 = ccw(x1, y1, x2, y2, x4, y4);
    ll ccw341 = ccw(x3, y3, x4, y4, x1, y1);
    ll ccw342 = ccw(x3, y3, x4, y4, x2, y2);
    return ccw123 * ccw124 <= 0 && ccw341 * ccw342 <= 0;
}

int main() {
    ll x1, y1, x2, y2, x3, y3, x4, y4;
    cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3 >> x4 >> y4;
    if (isIntersected(x1, y1, x2, y2, x3, y3, x4, y4)) cout << 1 << endl;
    else cout << 0 << endl;

    return 0;
}