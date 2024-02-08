#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int test_case = 1;
    while(test_case<=10) {
        int N;
        cin >> N;
        vector<int> apart;
        apart.push_back(0);
        apart.push_back(0);
        for(int i=0; i<N; i++) {
            int k;
            cin >> k;
            apart.push_back(k);
        }
        apart.push_back(0);
        apart.push_back(0);
        int result = 0;
        for(int i=2; i<N+2; i++) {
            int left = 0;
            int right = 0;
            if(apart.at(i) > apart.at(i-1) && apart.at(i) > apart.at(i-2)) {
                left = apart.at(i) - max(apart.at(i-1), apart.at(i-2));
            }
            if(apart.at(i) > apart.at(i+1) && apart.at(i) > apart.at(i+2)) {
                right = apart.at(i) - max(apart.at(i+1), apart.at(i+2));
            }
            result += min(left,right);
        }
        cout << '#' << test_case << ' ' << result << endl;
        test_case++;
    }
    return 0;
}