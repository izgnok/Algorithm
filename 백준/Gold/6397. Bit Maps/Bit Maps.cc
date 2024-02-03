#include <iostream>
#include <fstream>
#include <vector>
#include <string>
using namespace std;

int Bit_Transfer(int N, int M, int *arr, vector<char> *result) {
    int finish = -1;
    for(int i=0; i<N*M; i++) {
        if(arr[i] == 1) finish = 1;
        else {
            finish = -1;
            break;
        }
    }
    if(finish == 1) {
        result->push_back('1');
        return 0;
    }
    if(finish == -1) {
        for(int i=0; i<N*M; i++) {
            if(arr[i] == 0) finish = 0;
            else {
                finish = -1;
                break;
            }
        }
    }
    if(finish == 0) {
        result->push_back('0');
        return 0;
    }
    result->push_back('D');
    
    //좌상
    int left_up_N, left_up_M;
    if(N%2==0) {
        left_up_N = N/2;
    }
    else {
        left_up_N = N/2 + 1;
    }
    if(M%2==0) {
        left_up_M = M/2;
    }
    else {
        left_up_M = M/2 + 1;
    }
    int *left_up = new int[left_up_M * left_up_N];
    int idx = 0;
    int count = 0;
    for(int i=0; i < left_up_N*left_up_M; i++) {
        left_up[i] = arr[idx];
        idx++;
        count++;
        if(count == left_up_M) {
            count = 0;
            idx += (M/2);
        }
    }
    if(left_up_M*left_up_N != 0) Bit_Transfer(left_up_N, left_up_M, left_up, result);
    
    
    //우상
    int right_up_N, right_up_M;
    if(N%2==0) {
        right_up_N = N/2;
    }
    else {
        right_up_N = N/2 + 1;
    }
    right_up_M = M/2;
    int *right_up = new int[right_up_M * right_up_N];
    if(M%2 == 0) idx = M/2;
    else idx = M/2 +1;
    count = 0;
    for(int i=0; i < right_up_N*right_up_M; i++) {
        right_up[i] = arr[idx];
        idx++;
        count++;
        if(count==right_up_M) {
            count = 0;
            if(M%2 == 0) idx += M/2;
            else idx += M/2 +1;
        }
    }
    if(right_up_M*right_up_N != 0) Bit_Transfer(right_up_N, right_up_M, right_up, result);

    

    //좌하
    int left_down_N, left_down_M;
    left_down_N = N/2;
    if(M%2==0) {
        left_down_M = M/2;
    }
    else {
        left_down_M = M/2 + 1;
    }
    int *left_down = new int[left_down_M * left_down_N];
    idx = M * (N-(N/2));
    count = 0;
    for(int i=0; i < left_down_N*left_down_M; i++) {
        left_down[i] = arr[idx];
        idx++;
        count++;
        if(count== left_down_M) {
            count =0;
            idx += (M/2);
        }
    }
    if(left_down_M*left_down_N != 0) Bit_Transfer(left_down_N, left_down_M, left_down, result);

    
    //우하
    int right_down_N = N/2;
    int right_down_M = M/2;
    int *right_down = new int[right_down_M * right_down_N];
    if(M%2 == 0) idx = (M * (N-(N/2))) + M/2;
    else idx = (M * (N-(N/2))) + M/2 + 1;
    count = 0;
    for(int i=0; i < right_down_N*right_down_M; i++) {
        right_down[i] = arr[idx];
        idx++;
        count++;
        if(count== right_down_M) {
            count = 0;
            if(M%2 == 0) idx += M/2;
            else idx += M/2 +1;
        }
    }
    if(right_down_M*right_down_N != 0) Bit_Transfer(right_down_N, right_down_M, right_down, result);
    
    return 0;
}
//함수끝

int Arr_Transfer(int All_N, int All_M, int N, int M, vector<char> *input, int *result, int idx, int cmp) {
    if( input->at(0) == 'D') {
        input->erase(input->begin());
        int tmp_idx = idx;
        
        //좌상
        int left_up_N, left_up_M;
        if(N% 2 == 0) left_up_N = N/2;
        else left_up_N = N/2 + 1;
        if(M % 2 == 0) left_up_M = M/2;
        else left_up_M = M/2 + 1;
        if(left_up_M*left_up_N != 0) Arr_Transfer(All_N, All_M, left_up_N, left_up_M, input, result, idx, 0);
        
        //우상
        if(M%2 == 0) idx += M/2;
        else idx += M/2 +1;
        int right_up_N, right_up_M;
        if(N%2 ==0) right_up_N = N/2;
        else right_up_N = N/2 + 1;
        right_up_M = M/2;
        if(right_up_M*right_up_N != 0) Arr_Transfer(All_N, All_M, right_up_N, right_up_M, input, result, idx, 1);
        
        //좌하
        idx = tmp_idx + (All_M * (N-(N/2)));
        int left_down_N, left_down_M;
        if(M%2==0) left_down_M = M/2;
        else left_down_M = M/2 + 1;
        left_down_N = N/2;
        if(left_down_M*left_down_N != 0) Arr_Transfer(All_N, All_M, left_down_N, left_down_M, input, result, idx, 2);
        
        //우하
        if(M%2 == 0) idx += M/2;
        else idx += M/2 +1;
        int right_down_N = N/2;
        int right_down_M = M/2;
        if(right_down_M*right_down_N != 0) Arr_Transfer(All_N, All_M, right_down_N, right_down_M, input, result, idx, 3);
        return 0;
    }
    
    
    else  {
        int count = 0;
        int tmp_idx = idx;
        if(cmp == 0) {
            for(int i=0; i<N*M; i++) {
                if(input->at(0) == '1') result[idx] = 1;
                else result[idx] = 0;
                count++;
                idx++;
                if(count==M) {
                    count = 0;
                    idx = tmp_idx + All_M;
                    tmp_idx = idx;
                }
            }
        }
        else if(cmp == 1) {
            for(int i=0; i<N*M; i++) {
                if(input->at(0) == '1') result[idx] = 1;
                else result[idx] = 0;
                count++;
                idx++;
                if(count==M) {
                    count = 0;
                    idx = tmp_idx + All_M;
                    tmp_idx = idx;
                }
            }        }
        else if(cmp == 2) {
            for(int i=0; i<N*M; i++) {
                if(input->at(0) == '1') result[idx] = 1;
                else result[idx] = 0;
                count++;
                idx++;
                if(count==M) {
                    count = 0;
                    idx = tmp_idx + All_M;
                    tmp_idx = idx;
                }
            }
        }
        else if(cmp == 3) {
            for(int i=0; i<N*M; i++) {
                if(input->at(0) == '1') result[idx] = 1;
                else result[idx] = 0;
                count++;
                idx++;
                if(count==M) {
                    count = 0;
                    idx = tmp_idx + All_M;
                    tmp_idx = idx;
                }
            }
        }
        else {
            for(int i=0; i<N*M; i++) {
                if(input->at(0) == '1') result[i] = 1;
                else result[i] = 0;
            }
        }
        input->erase(input->begin());
        return 0;
    }
    return 0;
}
//함수끝


int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    /*
    ifstream cin("bitmap.inp");
    ofstream cout("bitmap.out");
    if (!cin) {
          cerr << "파일을 찾을 수 없습니다" << endl;
          exit(100);
    }
    */
    int preview = 0;
    string pre_s;
    while(1) {
        char shape;
        if(preview == 1) {
            shape = pre_s[0];
        }
        else cin >> shape;
        if(shape == '#') break;
        
        int N, M;
        if(preview == 1) {
            string n, m;
            int change = 0;
            for(int i=2; i< pre_s.length(); i++) {
                if(pre_s[i] == ' ') {
                    change = 1;
                    continue;
                }
                if(change == 0) n += pre_s[i];
                else m += pre_s[i];
            }
            N = stoi(n);
            M = stoi(m);
        }
        else cin >> N >> M;
        
        if(shape == 'B') {  // 행렬 -> 비트맵
            vector<char> result;
            int *arr = new int[N*M];
            for(int i=0; i<N*M; i++) { //입력
                char a;
                cin >> a;
                arr[i] = a - '0';
            }
            Bit_Transfer(N, M, arr, &result);
            
            cout << "D ";
            if(N/1<100 && N/1 >=10) {
                cout << " ";
            }
            else if(N/1<10) {
                cout << "  ";
            }
            cout << N << " ";
            if(M/1<100 && M/1 >=10) {
                cout << " ";
            }
            else if(M/1<=10) {
                cout << "  ";
            }
            cout << M << endl;
            int i=0;
            while(true) {
                if(i == result.size()) break;
                if(i % 50 == 0 && i != 0) cout << endl;
                cout << result.at(i++);
            }
            cout << endl;
        }
        else {  //비트맵 -> 행렬
            vector<char> input;
            int *result = new int[N*M];
            string s;
            while(true) {
                string ss;
                cin >> ss;
                if(ss[1] == ' ') {
                    preview = 1;
                    pre_s = ss;
                    break;
                }
                s += ss;
                if(ss.length() < 50) break;
            }
            for(int i=0; i< s.length(); i++) {
                char k = s[i];
                input.push_back(k);
            }
            Arr_Transfer(N, M, N, M, &input, result, 0, -1);
            
            
            cout << "B ";
            if(N/1<100 && N/1 >=10) {
                cout << " ";
            }
            else if(N%1<10) {
                cout << "  ";
            }
            cout << N << " ";
            if(M/1<100 && M/1 >=10) {
                cout << " ";
            }
            else if(M/1<=10) {
                cout << "  ";
            }
            cout << M << endl;
            int i = 0;
            while(true) {
                if(i == N*M) break;
                if(i % 50 == 0 && i !=0) cout << endl;
                cout << result[i++];
            }
            cout << endl;
        }
    }
    /*
    cin.close();
    cout.close();
    return 0;
     */
}
