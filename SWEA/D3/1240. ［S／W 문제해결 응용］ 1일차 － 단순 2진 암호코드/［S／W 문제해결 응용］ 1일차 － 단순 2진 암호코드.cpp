#include <iostream>
using namespace std;

int main() {
    int T;
    cin >> T;
    int test_case;
    for(test_case =1 ; test_case <= T; test_case++) {
        int N,M;
        cin >> N >> M;
        char *arr = new char[N*M];
        for(int i=0; i<N*M; i++) {
            cin >> arr[i];
        }
        int index;
        for(int i=N*M-1; i>=0; i--) {
            if(arr[i] == '1') {
                index = i;
                break;
            }
        }
       
        int k = index - 55;
        int num_arr[8];
        int idx = 0;
        while(k<=index) {
            if(arr[k] == '0' && arr[k+1] == '0' && arr[k+2] == '0' && arr[k+3] == '1' && arr[k+4] == '1' && arr[k+5] =='0' && arr[k+6] =='1') num_arr[idx++] = 0;
            else if(arr[k] == '0' && arr[k+1] == '0' && arr[k+2] == '1' && arr[k+3] == '1' && arr[k+4] == '0' && arr[k+5] =='0' && arr[k+6] =='1') num_arr[idx++] = 1;
            else if(arr[k] == '0' && arr[k+1] == '0' && arr[k+2] == '1' && arr[k+3] == '0' && arr[k+4] == '0' && arr[k+5] =='1' && arr[k+6] =='1') num_arr[idx++] = 2;
            else if(arr[k] == '0' && arr[k+1] == '1' && arr[k+2] == '1' && arr[k+3] == '1' && arr[k+4] == '1' && arr[k+5] =='0' && arr[k+6] =='1') num_arr[idx++] = 3;
            else if(arr[k] == '0' && arr[k+1] == '1' && arr[k+2] == '0' && arr[k+3] == '0' && arr[k+4] == '0' && arr[k+5] =='1' && arr[k+6] =='1') num_arr[idx++] = 4;
            else if(arr[k] == '0' && arr[k+1] == '1' && arr[k+2] == '1' && arr[k+3] == '0' && arr[k+4] == '0' && arr[k+5] =='0' && arr[k+6] =='1') num_arr[idx++] = 5;
            else if(arr[k] == '0' && arr[k+1] == '1' && arr[k+2] == '0' && arr[k+3] == '1' && arr[k+4] == '1' && arr[k+5] =='1' && arr[k+6] =='1') num_arr[idx++] = 6;
            else if(arr[k] == '0' && arr[k+1] == '1' && arr[k+2] == '1' && arr[k+3] == '1' && arr[k+4] == '0' && arr[k+5] =='1' && arr[k+6] =='1') num_arr[idx++] = 7;
            else if(arr[k] == '0' && arr[k+1] == '1' && arr[k+2] == '1' && arr[k+3] == '0' && arr[k+4] == '1' && arr[k+5] =='1' && arr[k+6] =='1') num_arr[idx++] = 8;
            else if(arr[k] == '0' && arr[k+1] == '0' && arr[k+2] == '0' && arr[k+3] == '1' && arr[k+4] == '0' && arr[k+5] =='1' && arr[k+6] =='1') num_arr[idx++] = 9;
              else {}
            k = k+7;
        }
        int cmp = (num_arr[0] + num_arr[2] + num_arr[4] + num_arr[6] ) * 3 + num_arr[1] + num_arr[3] + num_arr[5] + num_arr[7];
        int answer;
        if(cmp%10 ==0) answer = num_arr[0] + num_arr[1] + num_arr[2] + num_arr[3] + num_arr[4] + num_arr[5] +num_arr[6] + num_arr[7];
        else answer = 0;
        
        cout << '#' <<test_case << ' ' << answer << endl;
    }
    return 0;
}
