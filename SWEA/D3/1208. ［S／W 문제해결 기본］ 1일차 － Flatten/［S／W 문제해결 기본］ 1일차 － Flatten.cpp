import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test_case = 1;
		while(test_case<=10) {
			st = new StringTokenizer(br.readLine());
			int dump_count = Integer.parseInt(st.nextToken());
			int[] input = new int[100];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<100; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(input);
			while(dump_count>0) {
				input[99]--;
				input[0]++;
				if(input[0]> input[1] || input[99] < input[98]) Arrays.sort(input);
				dump_count--;
			}
			sb.append("#" + test_case + " " + (input[99]-input[0]) +  "\n");
			test_case++;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}