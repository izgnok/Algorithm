import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int target = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			target = Integer.parseInt(st.nextToken());
			int start = 0;
			int end = arr.length-1;
			while(true) {
				if(start>end) {
					sb.append("0\n");
					break;
				}
				int mid = (start+end)/2;
				if(arr[mid]==target) {
					sb.append("1\n");
					break;
				}
				else if(arr[mid] > target) {
					end = mid-1;
				}
				else {
					start = mid+1;
				}
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}