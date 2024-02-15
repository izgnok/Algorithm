import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("Test5.txt"));
		// 여기에 코드를 작성하세요.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int[][] map = new int[20][20];
		for (int i = 1; i <= 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] deltasX = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, {0,-1} };
		int[][] deltasY = { { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, {-1,0} };
		int[][] deltasZ = { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 }, { 5, 5 }, {-1,-1} };
        int[][] deltasZ2 =  { { -1, 1 }, { -2, 2 }, { -3, 3 }, { -4, 4 }, { -5, 5 }, {1,-1} };
		int result = 0;
		int a = 0, b = 0;
		int row, col;
		for (int i = 1; i <= 19; i++) {
			for (int j = 1; j <= 19; j++) {
				int k = map[i][j];
				if (k == 0)
					continue;
				boolean check = false;
				
				//가로
				for (int x = 0; x < 4; x++) {
					row = i + deltasX[x][0];
					col = j + deltasX[x][1];
					if (col <= 19) {
						if (k == map[row][col]) {
							check = true;
						} else {
							check = false;
							break;
						}
					}
					else {
                        check = false;
                        break;
                    }
				}
				if(check) {
					row = i;
					col = j + deltasX[4][1];
					if(col <= 19) {
						if(k==map[row][col]) check = false;
					}
					row = i + deltasX[5][0];
					col = j + deltasX[5][1];
					if( col>=1 ) {
						if(k==map[row][col]) check = false;
					}
				}
				if (check) {
					result = map[i][j];
					a = i;
					b = j;
					break;
				}
				
				//세로
				for (int y = 0; y < 4; y++) {
					row = i + deltasY[y][0];
					col = j + deltasY[y][1];
					if (row <= 19) {
						if (k == map[row][col]) {
							check = true;
						} else {
							check = false;
							break;
						}
					}
					else {
                        check = false;
                        break;
                    }
				}
				if(check) {
					row = i + deltasY[4][0];
					col = j + deltasY[4][1];
					if(row <= 19) {
						if(k==map[row][col]) check = false;
					}
					row = i + deltasY[5][0];
					col = j + deltasY[5][1];
					if( row>=1 ) {
						if(k==map[row][col]) check = false;
					}
				}
				if (check) {
					result = map[i][j];
					a = i;
					b = j;
					break;
				}
				
				// 대각
				for (int z = 0; z < 4; z++) {
					row = i + deltasZ[z][0];
					col = j + deltasZ[z][1];
					if (row <= 19 && col <= 19) {
						if (k == map[row][col]) {
							check = true;
						} else {
							check = false;
							break;
						}
					}
					else {
                        check = false;
                        break;
                    }
				}
				if(check) {
					row = i + deltasZ[4][0];
					col = j + deltasZ[4][1];
					if(row <= 19 && col <= 19) {
						if(k==map[row][col]) check = false;
					}
					row = i + deltasZ[5][0];
					col = j + deltasZ[5][1];
					if( row>=1 && col >= 1) {
						if(k==map[row][col]) check = false;
					}
				}
				if (check) {
					result = map[i][j];
					a = i;
					b = j;
					break;
				}
                
                	// 대각
				for (int z = 0; z < 4; z++) {
					row = i + deltasZ2[z][0];
					col = j + deltasZ2[z][1];
					if (row >= 1 && col <= 19) {
						if (k == map[row][col]) {
							check = true;
						} else {
							check = false;
							break;
						}
					}
					else {
                        check = false;
                        break;
                    }
				}
				if(check) {
					row = i + deltasZ2[4][0];
					col = j + deltasZ2[4][1];
					if(row >= 1 && col <= 19) {
						if(k==map[row][col]) check = false;
					}
					row = i + deltasZ2[5][0];
					col = j + deltasZ2[5][1];
					if( row <= 19 && col >= 1) {
						if(k==map[row][col]) check = false;
					}
				}
				if (check) {
					result = map[i][j];
					a = i;
					b = j;
					break;
				}
			}
			if (result != 0)
				break;
		}
		sb.append(String.valueOf(result)).append("\n");
		if (result != 0)
			sb.append(a + " " + b);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}