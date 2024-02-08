#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    int test_case;
    while(true) {
        cin >> test_case;
         int row_sum[100] = {0,};
    	int col_sum[100] = {0,};
    	int left_da_sum=0;
    	int right_da_sum=0;
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                int k;
                cin >> k;
                row_sum[i] += k;
                col_sum[j] += k;
                if(i==j) left_da_sum += k;
                if(i+j == 99) right_da_sum += k;
            }
        }
        sort(row_sum, row_sum+100);
        sort(col_sum, col_sum+100);
        int result = max(max(max(row_sum[99], col_sum[99]), left_da_sum),right_da_sum);
        cout << '#' << test_case << ' ' << result << endl;
        if(test_case == 10) break;
    }
    return 0;
}