#include <iostream>
#include <algorithm>
#include <string>

using namespace std;
int main() {
    int T;
    int test_case;
    cin >> T;
    for(test_case =1; test_case<=T; test_case++) {
        string s;
        cin >> s;
        s.erase(remove(s.begin(),s.end(), 'a'), s.end());
        s.erase(remove(s.begin(),s.end(), 'e'), s.end());
        s.erase(remove(s.begin(),s.end(), 'i'), s.end());
        s.erase(remove(s.begin(),s.end(), 'o'), s.end());
        s.erase(remove(s.begin(),s.end(), 'u'), s.end());
        cout << '#' << test_case << ' ' << s << endl;
    }
    return 0;
}