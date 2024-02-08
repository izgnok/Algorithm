#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin>>T;
    
	for(test_case = 1; test_case <= T; ++test_case)
	{
		int max_time = 24;
        int a,b;
        cin >> a >> b;
        int answer = a + b;
        if(answer > 24 ) {
			answer = answer - 24;
        }
        else if(answer == 24 ) {
                answer = 0;
        }
        else {
        }    
        cout << "#" << test_case << " " << answer << endl;
	}
	return 0;
}