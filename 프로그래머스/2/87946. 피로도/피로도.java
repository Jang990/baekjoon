class Solution {
    boolean[] visited;
    int result = 0;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        rec(dungeons, k, 0);
        return result;
    }

    private void rec(int[][] dungeons, int currentK, int depth) {
        result = Math.max(result, depth);
        for (int i = 0; i < dungeons.length; i++) {
            if(visited[i] || dungeons[i][0] > currentK)
                continue;

            visited[i] = true;
            rec(dungeons, currentK - dungeons[i][1], depth + 1);
            visited[i] = false;
        }
    }
}