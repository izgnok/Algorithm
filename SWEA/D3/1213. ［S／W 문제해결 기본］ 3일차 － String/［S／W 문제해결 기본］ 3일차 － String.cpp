#include <iostream>
#include <string>
using namespace std;

int main() {
    int test_case;
    while(true){ 
        cin >> test_case;
        string word, str;
        cin >> word >> str;
        
        int word_length = word.size();
        int str_length = str.size();
        
        int j = 0;
        int k;
		int result = 0;
        bool cmp = false;
        
        for(int i=0; i< str.size(); i++) {
            if(str[i] == word[j]) {
                cmp = true;
                k = i + 1;
                j++;
                for(;j<word.size(); j++) {
                    if(str[k] == word[j]) cmp = true;
                   	else {
                        cmp = false;
                        break;
                    }
                    k++;
                }
                j = 0;
                if(cmp) {
                    result++;
                    i = k;
                }
            }
        }
        cout << '#' << test_case << ' ' << result << endl;
        if(test_case == 10) break;
    }
    return 0;
}