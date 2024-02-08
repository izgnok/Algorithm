#include <iostream>
using namespace std;

int main() {
    int T;
    cin >> T;
    int test_case = 1;
    while(test_case <= T) {
        int N, D;
        cin >> N >> D;
        int result = N / ( D*2+1);
        if(N%(D*2+1) != 0 ) result++;
        cout << '#' << test_case << ' ' << result << endl;
        test_case++;
    }
    return 0;
}