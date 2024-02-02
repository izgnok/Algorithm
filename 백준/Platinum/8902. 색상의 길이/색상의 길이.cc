#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>
#include <cstring>
using namespace std;

int DP[5001][5001];
int One[5001][26];
int Two[5001][26];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    /*
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    if (!fin) {
        cerr << "파일을 찾을 수 없습니다" << endl;
        exit(100);
    }
     */
    int T;
    cin >> T;
    
    while(T>0) {
        string A,B;
        cin >> A >> B;
        char arr_1[A.length()];
        A.copy(arr_1, A.length());
        char arr_2[B.length()];
        B.copy(arr_2, B.length());
        
        memset(DP, 0, sizeof DP);
        memset(One, 0, sizeof One);
        memset(Two, 0, sizeof Two);

        for(int i=1; i<=A.length(); i++) {
            int k = arr_1[i-1]-'A';
            if(i==1) {
                One[1][k] = 1;
                continue;
            }
            for(int j=0; j<26; j++) {
                One[i][j] = One[i-1][j];
            }
            One[i][k]++;
        }
        for(int i=1; i<=B.length(); i++) {
            int k = arr_2[i-1]-'A';
            if(i==1) {
                Two[1][k] = 1;
                continue;
            }
            for(int j=0; j<26; j++) {
                Two[i][j] = Two[i-1][j];
            }
            Two[i][k]++;
        }
        
        for(int i=0; i<=A.length(); i++) {
            for(int j=0; j<=B.length(); j++) {
                int start, end, k;
                if(i==0 && j==0) DP[0][0] = 0;
                else if(i==0 && j!=0) {
                    start = 0;
                    end = 0;
                    k = arr_2[j-1] - 'A';
                    if(Two[j][k] == 1) start = j;
                    if(Two[j][k] == One[A.length()][k] + Two[B.length()][k]) end = j;
                    DP[0][j] = DP[0][j-1] + (end - start);
                }
                else if(i!=0 && j==0) {
                    start = 0;
                    end = 0;
                    k = arr_1[i-1] - 'A';
                    if(One[i][k] == 1) start = i;
                    if(One[i][k] == One[A.length()][k] + Two[B.length()][k]) end = i;
                    DP[i][0] = DP[i-1][0] + (end - start);
                }
                else {
                    start =0;
                    end =0;
                    k = arr_1[i-1]-'A';
                    if(One[i][k] + Two[j][k] == 1) start = i+j;  // 시작
                    if(One[i][k] + Two[j][k] == One[A.length()][k] + Two[B.length()][k]) end = i+j; //마지막
                    int len1 = end - start; // 시작이자 마지막이면 0
                    
                    start = 0;
                    end = 0;
                    k = arr_2[j-1]-'A';
                    if(One[i][k] + Two[j][k] == 1) start = i+j; //시작
                    if(One[i][k] + Two[j][k] == One[A.length()][k] + Two[B.length()][k]) end = i+j; //마지막
                    int len2 = end - start; // 시작이자 마지막이면 0
                    DP[i][j] = min(DP[i-1][j]+len1, DP[i][j-1]+len2);
                }
            }
        }
        cout << DP[A.length()][B.length()] << endl;
        T--;
    }
    /*
    fin.close();
    fout.close();
     */
    return 0;
}
