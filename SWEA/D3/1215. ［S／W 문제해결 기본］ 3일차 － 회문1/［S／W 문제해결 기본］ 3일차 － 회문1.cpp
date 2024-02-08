#include <iostream>
using namespace std;

int main() {
    int test_case = 1;
    while(test_case<=10) {
        int N;
        cin >> N;
        char arr[8][8];
        for(int i =0; i<8; i++) {
            for(int j=0; j<8; j++) {
                cin >> arr[i][j];
            }
        }
        
        bool cmp = false;
        int result = 0;
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if(j+N-1 < 8) {
                	if(arr[i][j] == arr[i][j+N-1]) {
                    	cmp = true;
                    	int front = j+1;
                    	int back = j+N-2;
                    	while(front<back) {
                        	if(arr[i][front] == arr[i][back]) cmp = true;
                        	else {
                            	cmp = false;
                            	break;
                        	}
                        	front++;
                        	back--;
                    	}
                    	if(cmp) result++;
                	}
                }
                if(i+N-1 <8) {
                	if(arr[i][j] == arr[i+N-1][j]) {
                    	cmp = true;
                    	int front = i+1;
                   	 	int back = i+N-2;
                    	while(front<back) {
                        	if(arr[front][j] == arr[back][j]) cmp = true;
                        	else {
                            	cmp = false;
                           		break;
                        	}
                        	front++;
                       	 	back--;
                    	}
                    	if(cmp) result++;
                	}
                	cmp = false;
            	}
            }
        }
        cout << '#' << test_case << ' ' << result << endl;
        test_case++;
    }
    return 0;
}