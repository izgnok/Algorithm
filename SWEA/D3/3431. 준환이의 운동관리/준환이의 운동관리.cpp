#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin>>T;
	/*
	   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
	*/
	for(test_case = 1; test_case <= T; ++test_case)
	{
		int U,L,X;
        cin >> U >> L >> X;
        int answer;
        if( U <= X && L >= X) answer = 0;
        else if( U > X ) answer = U-X;
        else if( L < X ) answer = -1;
        else {};
        
        cout << '#' << test_case << ' ' << answer << endl;
        
		/////////////////////////////////////////////////////////////////////////////////////////////
		/*
			 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
		 */
		/////////////////////////////////////////////////////////////////////////////////////////////


	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}