#include <iostream>
using namespace std;

int main() {
    int T;
    int test_case = 1;
    cin >> T;
    while(T>=test_case){ 
        int max = 0;
        for(int i=0; i<10; i++) {
            int k;
            cin >> k;
            if(max<k) max = k;
        }
        cout << '#' << test_case << ' ' << max << endl;
        test_case++;
    }
    return 0;
}