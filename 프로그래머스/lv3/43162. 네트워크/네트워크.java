class Solution {
    static boolean[] visited;
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[computers.length];

        for (int i = 0; i < computers.length; i++) {
            if (visited[i]) {
                continue;
            }

            answer++;
            visited[i] = true;
            dfs(computers, i);
        }

        return answer;
    }

    private static void dfs(int[][] computers, int now) {
        for (int i = 0; i < computers.length; i++) {
            if (visited[i] || computers[now][i] == 0) {
                continue;
            }

            visited[i] = true;
            dfs(computers, i);
        }
    }
}