import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		while (R > 0) {
			int r = Integer.parseInt(st.nextToken());
			if (r == 1) { // 상하반전
				for (int j = 0; j < M; j++) {
					int start = 0;
					int end = N - 1;
					while (true) {
						int tmp = arr[start][j];
						arr[start][j] = arr[end][j];
						arr[end][j] = tmp;
						start++;
						end--;
						if (start >= end)
							break;
					}
				}
			} else if (r == 2) { // 좌우반전
				for (int i = 0; i < N; i++) {
					int start = 0;
					int end = M - 1;
					while (true) {
						int tmp = arr[i][start];
						arr[i][start] = arr[i][end];
						arr[i][end] = tmp;
						start++;
						end--;
						if (start >= end)
							break;
					}
				}
			} else if (r == 3) {
				int[][] tmp = new int[M][N];

				int jdx = 0;
				for (int i = 0; i < M; i++) {
					int idx = N - 1;
					for (int j = 0; j < N; j++) {
						tmp[i][j] = arr[idx][jdx];
						idx--;
					}
					jdx++;
				}

				arr = new int[M][N];
				arr = tmp;

				int t = N;
				N = M;
				M = t;
			} else if (r == 4) {
				int[][] tmp = new int[M][N];

				int jdx = M - 1;
				for (int i = 0; i < M; i++) {
					int idx = 0;
					for (int j = 0; j < N; j++) {
						tmp[i][j] = arr[idx][jdx];
						idx++;
					}
					jdx--;
				}

				arr = new int[M][N];
				arr = tmp;
				int t = N;
				N = M;
				M = t;
			} else {
				int n = N/2;
				int m = M/2;
				
				int[][] arr1 = new int[n][m];
				int[][] arr2 = new int[n][m];
				int[][] arr3 = new int[n][m];
				int[][] arr4 = new int[n][m];
				
				
				int idx = 0;
				int jdx = 0;
				
				for(int i = 0; i< n; i++) {
					jdx = 0;
					for(int j=0; j<m; j++) {
						arr1[idx][jdx] = arr[i][j];
						jdx++;
					}
					idx++;
				}
				
				idx =0;
				for(int i=n; i<N; i++) {
					jdx = 0;
					for(int j=0; j<m; j++) {
						arr4[idx][jdx] = arr[i][j];
						jdx++;
					}
					idx++;
				}
				idx =0;
				for(int i=0; i<n; i++) {
					jdx = 0;
					for(int j=m; j<M; j++) {
						arr2[idx][jdx] = arr[i][j];
						jdx++;
					}
					idx++;
				}
				idx =0;
				for(int i=n; i<N; i++) {
					jdx = 0;
					for(int j=m; j<M; j++) {
						arr3[idx][jdx] = arr[i][j];
						jdx++;
					}
					idx++;
				}
				
				int[][] tmp = new int[N][M];
				
				if(r==5) {
					idx =0;
					for(int i = 0; i< n; i++) {
						jdx = 0;
						for(int j=0; j<m; j++) {
							tmp[i][j] = arr4[idx][jdx];
							jdx++;
						}
						idx++;
					}
					idx =0;
					for(int i=n; i<N; i++) {
						jdx = 0;
						for(int j=0; j<m; j++) {
							tmp[i][j] = arr3[idx][jdx];
							jdx++;
						}
						idx++;
					}
					idx =0;
					for(int i=0; i<n; i++) {
						jdx = 0;
						for(int j=m; j<M; j++) {
							tmp[i][j] = arr1[idx][jdx];
							jdx++;
						}
						idx++;
					}
					idx =0;
					for(int i=n; i<N; i++) {
						jdx = 0;
						for(int j=m; j<M; j++) {
							tmp[i][j] = arr2[idx][jdx];
							jdx++;
						}
						idx++;
					}
				}
				else {
					idx =0;
					for(int i = 0; i< n; i++) { //1번
						jdx = 0;
						for(int j=0; j<m; j++) {
							tmp[i][j] = arr2[idx][jdx];
							jdx++;
						}
						idx++;
					}
					idx =0;
					for(int i=n; i<N; i++) { // 4번
						jdx = 0;
						for(int j=0; j<m; j++) {
							tmp[i][j] = arr1[idx][jdx];
							jdx++;
						}
						idx++;
					}
					idx =0;
					for(int i=0; i<n; i++) { // 2번
						jdx = 0;
						for(int j=m; j<M; j++) {
							tmp[i][j] = arr3[idx][jdx];
							jdx++;
						}
						idx++;
					}
					idx =0;
					for(int i=n; i<N; i++) { // 3번
						jdx = 0;
						for(int j=m; j<M; j++) {
							tmp[i][j] = arr4[idx][jdx];
							jdx++;
						}
						idx++;
					}
				}
				arr = tmp;
			}
			R--;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}