#include <iostream>
using namespace std;

int main() {
    int T;
    cin >> T;
    int test_case= 1;
    while(test_case <= T) {
        int result = 0;
     	for(int i=0; i<10; i++) {
            int k;
            cin >> k;
            if(k%2!=0) result+=k;
        }
        cout << '#'  << test_case << ' ' << result << endl;
        test_case++;
    }
}