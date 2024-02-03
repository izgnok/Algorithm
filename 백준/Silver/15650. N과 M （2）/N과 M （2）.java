import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int N; // N개중
	private static int R; // R개를 뽑아 줄세우는 경우의 수 구하기 (순열)
	private static boolean[] isSelected; // 현재 뽑은 수 flag 배열
	private static int[] numbers; // 현재까지 뽑은 수를 저장하는 배열
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		numbers = new int[R];
		isSelected = new boolean[N+1];
		permutation(0, 1);
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void permutation(int cnt, int idx) {
		// 기저부분
		if(cnt == R) {
			for(int number: numbers) {
				sb.append(number + " ");
			}
			sb.append("\n");
			return;
		}
		// 유도부분		
		for(int i=idx ; i<=N; i++) { // 가능한 모든 수 시도
			if(isSelected[i]) { // 이미 뽑았는지 여부 확인
				continue;
			}
			numbers[cnt] = i; //뽑은 숫자 i를  결과 배열에 저장 
			isSelected[i] = true; // 뽑은 숫자 체크
			permutation(cnt+1, i+1); // 다음 숫자 뽑으러 가기
			isSelected[i] = false;  // 하나의 경우의 수를 구한 후
		}		
	}
}