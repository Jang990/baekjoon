class Solution {
    static int[] result = {0, 0};
    static int[][] graph;
    public static int[] solution(int[][] arr) {
        graph = arr;
        rec(0, 0, arr.length);
        return result;
    }

    private static void rec(int i, int j, int size) {
        if (size == 1) {
            result[graph[i][j]]++;
            return;
        }

        if (isOk(i, j, size)) {
            result[graph[i][j]]++;
            return;
        }

        int nextSize = size / 2;
        int[] dirI = {0, 0, nextSize, nextSize};
        int[] dirJ = {0, nextSize, 0, nextSize};
        for (int k = 0; k < 4; k++) {
            int nextI = i + dirI[k];
            int nextJ = j + dirJ[k];
            rec(nextI, nextJ, nextSize);
        }
    }

    private static boolean isOk(int i, int j, int size) {
        int base = graph[i][j];
        for (int k = i; k < i+size; k++) {
            for (int l = j; l < j+size; l++) {
                if(base != graph[k][l]) return false;
            }
        }
        return true;
    }
}