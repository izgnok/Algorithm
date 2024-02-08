#include<iostream>
#include<algorithm>
#include<vector>
#include <numeric>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin>>T;
    
	for(test_case = 1; test_case <= T; ++test_case)
	{
		int t;
        cin >> t;
        vector<int> arr;
        int sum = 0;
        int k;
        for(int i=0; i<t; i++) {
            cin >> k;
            arr.push_back(k);
            sum += arr.at(i);
        }
		sort(arr.begin(),arr.end());
        double avg = sum / t;
		
        int count = 0;
       for(int i=0; i<arr.size();i++) {
        	if(arr.at(i) > avg) break;
           else count++;
       }
        cout << '#' << test_case << ' ' << count << endl;
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}