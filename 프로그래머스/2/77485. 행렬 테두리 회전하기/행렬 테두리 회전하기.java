import java.util.*;
class Solution {
    static int[][] graph;
    static int temp;
    static List<Integer> result = new LinkedList<>();
    public static int[] solution(int rows, int columns, int[][] queries) {
        //
        graph = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                graph[i][j] = i * columns + j + 1;
            }
        }

        for (int[] query : queries) {
            spin(query[0] - 1, query[1] - 1, query[2] - 1, query[3] - 1);
        }


        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    private static void spin(int y1, int x1, int y2, int x2) {
        temp = graph[y1][x1];
        int min = graph[y1][x1];
        for (int i = x1 + 1; i <= x2; i++) {
            swap(y1, i);
            min = Math.min(graph[y1][i], min);
        }

        for (int i = y1 + 1; i <= y2; i++) {
            swap(i, x2);
            min = Math.min(graph[i][x2], min);
        }

        for (int i = x2 - 1; i >= x1; i--) {
            swap(y2, i);
            min = Math.min(graph[y2][i], min);
        }

        for (int i = y2 - 1; i >= y1; i--) {
            swap(i, x1);
            min = Math.min(graph[i][x1], min);
        }
        result.add(min);
        //
    }

    private static void swap(int y, int x) {
        int temp2 = graph[y][x];
        graph[y][x] = temp;
        temp = temp2;
    }
}