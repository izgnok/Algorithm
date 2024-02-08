#include <iostream>
#include <cmath>
using namespace std;

int main() {
    int T;
    cin >> T;
    int test_case = 1;
    while(test_case <= T) {
        int N;
        cin >> N;
        int result = 1 + (N*4);
        
        int tmp_result = 0;
        for(int i=1; i<N; i++) {
            int x = pow(N,2) - pow(i,2);
            tmp_result += sqrt(x);
        }
        result += tmp_result * 4;
        
        cout << '#' << test_case << ' ' << result << endl;
        test_case++;
    }
}