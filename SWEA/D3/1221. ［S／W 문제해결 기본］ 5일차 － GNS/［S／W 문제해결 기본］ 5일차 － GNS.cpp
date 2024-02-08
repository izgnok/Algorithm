#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    int T, test_case;
    cin >> T;
    for(test_case=1; test_case<= T; test_case++) {
        char sharp;
        int num;
        cin >> sharp >> num;
        int N;
        cin >> N;
        int *arr = new int[N];
        char word[3];
        for(int i=0; i<N; i++) {
            cin >> word[0] >> word[1] >> word[2];
            if( word[0] == 'Z') arr[i] = 0;
            else if( word[0] == 'O') arr[i] = 1;
            else if( word[0] == 'T' && word[1] == 'W' ) arr[i] = 2;
            else if( word[0] == 'T' && word[1] == 'H' ) arr[i] = 3;
            else if( word[0] == 'F' && word[1] == 'O' ) arr[i] = 4;
            else if( word[0] == 'F' && word[1] == 'I' ) arr[i] = 5;
            else if( word[0] == 'S' && word[1] == 'I') arr[i] = 6;
            else if( word[0] == 'S' && word[1] == 'V' ) arr[i] = 7;
            else if( word[0] == 'E') arr[i] = 8;
            else  arr[i] = 9;
        }
        sort(arr,arr+N);
        
        cout << '#' << test_case << endl;
        for(int i=0; i<N; i++) {
            if(arr[i] == 0) cout << "ZRO";
            else if(arr[i] == 1) cout << "ONE";
            else if(arr[i] == 2) cout << "TWO";
            else if(arr[i] == 3) cout << "THR";
            else if(arr[i] == 4) cout << "FOR";
            else if(arr[i] == 5) cout << "FIV";
            else if(arr[i] == 6) cout << "SIX";
            else if(arr[i] == 7) cout << "SVN";
            else if(arr[i] == 8) cout << "EGT";
            else cout << "NIN";
            
            if( i < N-1 ) cout << " ";
            else cout << endl;
        } 
    }
    return 0;
}
