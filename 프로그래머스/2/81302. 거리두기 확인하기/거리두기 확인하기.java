class Solution {
    static String[][] graph;
    public static int[] solution(String[][] places) {
        int[] result = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            graph = convert(places[i]);
            result[i] = check();
        }
        return result;
    }

    private static int check() {
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                if(!graph[j][k].equals("P")) continue;

                boolean[][] visited = new boolean[5][5];
                visited[j][k] = true;
                if(!search(visited, 0, j, k))
                    return 0;
            }
        }
        return 1;
    }

    static int[] dirX = {0, 0, -1, 1},
            dirY = {1, -1, 0, 0};

    private static boolean search(boolean[][] visited, int depth, int y, int x) {
        if(depth >= 2)
            return true;

        boolean result = true;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];
            if(isOutOfBound(nextX,nextY) || visited[nextY][nextX] || graph[nextY][nextX].equals("X"))
                continue;
            if(graph[nextY][nextX].equals("P"))
                return false;
            visited[nextY][nextX] = true;
            result = result && search(visited, depth + 1, nextY, nextX);
        }
        return result;
    }

    private static boolean isOutOfBound(int x, int y) {
        return x < 0 || 5 <= x || y < 0 || 5 <= y;
    }

    private static String[][] convert(String[] place) {
        String[][] result = new String[5][5];
        for (int i = 0; i < 5; i++) {
            result[i] = place[i].split("");
        }
        return result;
    }
	
}