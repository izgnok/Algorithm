#include <iostream>
using namespace std;

int main(){
    int T;
    cin >> T;
    int test_case=1;
    while(test_case<=T) {
        int N,M;
        cin >> N >> M;
        int *arr1 = new int[N];
        int *arr2 = new int[M];
        for(int i = 0; i<N; i++) {
            cin >> arr1[i];
        }
        for(int i=0; i<M; i++) {
            cin >> arr2[i];
        }
        
        int result = 0;
		int index = 0;
        bool cmp = false;
        while(true) {
            int tmp = 0;
            int j = index;
            if(N<M) {
                for(int i=0; i<N; i++) {
                    if(j==M) {
                        cmp = true;
                        break;
                    }
                    tmp+= arr1[i] * arr2[j];
                    j++;
                }
            }
            else {
               for(int i=0; i<M; i++) {
                   if(j==N) {
                       cmp = true;
                       break;
                   }
                    tmp+= arr1[j] * arr2[i];
                    j++;
                }
            }
            if(cmp) break;
            result = max(result,tmp);
            index++;
        }
        cout << '#' << test_case << ' ' << result << endl;
        test_case++;
    }
    return 0;
}