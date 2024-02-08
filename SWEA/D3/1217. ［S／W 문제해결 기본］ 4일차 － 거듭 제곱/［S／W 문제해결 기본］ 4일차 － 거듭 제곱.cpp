#include <iostream>
using namespace std;

int Square(int N, int M) {
    if(M==1) return N;
    else return N*Square(N,M-1);
}

int main() {
    int test_case;
    while(true) {
        cin >> test_case;
        int N,M;
        cin >> N >> M;
        int result = Square(N,M);
        cout << '#' << test_case << ' ' << result << endl;
        if(test_case == 10) break;
    }
    
    return 0;
}