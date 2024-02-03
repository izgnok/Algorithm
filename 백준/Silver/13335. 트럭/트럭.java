import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Queue; 
import java.util.LinkedList;
import java.util.ArrayDeque;


public class Main {
	public static class Truck{
		int N;
		int pass = 0;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] input = in.readLine().split(" ");
		int W = Integer.parseInt(input[1]);
		int L = Integer.parseInt(input[2]);
		
		Queue<Integer> q = new ArrayDeque<>(); // 출발전 트럭 모음
		LinkedList<Truck> truck = new LinkedList<Truck>(); // 출발 후 트럭모음
		String S = in.readLine();
		for(String s: S.split(" ")) {
			q.add(Integer.parseInt(s));
		}
		
		int time = 1; //총 걸리는 시간
		int cur_L = L; //현재하중
		while(!truck.isEmpty()  || !q.isEmpty()) {
			if( !q.isEmpty() && cur_L - q.peek() >= 0) { //하중여유가 있는 경우
				Truck tmp = new Truck();
				cur_L = cur_L - q.peek();
				tmp.N = q.peek();
				q.remove();
				truck.add(tmp);
			}
			for(int i=0; i<truck.size(); i++) {
				int tmp_n = truck.get(i).N;
				int tmp_pass = truck.get(i).pass;
				tmp_pass++;
				Truck tmp = new Truck();
				tmp.N = tmp_n;
				tmp.pass = tmp_pass;
				truck.set(i, tmp);
			}
			if(truck.get(0).pass == W) { //빠져나온차가있는지 확인 
				cur_L = cur_L + truck.get(0).N;
				truck.remove();
			}
			time++;
		}
		sb.append(time);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}