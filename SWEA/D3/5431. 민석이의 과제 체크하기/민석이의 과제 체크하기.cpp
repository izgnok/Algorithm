#include <iostream>
using namespace std;

int main() {
    int T;
    cin >> T;
    int test_case = 1;
    while(test_case<=T) {
        int N, k;
        cin >> N >> k;
        bool* arr = new bool[N+1];
        for(int i=1; i<N+1; i++) {
            arr[i] = false;
        }
        for(int i=0; i<k; i++) {
            int t;
            cin >> t;
            arr[t] = true;
        }
        cout << '#' << test_case << ' ';
        for(int i=1; i<N+1; i++) {
            if(!arr[i]) cout << i << ' ';
        }
        cout << endl;
        test_case++;
    }
    return 0;
}