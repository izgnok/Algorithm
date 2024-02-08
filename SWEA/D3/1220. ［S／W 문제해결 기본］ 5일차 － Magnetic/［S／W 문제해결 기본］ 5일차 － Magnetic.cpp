#include <iostream>
using namespace std;

int main() {
    for(int test_case = 1; test_case<=10; test_case++) {
        int T;
        cin >> T;
        int arr[100][100];
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                cin >> arr[i][j];
            }
        }
        int result = 0;
        for(int j=0; j<100; j++) {
            bool deadlock = false;
            int i=0;
            while(i<100) {
                if(arr[i][j] == 1) {
                    deadlock = true;
                }
                else if( arr[i][j] == 2) {
                    if(deadlock) {
                        result++;
                        deadlock = false;
                    }
                }
                else {
                }
                i++;
            }
        }
        cout << '#' << test_case << ' ' << result << endl;
    }
    return 0;
}