import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, E;
    static int[][] costs;        // 최단 거리 배열
    static int[][] answer;       // 첫 번째 경유지 배열
    final static int INF = 987654321; // 무한대 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 입력: 정점과 간선의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        costs = new int[N + 1][N + 1];
        answer = new int[N + 1][N + 1];

        // 비용 배열과 경로 배열 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    costs[i][j] = 0;      // 자기 자신으로 가는 비용은 0
                    answer[i][j] = 0;    // 경유지는 없음
                } else {
                    costs[i][j] = INF;   // 초기 비용은 무한대
                    answer[i][j] = -1;   // 경로 없음
                }
            }
        }

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            costs[x][y] = cost;
            costs[y][x] = cost;

            answer[x][y] = y; // 초기에는 바로 목적지로 설정
            answer[y][x] = x;
        }

        // 플로이드 워셜 알고리즘
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (costs[i][j] > costs[i][k] + costs[k][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j];
                        answer[i][j] = answer[i][k]; // 첫 번째 경유지를 갱신
                    }
                }
            }
        }

        // 결과 출력
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) sb.append("- ");
                else if (answer[i][j] == -1) sb.append("- ");
                else sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}