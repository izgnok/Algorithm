import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static Node head = new Node();
	static Node tail = new Node();
	static Node tmp_head = new Node();
	static Node tmp_tail = new Node();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int test_case = 1;
		while (test_case <= 10) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			head = null;
			tail = null;
 			for(int i=0; i<N; i++) {
				Node newNode = new Node();
				newNode.num = Integer.parseInt(st.nextToken());
				addNode(newNode);
		}

			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			while (count > 0) {
				String cmd = st.nextToken();
				if (cmd.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					
					tmp_head = null;
					tmp_tail = null;
					for(int i=0; i < y; i++) {
						Node newNode = new Node();
						newNode.num = Integer.parseInt(st.nextToken());
						addNode2(newNode);
					}
					insertNode(x);
					
				} else if (cmd.equals("D")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					DeleteNode(x,y);

				} else if (cmd.equals("A")) {
					int y = Integer.parseInt(st.nextToken());				
					for(int i=0; i<y; i++) {
						Node newNode = new Node();
						newNode.num = Integer.parseInt(st.nextToken());
						addNode(newNode);
					}
					
				}		
				count--;
			}
			
			sb.append("#" + test_case + " ");
			Node result = head;
			for(int i=0; i<10; i++) {
				sb.append(result.num + " ");
				result = result.next;
			}
			sb.append("\n");
			test_case++;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		int num;
		Node next = null;
		Node pre = null;
	}

	static void addNode(Node newNode) {
		if (head == null) { // 첫 추가
			head = newNode;
		} else {
			tail.next = newNode;
			newNode.pre = tail;
		}
		tail = newNode;
	}
	
	static void addNode2(Node newNode) {
		if (tmp_head == null) { // 첫 추가
			tmp_head = newNode;
		} else {
			tmp_tail.next = newNode;
			newNode.pre = tmp_tail;
		}
		tmp_tail = newNode;
	}

	static void insertNode(int x) {
		Node tmpNode = head;
		for(int i = 0; i<x-1; i++) {
			tmpNode = tmpNode.next;
		}
		if(x==0) {
			tmp_tail.next = tmpNode;
			tmpNode.pre = tmp_tail;
			head = tmp_head;
		}
		else if(tmpNode == tail) {
			tail.next = tmp_head;
			tmp_head.pre = tail;
			tail = tmp_head;
		}
		else {
			tmp_tail.next = tmpNode.next;
			tmp_tail = tmpNode.next.pre;
			tmpNode.next = tmp_head;
			tmp_head.pre = tmpNode;
		}
	}

	static void DeleteNode(int x, int y) {
		Node tmpNode = head;
		for (int i = 0; i < x-1; i++) {
			tmpNode = tmpNode.next;
		}
		Node tmpNode2 = tmpNode.next;
		for (int i = 0; i < y; i++) {
			tmpNode2 = tmpNode2.next;
		}
		if(tmpNode == head) {
			tmpNode2 = head;
		}
		else if(tmpNode2 == null) {
			tmpNode.next = null;
			tail = tmpNode;
		}
		else {
			tmpNode.next = tmpNode2;
			tmpNode2.pre = tmpNode;
		}
	}
}