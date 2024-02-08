#include <iostream>
using namespace std;

int main() {
    int T;
    cin >> T;
    while(true){
        int test_case;
        cin >> test_case;
    int arr[101] ={0,};
    int score;
    for(int i=0; i<1000; i++) {
        cin >> score;
        arr[score]++;
    }
    
    int max = arr[0];
    int result = 0;
    for(int i=0; i<=100; i++) {
        if (arr[i] >= max) {
            max = arr[i];
            result = i;
        }
     }
     cout << '#' << test_case << ' ' << result << endl;
       	if(test_case ==10) break;
    }
}