import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.LinkedHashSet;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] input_Str = br.readLine().split(" ");
		int K = Integer.parseInt(input_Str[0]);
		int L = Integer.parseInt(input_Str[1]);
		
		int rank = 0;
		int index = 0;
		LinkedHashSet<String> hashList = new LinkedHashSet<>();
		HashMap<String, Integer> hash = new HashMap<>();
		LinkedList<String> list = new LinkedList<>();
		for(int i=0; i<L; i++) {
			String name = br.readLine();
			if(hashList.contains(name)) {
				hashList.remove(name);
				hashList.add(name);
//				int tmp_index = hash.get(name);
//				list.remove(tmp_index);
//				index--;
//				rank--;
//				hash.put(name, rank);
//				list.add(name);
//				rank++;
//				index++;
//				
//				for(int j=tmp_index; j<index; j++) { // 이중포문 쓰면 안될듯
//					int tmp_rank = hash.get(list.get(j)) + 1;
//					hash.put(list.get(j), tmp_rank);
//				}
			}
			else {
				hashList.add(name);
				
//				hash.put(name, rank);
//				list.add(name);
//				rank++;
//				index++;
			}
		}
		for(String str: hashList) { // 출력
			sb.append(str + "\n");
			K--;
			if(K==0) break;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}