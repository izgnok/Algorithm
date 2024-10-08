import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		print(N, 0);
//		bw.write(sb.toString());
//		bw.flush();
//		bw.close();
	}

	static void print(int N, int count) {
		
		if(N==0) {
			for(int i=0; i<count; i++) System.out.print("_");
			System.out.println("\"재귀함수가 뭔가요?\"");
			for(int i=0; i<count; i++) System.out.print("_");
			System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			for(int i=0; i<count; i++) System.out.print("_");
			System.out.println("라고 답변하였지.");
			return;
		}
		for(int i=0; i<count; i++) System.out.print("_");
		System.out.println("\"재귀함수가 뭔가요?\"");
		for(int i=0; i<count; i++) System.out.print("_");
		System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		for(int i=0; i<count; i++) System.out.print("_");
		System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		for(int i=0; i<count; i++) System.out.print("_");
		System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		print(N-1, count+4);
		for(int i=0; i<count; i++) System.out.print("_");
		System.out.println("라고 답변하였지.");
		
	}
}