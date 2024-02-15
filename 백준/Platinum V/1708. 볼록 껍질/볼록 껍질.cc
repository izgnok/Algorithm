#include <iostream>
#include <fstream>
#include <cstring>
#include <stack>
#include <algorithm>
using namespace std;

typedef struct Point{
    long long int x;
    long long int y;
} Point;
Point P[100001];

long long int ccw2(Point A, Point B) {
    return A.x*B.y - A.y*B.x;
}

int ccw(Point A, Point B, Point C) {
    Point P, Q;
    P.x = B.x - A.x;
    P.y = B.y - A.y;
    Q.x = C.x - A.x;
    Q.y = C.y - A.y;
    long long int rotation = ccw2(P,Q);
    
    if(rotation > 0) return 1; // 0보다 크면 좌회전
    else if(rotation < 0) return -1; // 0보다 작으면 우회전
    else return 0; // 0이면 직선
}

bool compare (const Point &a, const Point &b) {
    if(a.x == b.x) {
        if(a.y < b.y) return true;
    }
    if(a.x < b.x) return true;
    return false;
}
bool compare2 (const Point &a, const Point &b) {
    long long int rect = ccw(P[0], a, b);
    if(rect > 0) return true; //좌회전 기준 정렬
    if(rect < 0) return false;
    long long int dist1 = (P[0].x - a.x)*(P[0].x - a.x) + (P[0].y - a.y)*(P[0].y - a.y);
    long long int dist2 = (P[0].x - b.x)*(P[0].x - b.x) + (P[0].y - b.y)*(P[0].y - b.y);
    if(dist1 < dist2) return true;  //직선이라면 가까운점을 앞으로
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int N;
    cin >> N;
    for(int i=0; i<N; i++) {
        cin >> P[i].x >> P[i].y;
    }
    sort(P, P+N, compare);
    sort(P+1, P+N, compare2);
    
    stack<Point> V;
    V.push(P[0]);
    V.push(P[1]);
    int next =2;
    
    while(next < N) {
        while(V.size() >= 2) {
            Point first, second;
            second = V.top();
            V.pop();
            first = V.top();
            
            if(ccw(first,second,P[next]) > 0) {
                V.push(second);
                break;
            }
        }
        V.push(P[next]);
        next++;
    }
    
    cout << V.size() << endl;
//    int result_size = (int)V.size();
//    Point result[result_size];
//    for(int i=result_size-1; i>=0; i--) {
//        result[i] = V.top();
//        V.pop();
//    }
//    for(int i=0; i<result_size; i++) {
//        cout << result[i].x << " " << result[i].y << endl;
//    }
    return 0;
}